package space.enthropy.services;

import io.smallrye.common.constraint.NotNull;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.enthropy.clients.BillCreateClient;
import space.enthropy.models.CreateBillEntity;
import space.enthropy.models.CreateBillRequest;
import space.enthropy.models.CreateBillResponse;
import space.enthropy.models.PaymentMethodRequest;
import space.enthropy.repositories.BillRepository;
import space.enthropy.repositories.PaymentMethodRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@ApplicationScoped
@Transactional
public class BillServiceImpl implements BillService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BillServiceImpl.class);

    @ConfigProperty(name = "qiwi.site.id")
    String siteId;

    @ConfigProperty(name = "qiwi.site.token")
    String siteToken;

    @RestClient
    BillCreateClient billCreateClient;

    @Inject
    PaymentMethodRepository paymentMethodRepository;

    @Inject
    BillRepository billRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("java:S2201")
    public Uni<CreateBillResponse> confirmBill(@NotBlank String accountId) {
        AtomicReference<CreateBillEntity> response = new AtomicReference<>();
        return billRepository.findBillByAccountId(accountId)
                .map(bill -> {
                    LOGGER.debug("confirmBill: {}", bill);
                    BigDecimal var = bill.getAmount().getValue();
                    var.setScale(2);
                    LOGGER.debug("Confirm bill with request NUMBER 1: {}", bill);
                    response.set(bill);
                    return bill;
                })
                .onItem().transformToUni(item -> paymentMethodRepository.findPaymentMethodByAccountId(accountId))
                .map(paymentMethod -> {
                    response.get().setPaymentMethod(paymentMethod);
                    LOGGER.debug("Confirm bill with request NUMBER 2: {}", paymentMethod);
                    return response.get();
                })
                .flatMap(bill -> {
                    PaymentMethodRequest paymentMethodRequest = new PaymentMethodRequest();
                    paymentMethodRequest.setPaymentToken(bill.getPaymentMethod().getPaymentToken());
                    paymentMethodRequest.setType(bill.getPaymentMethod().getType());
                    CreateBillRequest createBillRequest = new CreateBillRequest();
                    createBillRequest.setAmount(bill.getAmount());
                    createBillRequest.setCustomer(bill.getCustomer());
                    createBillRequest.setPaymentMethod(paymentMethodRequest);
                    return billCreateClient.createBill(siteId, UUID.randomUUID().toString(), "Bearer " + siteToken, createBillRequest)
                            .invoke(billResponse -> LOGGER.debug("Bill confirmed with response: {}", billResponse));
                });

    }

    @Override
    public Uni<CreateBillEntity> createBill(@NotNull @Valid CreateBillEntity createBillEntity) {
        LOGGER.debug("Creating bill with request: {}", createBillEntity);
        return billRepository.saveBill(createBillEntity)
                .invoke(bill -> LOGGER.debug("Bill created with response: {}", bill));
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Uni<?> getBillByAccountId(@NotBlank String accountId) {
        return billRepository.findBillAmountByAccountId(accountId)
                .invoke(bill -> LOGGER.debug("Bill retrieved with response: {}", bill));
    }
}
