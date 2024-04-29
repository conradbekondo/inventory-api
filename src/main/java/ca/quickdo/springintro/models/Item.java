package ca.quickdo.springintro.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Jacksonized
@Embeddable
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Item {


    private String productId;
    private String movementId;
    private int quantity;
    private String unit;
    private double sellingPrice;




}
