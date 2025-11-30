package dev.marcos.api_job_search.service;

import dev.marcos.api_job_search.dto.user.LoginDTO;
import dev.marcos.api_job_search.dto.user.TokenDTO;
import dev.marcos.api_job_search.dto.user.UserRequestDTO;
import dev.marcos.api_job_search.dto.user.UserResponseDTO;
import dev.marcos.api_job_search.entity.User;
import dev.marcos.api_job_search.exception.ConflictException;
import dev.marcos.api_job_search.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public UserResponseDTO register(UserRequestDTO dto) {
        if (userRepository.existsByEmail(dto.email())) {
            throw new ConflictException("Email already exists, please, try other email address");
        }

        User user = User.builder()
                .name(dto.name())
                .email(dto.email())
                .password(new BCryptPasswordEncoder().encode(dto.password()))
                .build();

        User savedUser = userRepository.save(user);
        return new UserResponseDTO(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getPassword());
    }

    public TokenDTO login(LoginDTO dto) {
        UsernamePasswordAuthenticationToken usernamePassword  = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        authenticationManager.authenticate(usernamePassword);
        String token = tokenService.generateToken(dto.email());
        return new TokenDTO(token);
    }
}
