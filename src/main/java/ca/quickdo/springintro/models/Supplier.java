package ca.quickdo.springintro.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "suppliers")
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate dateAdded;

    private String contact;

    public Supplier() {

    }
}