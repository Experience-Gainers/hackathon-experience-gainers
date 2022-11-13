package space.enthropy.resources;

import io.smallrye.common.constraint.NotNull;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.enthropy.models.PayerConfirmation;
import space.enthropy.models.PayerInitToken;
import space.enthropy.models.PayerTokenResponse;
import space.enthropy.services.PayerService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/payer")
@Tag(name = "Payer API", description = "Payer operations")
public class PayerResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(PayerResource.class);
    @Inject
    PayerService payerService;


    @POST
    @Path("/token/init/{siteId}")
    @Timed(name = "space.enthropy.resources.payerresource.inittokenforpayer", description = "A measure of how long it takes to init token for payer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Handles payer token init", description = "Handles payer token init")
    @APIResponse(responseCode = "200", description = "Payer token is being initialized", content = @Content(schema = @Schema(implementation = PayerTokenResponse.class), mediaType = MediaType.APPLICATION_JSON))
    @APIResponse(responseCode = "400", description = "Bad request")
    public Uni<PayerTokenResponse> initTokenForPayer(@NotBlank @PathParam("siteId") @Schema(example = "sa3khn-11") String siteId, @NotNull @Valid PayerInitToken payerInitToken) {
        LOGGER.debug("initTokenForPayer: {}", payerInitToken);
        return payerService.initToken(siteId, payerInitToken);
    }

    @POST
    @Path("/token/confirm/{siteId}")
    @Timed(name = "space.enthropy.resources.payerresource.confirmtokenforpayer", description = "A measure of how long it takes to confirm token for payer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Handles payer token confirmation", description = "Handles payer token confirmation")
    @APIResponse(responseCode = "200", description = "Payer token is being confirmed", content = @Content(schema = @Schema(implementation = PayerTokenResponse.class), mediaType = MediaType.APPLICATION_JSON))
    @APIResponse(responseCode = "400", description = "Bad request")
    public Uni<PayerTokenResponse> confirmTokenForPayer(@NotBlank @HeaderParam("x-account-id") String accountId, @PathParam("siteId") @Schema(example = "sa3khn-11") String siteId, @NotNull @Valid PayerConfirmation payerConfirmation) {
        LOGGER.debug("confirmTokenForPayer: {}", payerConfirmation);
        return payerService.confirmToken(accountId, siteId, payerConfirmation);
    }


}
