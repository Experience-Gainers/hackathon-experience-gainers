package space.enthropy.services;


import io.smallrye.common.constraint.NotNull;
import io.smallrye.mutiny.Uni;
import space.enthropy.models.CreateBillEntity;
import space.enthropy.models.CreateBillResponse;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public interface BillService {

    /**
     * Confirms bill for payments in Qiwi
     *
     * @param accountId account id
     * @return created bill
     * @author Sergey Shamov
     */
    Uni<CreateBillResponse> confirmBill(@NotBlank String accountId);

    /**
     * Saves bill to database
     *
     * @param createBillEntity bill to save
     * @return created bill
     * @author Sergey Shamov
     */
    Uni<CreateBillEntity> createBill(@NotNull @Valid CreateBillEntity createBillEntity);

    /**
     * Retrieves bill from Qiwi by AccountId
     *
     * @param accountId account id
     * @return bill
     * @author Sergey Shamov
     */
    Uni<?> getBillByAccountId(@NotBlank String accountId);
}
