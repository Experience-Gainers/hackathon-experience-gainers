package space.enthropy.repositories;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import space.enthropy.models.CreateBillEntity;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BillRepository implements PanacheRepository<CreateBillEntity> {

    public Uni<CreateBillEntity> saveBill(CreateBillEntity createBillEntity) {
        return persistAndFlush(createBillEntity);
    }

    public Uni<CreateBillEntity> findBillAmountByAccountId(String accountId) {
        return find("from CreateBillEntity as w where w.customer.account = ?1", accountId).firstResult();
    }

    public Uni<CreateBillEntity> findBillByAccountId(String accountId) {
        return find("from CreateBillEntity as w where w.customer.account = ?1", accountId).firstResult();
    }
}
