package ca.quickdo.springintro.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "units")
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Double price;

    @Column
    private String name;

    @Column
    private Integer baseMultiplier;

    @ManyToOne(targetEntity = Product.class)
    private Product product;

    @OneToMany(targetEntity = Movement.class)
    @Builder.Default
    private Collection<Movement> supplies = new HashSet<>();
}