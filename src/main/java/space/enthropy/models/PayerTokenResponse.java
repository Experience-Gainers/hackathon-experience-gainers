package space.enthropy.models;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class PayerTokenResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = -5220576050444924673L;

    private String requestId;
    private Status status;
    private PayerToken token;
}
