package dev.marcos.api_job_search.service;

import dev.marcos.api_job_search.dto.company.CompanyRequestDTO;
import dev.marcos.api_job_search.dto.company.CompanyResponseDTO;
import dev.marcos.api_job_search.dto.company.CompanyUpdateRequestDTO;
import dev.marcos.api_job_search.dto.company.CompanyWithJobsResponseDTO;
import dev.marcos.api_job_search.entity.Company;
import dev.marcos.api_job_search.entity.User;
import dev.marcos.api_job_search.exception.ConflictException;
import dev.marcos.api_job_search.exception.NotFoundException;
import dev.marcos.api_job_search.mapper.CompanyMapper;
import dev.marcos.api_job_search.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public CompanyResponseDTO create(CompanyRequestDTO dto) {
        if (companyRepository.existsByOwnerId(dto.ownerId())) {
            throw new ConflictException("You are already linked to another company.");
        }

        Company company = companyMapper.toEntity(dto);

        Company savedCompany = companyRepository.save(company);
        return companyMapper.toDTO(savedCompany);
    }

    public CompanyResponseDTO update(UUID id, CompanyUpdateRequestDTO dto) {
        Company company = getById(id);

        companyMapper.updateCompanyFromDto(dto, company);

        Company savedCompany = companyRepository.save(company);
        return companyMapper.toDTO(savedCompany);
    }

    public List<CompanyResponseDTO> findAll() {
        return companyRepository.findAll().stream().map(companyMapper::toDTO).toList();
    }

    public CompanyResponseDTO findById(UUID id) {
        Company company = getById(id);
        return companyMapper.toDTO(company);
    }

    public Company getById(UUID id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Company not found with id: " + id));
    }

    public CompanyWithJobsResponseDTO findJobsByCompanyId(UUID id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Company not found with id: " + id));

        return companyMapper.toDTOWithJobs(company);
    }
}
