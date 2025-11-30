package dev.marcos.api_job_search.dto.job;

import dev.marcos.api_job_search.entity.enums.Modality;

import java.math.BigDecimal;
import java.util.UUID;

public record JobRequestDTO(
        String name,
        String description,
        Modality modality,
        BigDecimal salary,
        Boolean active,
        String location,
        UUID companyId
) {
}
