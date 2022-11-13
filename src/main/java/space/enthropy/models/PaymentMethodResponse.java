package space.enthropy.models;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class PaymentMethodResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 7639428927555935966L;

    private String id;
    private String type;
}
