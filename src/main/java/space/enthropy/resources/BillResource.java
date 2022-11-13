package space.enthropy.resources;

import io.smallrye.common.constraint.NotNull;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.enthropy.models.CreateBillEntity;
import space.enthropy.models.CreateBillResponse;
import space.enthropy.services.BillServiceImpl;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/bill")
@Tag(name = "Bill API", description = "Bill operations")
public class BillResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(BillResource.class);
    @Inject
    BillServiceImpl billService;


    @POST
    @Timed(name = "space.enthropy.resources.billresource.createbill", description = "A measure of how long it takes to create bill")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Handles bill creation", description = "Handles bill creation")
    @APIResponse(responseCode = "200", description = "Bill created", content = @Content(schema = @Schema(implementation = CreateBillResponse.class)))
    @APIResponse(responseCode = "400", description = "Bad request")
    public Uni<CreateBillEntity> createBill(@NotNull @Valid CreateBillEntity createBillEntity) {
        LOGGER.debug("createBill: {}", createBillEntity);
        return billService.createBill(createBillEntity);
    }

    @GET
    @Path("/{accountId}")
    @Timed(name = "space.enthropy.resources.billresource.getbillbyaccountid", description = "A measure of how long it takes to get bill by account id")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Handles retrieval bill by account id", description = "Handles retrieval bill by account id")
    @APIResponse(responseCode = "200", description = "OK")
    @APIResponse(responseCode = "400", description = "Bad request")
    public Uni<RestResponse<?>> getBillByAccountId(@PathParam("accountId") String accountId) {
        LOGGER.debug("getBillByAccountId: {}", accountId);
        return billService.getBillByAccountId(accountId).onItem().transform(resp -> {
            if (resp == null) {
                return RestResponse.notFound();
            }
            return RestResponse.ok(resp);
        });
    }

    @GET
    @Path("/{accountId}/pay")
    @Timed(name = "space.enthropy.resources.billresource.confirmbill", description = "A measure of how long it takes to confirm bill")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Handles bill confirmation", description = "Handles bill confirmation")
    @APIResponse(responseCode = "200", description = "Bill confirmed", content = @Content(schema = @Schema(implementation = CreateBillResponse.class)))
    @APIResponse(responseCode = "400", description = "Bad request")
    public Uni<CreateBillResponse> confirmBill(@NotBlank @PathParam("accountId") String accountId) {
        LOGGER.debug("confirmBill: {}", accountId);
        return billService.confirmBill(accountId);
    }
}