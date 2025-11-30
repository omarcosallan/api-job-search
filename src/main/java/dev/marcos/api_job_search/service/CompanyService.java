package dev.marcos.api_job_search.service;

import dev.marcos.api_job_search.dto.company.CompanyRequestDTO;
import dev.marcos.api_job_search.dto.company.CompanyResponseDTO;
import dev.marcos.api_job_search.entity.Company;
import dev.marcos.api_job_search.entity.User;
import dev.marcos.api_job_search.exception.ConflictException;
import dev.marcos.api_job_search.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final UserService userService;

    public CompanyResponseDTO create(CompanyRequestDTO dto) {
        if (companyRepository.existsByOwnerId(dto.ownerId())) {
            throw new ConflictException("You are already linked to another company.");
        }

        User owner = userService.findById(dto.ownerId());

        Company company = Company.builder()
                .name(dto.name())
                .website(dto.website())
                .description(dto.description())
                .owner(owner)
                .build();

        Company savedCompany = companyRepository.save(company);
        return new CompanyResponseDTO(
                savedCompany.getId(),
                savedCompany.getName(),
                savedCompany.getWebsite(),
                savedCompany.getDescription());
    }
}
