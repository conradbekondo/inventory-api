package ca.quickdo.springintro.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductDTO {
    private String name;
    private String color;
    private String description;
    private int quantity;
}

