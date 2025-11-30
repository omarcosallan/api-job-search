package dev.marcos.api_job_search.dto.user;

public record UserRequestDTO(
        String name,
        String email,
        String password
) {
}
