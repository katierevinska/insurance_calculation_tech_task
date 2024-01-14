package insurance.calculation.core.validation;

import insurance.calculation.core.validation.validators.list_error_validators.PolicyListErrorValidator;
import insurance.calculation.core.validation.validators.list_error_validators.sub_objects.PolicySubObjectErrorListValidator;
import insurance.calculation.core.validation.validators.singl_error_validators.PolicyErrorValidator;
import insurance.calculation.dto.ValidationError;
import insurance.calculation.dto.Policy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PolicyValidation {
    @Autowired
    List<PolicyErrorValidator> policyErrorValidators;
    @Autowired
    List<PolicyListErrorValidator> policyListErrorValidators;

    public List<ValidationError> validate(Policy policy) {
        return concatenateLists(getSingleErrors(policy), getListErrors(policy));
    }

    private List<ValidationError> getSingleErrors(Policy policy) {
        return policyErrorValidators.stream()
                .map(validator -> validator.validate(policy))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private List<ValidationError> getListErrors(Policy policy) {
        return policyListErrorValidators.stream()
                .map(validator -> validator.validate(policy))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<ValidationError> concatenateLists(List<ValidationError> singleErrors, List<ValidationError> listErrors) {
        singleErrors.addAll(listErrors);
        return singleErrors;
    }
}
