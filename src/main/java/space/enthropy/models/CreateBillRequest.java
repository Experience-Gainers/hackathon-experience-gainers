package space.enthropy.models;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


@Data
public class CreateBillRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 2503240180270487796L;

    private Amount amount;
    private PaymentMethodRequest paymentMethod;
    private Customer customer;
}
