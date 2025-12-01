package dev.marcos.api_job_search.validator;

import dev.marcos.api_job_search.annotation.ValidModality;
import dev.marcos.api_job_search.entity.enums.Modality;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class ModalityValidator implements ConstraintValidator<ValidModality, String> {

    private static final Set<String> VALID_MODALITIES = Arrays.stream(Modality.values())
            .map(Enum::name)
            .collect(Collectors.toSet());

    @Override
    public boolean isValid(String modality, ConstraintValidatorContext constraintValidatorContext) {
        if (modality != null) return VALID_MODALITIES.contains(modality.toUpperCase());
        return true;
    }
}
