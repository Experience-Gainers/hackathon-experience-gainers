package space.enthropy.services;

import io.smallrye.common.constraint.NotNull;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.enthropy.clients.PayerClient;
import space.enthropy.models.PayerConfirmation;
import space.enthropy.models.PayerInitToken;
import space.enthropy.models.PayerTokenResponse;
import space.enthropy.models.PaymentMethod;
import space.enthropy.repositories.PaymentMethodRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@ApplicationScoped
@Transactional
public class PayerServiceImpl implements PayerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PayerServiceImpl.class);


    @RestClient
    PayerClient payerClient;

    @ConfigProperty(name = "qiwi.site.token")
    String siteToken;

    @Inject
    PaymentMethodRepository paymentMethodRepository;


    /**
     * {@inheritDoc}
     */
    @Override
    public Uni<PayerTokenResponse> initToken(@NotBlank String siteId, @NotNull @Valid PayerInitToken payerInitToken) {
        LOGGER.debug("Init token with request: {}", payerInitToken);
        return payerClient.initToken(siteId, "Bearer " + siteToken, payerInitToken)
                .onItem().invoke(payer -> LOGGER.debug("Payer token initialized with response: {}", payer));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Uni<PayerTokenResponse> confirmToken(String accountId, String siteId, PayerConfirmation payerConfirmation) {
        LOGGER.debug("Confirm token with request: {}", payerConfirmation);
        UUID fakePaymentToken = UUID.randomUUID();
        AtomicReference<UUID> originalPaymentToken = new AtomicReference<>();
        AtomicReference<PayerTokenResponse> response = new AtomicReference<>();
        return payerClient.confirmToken(siteId, "Bearer " + siteToken, payerConfirmation)
                .onItem()
                .invoke(payer -> LOGGER.debug("Payer token confirmed with response: {}", payer))
                .map(item -> {
                    originalPaymentToken.set(UUID.fromString(item.getToken().getValue()));
                    item.getToken().setValue(fakePaymentToken.toString());
                    response.set(item);
                    return response.get();
                })
                .map(item -> {
                    PaymentMethod paymentMethod = new PaymentMethod();
                    paymentMethod.setPaymentFakeId(fakePaymentToken);
                    paymentMethod.setPaymentToken(originalPaymentToken.toString());
                    paymentMethod.setType("TOKEN");
                    paymentMethod.setAccountId(accountId);
                    return paymentMethod;
                })
                .onItem()
                .transformToUni(task -> paymentMethodRepository.createPaymentMethod(task)
                        .invoke(paymentMethod -> LOGGER.debug("Payment method saved with fake id: {}", task.getPaymentFakeId()))
                .replaceWith(response.get()));
    }

}
