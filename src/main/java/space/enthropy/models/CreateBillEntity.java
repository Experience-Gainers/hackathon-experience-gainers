package space.enthropy.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.smallrye.common.constraint.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "bills")
public class CreateBillEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -7272870757188361520L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    @JsonIgnore
    private UUID id;

    @NotNull
    @JoinColumn(name = "amount_id", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    private Amount amount;
    @NotNull
    @Transient
    private PaymentMethod paymentMethod;
    @NotNull
    @JoinColumn(name = "customer_id", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CreateBillEntity that = (CreateBillEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
