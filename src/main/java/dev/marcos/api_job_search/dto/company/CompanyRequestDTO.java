package dev.marcos.api_job_search.dto.company;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CompanyRequestDTO(
        @NotEmpty(message = "Name cant be empty")
        @Size(min = 5, max = 150, message = "Name must be between 5 and 150 characters long")
        String name,

        @NotEmpty(message = "Website cant be empty")
        @Size(min = 5, max = 150, message = "Website must be between 5 and 150 characters long")
        String website,

        String description
) {
}
