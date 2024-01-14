
package insurance.calculation.core.validation.validators.list_error_validators.sub_objects;

import insurance.calculation.core.validation.validators.list_error_validators.PolicyListErrorValidator;
import insurance.calculation.dto.Policy;
import insurance.calculation.dto.PolicyObject;
import insurance.calculation.dto.PolicySubObject;
import insurance.calculation.dto.ValidationError;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class PolicySubObjectErrorListValidator implements PolicyListErrorValidator {

    @Override
    public List<ValidationError> validate(Policy policy) {
        return policy.getPolicyObjects().stream()
                .flatMap(obj -> validateSubObjectsOfTheObject(obj).stream())
                .collect(Collectors.toList());
    }

    private List<ValidationError> validateSubObjectsOfTheObject(PolicyObject object) {
        return object.getSubObjects().stream()
                .map(subObject -> buildError(object.getObjectName(), subObject))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    protected abstract Optional<ValidationError> buildError(String objectName, PolicySubObject subObject);
}
