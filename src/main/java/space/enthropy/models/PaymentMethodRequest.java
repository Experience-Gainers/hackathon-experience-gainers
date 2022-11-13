package space.enthropy.models;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class PaymentMethodRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = -5054458276096227394L;

    private String type;
    private String paymentToken;
}
