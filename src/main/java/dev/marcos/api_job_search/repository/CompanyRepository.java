package dev.marcos.api_job_search.repository;

import dev.marcos.api_job_search.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
    boolean existsByOwnerId(UUID uuid);

    Optional<Company> findByIdAndOwnerId(UUID id, UUID ownerId);
}
