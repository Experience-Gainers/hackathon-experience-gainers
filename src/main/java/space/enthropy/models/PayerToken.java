package space.enthropy.models;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Data
public class PayerToken implements Serializable {
    @Serial
    private static final long serialVersionUID = 2549232061800200080L;

    private String value;
    private Instant expiredDate;
}
