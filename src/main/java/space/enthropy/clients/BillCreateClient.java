package space.enthropy.clients;


import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import space.enthropy.models.CreateBillRequest;
import space.enthropy.models.CreateBillResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RegisterRestClient(configKey = "bill-api")
public interface BillCreateClient {

    @PUT
    @Path("/partner/payin/v1/sites/{siteId}/payments/{billId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Retry(maxRetries = 5, delay = 2000)
    Uni<CreateBillResponse> createBill(@PathParam("siteId") String siteId,
                                       @PathParam("billId") String billId,
                                       @HeaderParam("Authorization") String authorization,
                                       @RequestBody CreateBillRequest createBillRequest);
}
