package ca.quickdo.springintro.models;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Jacksonized
@Builder
@Entity
@Table(name = "stock_movements")
public class Movement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private Integer quantity;

    @Column
    @CreatedDate
    private Date supplyDate;

    @Column
    private Integer direction;

    @ManyToOne(targetEntity = Unit.class)
    private Unit unit;
}
