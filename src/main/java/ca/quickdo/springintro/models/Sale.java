package ca.quickdo.springintro.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.Embeddable;
import java.util.List;

@Data
@Jacksonized
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Sale {
    private String timestamp;
    private List<Item> items;

}




