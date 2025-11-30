package dev.marcos.api_job_search.dto.company;

import java.util.UUID;

public record CompanyResponseDTO(
        UUID id,
        String name,
        String website,
        String description
) {
}
