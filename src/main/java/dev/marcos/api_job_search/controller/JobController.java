package dev.marcos.api_job_search.controller;

import dev.marcos.api_job_search.dto.job.JobRequestDTO;
import dev.marcos.api_job_search.dto.job.JobResponseDTO;
import dev.marcos.api_job_search.entity.enums.Modality;
import dev.marcos.api_job_search.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/v1/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping
    public ResponseEntity<JobResponseDTO> save(@Valid @RequestBody JobRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobService.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<JobResponseDTO>> findAll(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Modality modality,
            @RequestParam(required = false, defaultValue = "true") boolean active,
            @RequestParam(required = false) BigDecimal minSalary
    ) {
        return ResponseEntity.ok(jobService.findAll(page, size, title, modality, active, minSalary));
    }
}
