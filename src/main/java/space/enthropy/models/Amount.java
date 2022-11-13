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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "amounts")
public class Amount implements Serializable {
    @Serial
    private static final long serialVersionUID = 5967569054371806454L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @JsonIgnore
    private Long id;

    @NotBlank
    @Column(nullable = false)
    @Schema(example = "RUB")
    private String currency;
    @Positive
    @Digits(integer = 10, fraction = 2)
    @Column(nullable = false)
    @Schema(example = "100.00")
    private BigDecimal value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Amount amount = (Amount) o;
        return id != null && Objects.equals(id, amount.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
