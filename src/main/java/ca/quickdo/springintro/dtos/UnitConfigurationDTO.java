package ca.quickdo.springintro.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UnitConfigurationDTO {
    private Double price;
    private String name;
    private Integer baseMultiplier;
}
