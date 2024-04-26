package ca.quickdo.springintro.dto;

import ca.quickdo.springintro.models.Product;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import org.springframework.context.annotation.PropertySource;

@Data
@Jacksonized
@Builder
public class NewProductDto {
    private String name;
    private int startingQuantity;

}