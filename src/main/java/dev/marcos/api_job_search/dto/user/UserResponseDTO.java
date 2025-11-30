package dev.marcos.api_job_search.dto.user;

import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String name,
        String email
) {
}
