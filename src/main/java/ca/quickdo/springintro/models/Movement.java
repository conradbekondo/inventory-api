package ca.quickdo.springintro.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Jacksonized
@Builder
@AllArgsConstructor
@Entity
@Table(name = "stock_movements")
public class Movement {

    public Movement() {

    }

    public enum MovementStatus  {
        Pending, Complete
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer quantity;

    @CreatedDate
    private Date supplyDate;

    private Integer direction;

    @ManyToOne(targetEntity = Unit.class)
    private Unit unit;

    private MovementStatus status;
}