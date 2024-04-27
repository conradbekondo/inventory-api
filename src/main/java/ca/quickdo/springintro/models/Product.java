package ca.quickdo.springintro.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

@Data
@Jacksonized
@Builder
@Entity
@NoArgsConstructor(onConstructor =@__({@JsonCreator}))
@AllArgsConstructor(onConstructor =@__({@JsonCreator}))
@Table(name = "products")
public class Product {
    @Column
    private String name, color, description;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "product")
    @Builder.Default
    private Collection<Unit> units = new HashSet<>();

    @CreatedDate
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    public boolean hasUnitWithName(String unitName) {
        for (Unit unit : units) {
            if (unit.getName().equals(unitName)) {
                return true;
            }
        }
        return false;
    }
}
