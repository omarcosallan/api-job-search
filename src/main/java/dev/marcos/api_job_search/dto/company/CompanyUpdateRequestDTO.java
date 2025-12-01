package dev.marcos.api_job_search.dto.company;

import jakarta.validation.constraints.Size;

public record CompanyUpdateRequestDTO(
        @Size(max = 150, message = "Name cannot be longer than 150 characters")
        String name,

        @Size(max = 150, message = "Website cannot be longer than 150 characters")
        String website,

        String description
) {
}
