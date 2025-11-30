package dev.marcos.api_job_search.controller;

import dev.marcos.api_job_search.dto.user.LoginDTO;
import dev.marcos.api_job_search.dto.user.TokenDTO;
import dev.marcos.api_job_search.dto.user.UserRequestDTO;
import dev.marcos.api_job_search.dto.user.UserResponseDTO;
import dev.marcos.api_job_search.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> register(@RequestBody LoginDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.login(dto));
    }
}
