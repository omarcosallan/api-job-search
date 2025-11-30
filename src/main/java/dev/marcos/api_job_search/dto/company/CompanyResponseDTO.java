package dev.marcos.api_job_search.dto.company;

import dev.marcos.api_job_search.dto.user.UserResponseDTO;

import java.util.UUID;

public record CompanyResponseDTO(
        UUID id,
        String name,
        String website,
        String description,
        UserResponseDTO owner
) {
}
