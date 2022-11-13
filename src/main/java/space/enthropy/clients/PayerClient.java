package space.enthropy.clients;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import space.enthropy.models.PayerConfirmation;
import space.enthropy.models.PayerInitToken;
import space.enthropy.models.PayerTokenResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RegisterRestClient(configKey = "payer-api")
public interface PayerClient {

    @POST
    @Path("/partner/payin-tokenization-api/v1/sites/{siteId}/token-requests")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Retry(maxRetries = 5, delay = 2000)
    Uni<PayerTokenResponse> initToken(@PathParam("siteId") String siteId,
                                      @HeaderParam("Authorization") String authorization,
                                      @RequestBody PayerInitToken payerInitToken);

    @POST
    @Path("/partner/payin-tokenization-api/v1/sites/{siteId}/token-requests/complete")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Retry(maxRetries = 5, delay = 2000)
    Uni<PayerTokenResponse> confirmToken(@PathParam("siteId") String siteId,
                                         @HeaderParam("Authorization") String authorization,
                                         @RequestBody PayerConfirmation payerConfirmation);


}
