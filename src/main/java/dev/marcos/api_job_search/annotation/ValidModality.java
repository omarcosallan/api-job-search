package dev.marcos.api_job_search.annotation;

import dev.marcos.api_job_search.validator.ModalityValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ModalityValidator.class)
public @interface ValidModality {
    String message() default "Invalid modality";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
