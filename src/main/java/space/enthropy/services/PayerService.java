package space.enthropy.services;

import io.smallrye.common.constraint.NotNull;
import io.smallrye.mutiny.Uni;
import space.enthropy.models.PayerConfirmation;
import space.enthropy.models.PayerInitToken;
import space.enthropy.models.PayerTokenResponse;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public interface PayerService {


    /**
     * Initializes token for payer
     *
     * @param siteId         site id
     * @param payerInitToken payer init token
     * @return payer token response
     * @author Sergey Shamov
     */
    Uni<PayerTokenResponse> initToken(@NotBlank String siteId, @NotNull @Valid PayerInitToken payerInitToken);

    /**
     * Confirms token for payer
     *
     * @param siteId            site id
     * @param payerConfirmation payer confirmation
     * @return payer token response
     * @author Sergey Shamov
     */
    Uni<PayerTokenResponse> confirmToken(@NotBlank String accountId, @NotBlank String siteId, @NotNull @Valid PayerConfirmation payerConfirmation);


}
