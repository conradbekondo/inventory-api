package ca.quickdo.springintro.dtos;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;

@Data
@Builder
@Jacksonized
public class SupplierDTO {
    private String name;
    private LocalDate dateAdded;
    private String contact;
}
