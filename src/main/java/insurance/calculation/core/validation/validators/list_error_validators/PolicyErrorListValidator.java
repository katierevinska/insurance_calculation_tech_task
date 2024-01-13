
package insurance.calculation.core.validation.validators.list_error_validators;

import insurance.calculation.dto.Policy;
import insurance.calculation.dto.ValidationError;

import java.util.List;

public interface PolicyErrorListValidator {
    List<ValidationError> validate(Policy policy);
}
