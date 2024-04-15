package ca.quickdo.springintro.models;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Data
@Jacksonized
@Builder
@Entity
@Table(name = "units")
public class Unit {
    @Column
    private Double price;

    @Column
    private String name;

    @Column
    private Integer baseMultiplier;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(targetEntity = Product.class)
    private Product product;

    @OneToMany(targetEntity = Movement.class)
    @Builder.Default
    private Collection<Movement> supplies = new HashSet<>();
}
