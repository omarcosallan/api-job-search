package dev.marcos.api_job_search.controller;

import dev.marcos.api_job_search.dto.company.CompanyRequestDTO;
import dev.marcos.api_job_search.dto.company.CompanyResponseDTO;
import dev.marcos.api_job_search.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<CompanyResponseDTO> create(@RequestBody CompanyRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.create(dto));
    }
}
