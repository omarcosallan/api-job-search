package dev.marcos.api_job_search.controller;

import dev.marcos.api_job_search.dto.job.JobRequestDTO;
import dev.marcos.api_job_search.dto.job.JobResponseDTO;
import dev.marcos.api_job_search.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping
    public ResponseEntity<JobResponseDTO> save(@Valid @RequestBody JobRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobService.save(dto));
    }
}
