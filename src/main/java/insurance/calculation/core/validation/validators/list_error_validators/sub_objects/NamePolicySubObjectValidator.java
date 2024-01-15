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
class NamePolicySubObjectValidator extends PolicySubObjectErrorListValidator {
    @Autowired
    ValidationErrorFactory errorFactory;

    @Override
    public  Optional<ValidationError> buildError(String objectName, PolicySubObject subObject) {
        return subObject.getSubObjectName() == null || subObject.getSubObjectName().isEmpty()
                ? Optional.of(errorFactory.errorByCode("EMPTY_SUB_OBJECT_NAME", List.of(
                        new Placeholder("OBJECT_NAME", objectName))))
                : Optional.empty();
    }
}
