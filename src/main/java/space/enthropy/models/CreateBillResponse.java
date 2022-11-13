package space.enthropy.models;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Data
public class CreateBillResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 5084468340997082365L;

    private String billId;
    private String invoiceUid;
    private Amount amount;
    private Instant expirationDateTime;
    private Status status;
    private Customer customer;
    private List<String> flags;
    private String payUrl;
}
