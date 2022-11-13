package space.enthropy.models;

import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;

@Data
public class PayerConfirmation implements Serializable {
    @Serial
    private static final long serialVersionUID = 348822149566518350L;

    @NotBlank(message = "RequestId is required")
    @Schema(example = "2cba83e9-26c4-48db-ab03-465bb170255e")
    private String requestId;
    @NotBlank(message = "SmsCode is required")
    @Schema(example = "1276")
    private String smsCode;
}
