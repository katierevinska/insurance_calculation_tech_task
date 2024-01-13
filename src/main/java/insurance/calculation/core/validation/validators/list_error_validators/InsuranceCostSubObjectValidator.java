package insurance.calculation.core.validation.validators.list_error_validators;

import insurance.calculation.core.util.ValidationErrorFactory;
import insurance.calculation.dto.Policy;
import insurance.calculation.dto.PolicySubObject;
import insurance.calculation.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Component
class InsuranceCostSubObjectValidator implements PolicyErrorListValidator {
    @Autowired
    ValidationErrorFactory errorFactory;

    @Override
    public List<ValidationError> validate(Policy policy) {
        return policy.getPolicyObjects().stream()
                .flatMap(obj -> obj.getSubObjects().stream())
                .map(this::buildErrorIfEmpty)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private Optional<ValidationError> buildErrorIfEmpty(PolicySubObject subObject) {
        return subObject.getInsuranceCost() == null
                ? Optional.of(errorFactory.errorByCode("EMPTY_SUB_OBJECT_COST"))
                : Optional.empty();
    }
}
