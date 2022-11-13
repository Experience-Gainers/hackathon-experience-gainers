package space.enthropy.repositories;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import space.enthropy.models.PaymentMethod;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PaymentMethodRepository implements PanacheRepository<PaymentMethod> {

    public Uni<PaymentMethod> createPaymentMethod(PaymentMethod paymentMethod) {
        return persistAndFlush(paymentMethod);
    }

    public Uni<PaymentMethod> findPaymentMethodByAccountId(String accountId) {
        return find("accountId", accountId).firstResult();
    }
}
