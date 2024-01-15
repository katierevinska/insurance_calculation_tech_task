package insurance.calculation.core.validation.validators.list_error_validators.sub_objects;

import insurance.calculation.core.validation.util.Placeholder;
import insurance.calculation.core.validation.util.ValidationErrorFactory;
import insurance.calculation.dto.PolicySubObject;
import insurance.calculation.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
class RiskTypeSubObjectValidator extends PolicySubObjectErrorListValidator {
    @Autowired
    ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationError> buildError(String objectName, PolicySubObject subObject) {
        return subObject.getRiskType() == null || subObject.getRiskType().isEmpty()
                ? Optional.of(errorFactory.errorByCode("EMPTY_SUB_OBJECT_RISK_TYPE", List.of(
                new Placeholder("OBJECT_NAME", objectName))))
                : Optional.empty();
    }
}
