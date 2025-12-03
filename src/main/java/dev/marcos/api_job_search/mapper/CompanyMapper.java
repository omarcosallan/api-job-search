package dev.marcos.api_job_search.mapper;

import dev.marcos.api_job_search.dto.company.CompanyRequestDTO;
import dev.marcos.api_job_search.dto.company.CompanyResponseDTO;
import dev.marcos.api_job_search.dto.company.CompanyUpdateRequestDTO;
import dev.marcos.api_job_search.dto.company.CompanyWithJobsResponseDTO;
import dev.marcos.api_job_search.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CompanyMapper {

    Company toEntity(CompanyRequestDTO dto);
    CompanyResponseDTO toDTO(Company entity);

    CompanyWithJobsResponseDTO toDTOWithJobs(Company entity);

    void updateCompanyFromDto(CompanyUpdateRequestDTO dto, @MappingTarget Company company);
}
