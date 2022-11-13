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
import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "customers")
public class Customer implements Serializable {
    @Serial
    private static final long serialVersionUID = 8836854416300008596L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @JsonIgnore
    private Long id;

    @NotBlank(message = "Account required")
    @Schema(example = "437c71a4-8517-4134-8b3f-a9f11d83548b")
    private String account;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Customer customer = (Customer) o;
        return id != null && Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
