package insurance.calculation.core.validation.validators.singl_error_validators;

import insurance.calculation.dto.Policy;
import insurance.calculation.dto.ValidationError;

import java.util.Optional;

public interface PolicyErrorValidator {
    Optional<ValidationError> validate(Policy policy);
}
