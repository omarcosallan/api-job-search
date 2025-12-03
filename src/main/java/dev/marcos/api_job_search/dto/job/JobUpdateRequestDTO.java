package dev.marcos.api_job_search.dto.job;

import dev.marcos.api_job_search.annotation.ValidModality;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record JobUpdateRequestDTO(
        @Size(max = 150, message = "Name cannot be longer than 150 characters")
        String name,

        String description,

        @ValidModality
        String modality,

        @DecimalMin(value = "0.01", message = "Salary must be greater than zero")
        BigDecimal salary,

        @Size(max = 150, message = "Location cannot be longer than 150 characters")
        String location
) {
}
