package dev.marcos.api_job_search.repository.specs;

import dev.marcos.api_job_search.entity.Job;
import dev.marcos.api_job_search.entity.enums.Modality;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class JobSpec {

    public static Specification<Job> create(String title, Modality modality, Boolean active, BigDecimal minSalary) {
        return Specification.allOf(
                title(title),
                modality(modality),
                active(active),
                minSalary(minSalary)
        );
    }

    public static Specification<Job> title(String title) {
        if (title == null) return null;
        return (root, query, builder) -> builder.equal(root.get("title"), title);
    }

    public static Specification<Job> modality(Modality modality) {
        if (modality == null) return null;
        return (root, query, builder) -> builder.equal(root.get("modality"), modality);
    }

    public static Specification<Job> active(Boolean active) {
        if (active == null) return null;
        return (root, query, builder) -> builder.equal(root.get("active"), active);
    }

    public static Specification<Job> minSalary(BigDecimal minSalary) {
        if (minSalary == null) return null;
        return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("salary"), minSalary);
    }
}
