package dev.marcos.api_job_search.dto.company;

import java.util.UUID;

public record CompanyRequestDTO(
        String name,
        String website,
        String description,
        UUID ownerId
) {
}
