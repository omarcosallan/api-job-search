package dev.marcos.api_job_search.dto.company;

import dev.marcos.api_job_search.dto.job.JobSimpleResponseDTO;

import java.util.List;
import java.util.UUID;

public record CompanyWithJobsResponseDTO(
        UUID id,
        String name,
        String website,
        String description,
        List<JobSimpleResponseDTO> jobs
) {
}
