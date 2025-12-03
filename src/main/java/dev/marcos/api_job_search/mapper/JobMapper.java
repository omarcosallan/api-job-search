package dev.marcos.api_job_search.mapper;

import dev.marcos.api_job_search.dto.job.JobRequestDTO;
import dev.marcos.api_job_search.dto.job.JobResponseDTO;
import dev.marcos.api_job_search.dto.job.JobUpdateRequestDTO;
import dev.marcos.api_job_search.entity.Job;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface JobMapper {

    Job toEntity(JobRequestDTO dto);
    JobResponseDTO toDTO(Job entity);

    void updateJobFromDto(JobUpdateRequestDTO dto, @MappingTarget Job job);
}
