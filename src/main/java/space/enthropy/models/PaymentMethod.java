package space.enthropy.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "payment_methods")
public class PaymentMethod implements Serializable {
    @Serial
    private static final long serialVersionUID = 4748619700623919509L;


    @NotBlank
    @Id
    @Column(name = "id", nullable = false)
    @Schema(example = "d83aa5ce-a60f-4798-b98b-17e8249e8d29")
    private String paymentToken;
    @Column
    @JsonIgnore
    private UUID paymentFakeId;
    @NotBlank
    @Column
    @Schema(example = "TOKEN")
    private String type;
    @Column
    @JsonIgnore
    private String accountId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PaymentMethod that = (PaymentMethod) o;
        return paymentFakeId != null && Objects.equals(paymentFakeId, that.paymentFakeId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
