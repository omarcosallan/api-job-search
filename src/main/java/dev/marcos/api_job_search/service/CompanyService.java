package dev.marcos.api_job_search.service;

import dev.marcos.api_job_search.dto.company.CompanyRequestDTO;
import dev.marcos.api_job_search.dto.company.CompanyResponseDTO;
import dev.marcos.api_job_search.dto.user.UserResponseDTO;
import dev.marcos.api_job_search.entity.Company;
import dev.marcos.api_job_search.entity.User;
import dev.marcos.api_job_search.exception.BusinessException;
import dev.marcos.api_job_search.exception.ConflictException;
import dev.marcos.api_job_search.exception.NotFoundException;
import dev.marcos.api_job_search.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyResponseDTO create(CompanyRequestDTO dto) {
        User owner = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (companyRepository.existsByOwnerId(owner.getId())) {
            throw new ConflictException("You are already linked to another company.");
        }

        Company company = Company.builder()
                .name(dto.name())
                .website(dto.website())
                .description(dto.description())
                .owner(owner)
                .build();

        Company savedCompany = companyRepository.save(company);
        UserResponseDTO userResponse = new UserResponseDTO(
                savedCompany.getOwner().getId(),
                savedCompany.getOwner().getName(),
                savedCompany.getOwner().getEmail()
        );
        return new CompanyResponseDTO(
                savedCompany.getId(),
                savedCompany.getName(),
                savedCompany.getWebsite(),
                savedCompany.getDescription(),
                userResponse);
    }

    public CompanyResponseDTO update(UUID id, CompanyRequestDTO dto) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Company not found with id: " + id));

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!company.getOwner().getEmail().equals(currentUser.getEmail())) {
            throw new BusinessException("You are not allowed to update this company");
        }

        if (dto.name() != null) {
            company.setName(dto.name());
        }

        if (dto.website() != null) {
            company.setWebsite(dto.website());
        }

        if (dto.description() != null) {
            company.setDescription(dto.description());
        }

        Company savedCompany = companyRepository.save(company);
        UserResponseDTO userResponse = new UserResponseDTO(
                savedCompany.getOwner().getId(),
                savedCompany.getOwner().getName(),
                savedCompany.getOwner().getEmail()
        );
        return new CompanyResponseDTO(
                savedCompany.getId(),
                savedCompany.getName(),
                savedCompany.getWebsite(),
                savedCompany.getDescription(),
                userResponse);
    }
}
