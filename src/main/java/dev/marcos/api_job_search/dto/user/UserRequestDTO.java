package dev.marcos.api_job_search.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(
        @NotEmpty(message = "Name cant be empty")
        @Size(min = 5, max = 150, message = "Name must be between 5 and 150 characters long")
        String name,

        @NotEmpty(message = "Email cant be empty")
        @Size(min = 5, max = 150, message = "Email must be between 5 and 150 characters long")
        @Email(message = "Should be in email format")
        String email,

        @NotEmpty(message = "Password cant be empty")
        String password
) {
}
