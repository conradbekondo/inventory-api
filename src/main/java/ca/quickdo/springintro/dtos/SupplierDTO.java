package ca.quickdo.springintro.dtos;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class SupplierDTO {
    private String name;
    private LocalDate dateAdded;
    private String contact;
}
