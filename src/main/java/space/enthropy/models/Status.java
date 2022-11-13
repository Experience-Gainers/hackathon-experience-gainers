package space.enthropy.models;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Data
public class Status implements Serializable {
    @Serial
    private static final long serialVersionUID = -7907137153156211734L;

    private String value;
    private Instant changedDateTime;
}
