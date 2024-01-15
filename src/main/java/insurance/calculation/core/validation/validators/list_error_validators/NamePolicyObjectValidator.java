package insurance.calculation.core.validation.validators.list_error_validators;

import insurance.calculation.core.validation.util.ValidationErrorFactory;
import insurance.calculation.dto.Policy;
import insurance.calculation.dto.PolicyObject;
import insurance.calculation.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Component
class NamePolicyObjectValidator implements PolicyListErrorValidator {
    @Autowired
    ValidationErrorFactory errorFactory;

    @Override
    public List<ValidationError> validate(Policy policy) {
        return policy.getPolicyObjects().stream()
                .map(this::buildErrorIfEmpty)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private Optional<ValidationError> buildErrorIfEmpty(PolicyObject object) {
        return object.getObjectName() == null || object.getObjectName().isEmpty()
                ? Optional.of(errorFactory.errorByCode("EMPTY_OBJECT_NAME"))
                : Optional.empty();
    }
}
