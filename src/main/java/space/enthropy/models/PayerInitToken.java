package space.enthropy.models;

import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;

@Data
public class PayerInitToken implements Serializable {
    @Serial
    private static final long serialVersionUID = -674793840176641762L;
    @NotBlank(message = "RequestId is required")
    @Schema(example = "976d3b41-eb34-4192-b6dc-8595f7661cc1")
    private String requestId;
    @NotBlank(message = "Phone is required")
    @Length(min = 11, max = 11, message = "Phone must be 11 digits")
    @Schema(example = "78000000005")
    private String phone;
    @NotBlank(message = "AccountId is required")
    @Schema(example = "437c71a4-8517-4134-8b3f-a9f11d83548b")
    private String accountId;
}
