package dev.marcos.api_job_search.service;

import dev.marcos.api_job_search.dto.company.CompanyRequestDTO;
import dev.marcos.api_job_search.dto.company.CompanyResponseDTO;
import dev.marcos.api_job_search.dto.company.CompanyUpdateRequestDTO;
import dev.marcos.api_job_search.entity.Company;
import dev.marcos.api_job_search.entity.User;
import dev.marcos.api_job_search.exception.ConflictException;
import dev.marcos.api_job_search.exception.NotFoundException;
import dev.marcos.api_job_search.mapper.CompanyMapper;
import dev.marcos.api_job_search.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public CompanyResponseDTO create(CompanyRequestDTO dto) {
        User owner = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (companyRepository.existsByOwnerId(owner.getId())) {
            throw new ConflictException("You are already linked to another company.");
        }

        Company company = companyMapper.toEntity(dto);

        Company savedCompany = companyRepository.save(company);
        return companyMapper.toDTO(savedCompany);
    }

    public CompanyResponseDTO update(UUID id, CompanyUpdateRequestDTO dto) {
        User owner = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Company company = companyRepository.findByIdAndOwnerId(id, owner.getId())
                .orElseThrow(() -> new NotFoundException("Company not found or you are not the owner"));

        companyMapper.updateCompanyFromDto(dto, company);

        Company savedCompany = companyRepository.save(company);
        return companyMapper.toDTO(savedCompany);
    }
}
