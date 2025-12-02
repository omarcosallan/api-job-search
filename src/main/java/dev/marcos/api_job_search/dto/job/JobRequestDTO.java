package dev.marcos.api_job_search.dto.job;

import dev.marcos.api_job_search.annotation.ValidModality;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.UUID;

public record JobRequestDTO(
        @NotEmpty(message = "Name cant be empty")
        @Size(min = 5, max = 150, message = "Name must be between 5 and 150 characters long")
        String name,

        String description,

        @NotNull(message = "Modality cant be null")
        @ValidModality
        String modality,

        @NotNull(message = "Salary cant be null")
        @DecimalMin(value = "0.01", message = "Salary must be greater than zero.")
        BigDecimal salary,

        Boolean active,

        @NotEmpty(message = "Location cant be empty")
        @Size(min = 5, max = 150, message = "Location must be between 5 and 150 characters long")
        String location,

        @NotNull(message = "Company id cant be null")
        UUID companyId
) {
}
