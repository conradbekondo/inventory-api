package ca.quickdo.springintro.models;


import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Jacksonized
@Embeddable
public class Item {


    private String productId;
    private String movementId;
    private int quantity;
    private String unit;
    private double sellingPrice;


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getMovementId() {
        return movementId;
    }

    public void setMovementId(String movementId) {
        this.movementId = movementId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

}
