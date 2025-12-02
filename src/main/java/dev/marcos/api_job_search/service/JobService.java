package dev.marcos.api_job_search.service;

import dev.marcos.api_job_search.dto.job.JobRequestDTO;
import dev.marcos.api_job_search.dto.job.JobResponseDTO;
import dev.marcos.api_job_search.entity.Company;
import dev.marcos.api_job_search.entity.Job;
import dev.marcos.api_job_search.entity.User;
import dev.marcos.api_job_search.entity.enums.Modality;
import dev.marcos.api_job_search.mapper.JobMapper;
import dev.marcos.api_job_search.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static dev.marcos.api_job_search.repository.specs.JobSpec.create;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;
    private final CompanyService companyService;

    public JobResponseDTO save(JobRequestDTO dto) {
        User owner = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Company company = companyService.findByIdAndOwnerId(dto.companyId(), owner.getId());

        Job job = jobMapper.toEntity(dto);
        job.setCompany(company);

        Job savedJob = jobRepository.save(job);

        return jobMapper.toDTO(savedJob);
    }

    public List<JobResponseDTO> findAll(
            int page,
            int size,
            String title,
            Modality modality,
            Boolean active,
            BigDecimal minSalary) {

        Specification<Job> specs = create(title, modality, active, minSalary);

        Pageable pageable = PageRequest.of(page, size);

        return jobRepository.findAll(specs, pageable).stream()
                .map(jobMapper::toDTO).collect(Collectors.toList());
    }
}
