package insurance.calculation.core.validation.validators.singl_error_validators;

import insurance.calculation.core.validation.util.ValidationErrorFactory;
import insurance.calculation.dto.Policy;
import insurance.calculation.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class PolicyNumberErrorValidator implements PolicyErrorValidator {
    @Autowired
    ValidationErrorFactory errorFactory;
    @Override
    public Optional<ValidationError> validate(Policy policy) {
        return policy.getPolicyNumber() == null || policy.getPolicyNumber().isEmpty()
                ? Optional.of(errorFactory.errorByCode("EMPTY_NUMBER"))
                : Optional.empty();
    }
}
