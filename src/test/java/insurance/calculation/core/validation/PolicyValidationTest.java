package insurance.calculation.core.validation;

import insurance.calculation.core.validation.validators.list_error_validators.PolicyListErrorValidator;
import insurance.calculation.core.validation.validators.list_error_validators.sub_objects.PolicySubObjectErrorListValidator;
import insurance.calculation.core.validation.validators.singl_error_validators.PolicyErrorValidator;
import insurance.calculation.core.validation.validators.singl_error_validators.PolicyNumberErrorValidatorTest;
import insurance.calculation.dto.Policy;
import insurance.calculation.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PolicyValidationTest {
    @InjectMocks
    private PolicyValidation policyValidation;

    @Test
    public void validateListErrors() {
        Policy policy = mock(Policy.class);
        ValidationError error = mock(ValidationError.class);
        PolicyErrorValidator policyErrorValidator = mock(PolicyErrorValidator.class);
        PolicyListErrorValidator policyListErrorValidator1 = mock(PolicyListErrorValidator.class);
        PolicyListErrorValidator policyListErrorValidator2 = mock(PolicyListErrorValidator.class);
        when(policyErrorValidator.validate(policy)).thenReturn(Optional.empty());
        when(policyListErrorValidator1.validate(policy)).thenReturn(List.of(error, error));
        when(policyListErrorValidator2.validate(policy)).thenReturn(List.of(error, error));
        ReflectionTestUtils.setField(policyValidation, "policyErrorValidators",
                List.of(policyErrorValidator));
        ReflectionTestUtils.setField(policyValidation, "policyListErrorValidators",
                List.of(policyListErrorValidator1, policyListErrorValidator2));
        assertEquals(policyValidation.validate(policy).size(), 4);
    }
    @Test
    public void validateSingleErrors() {
        Policy policy = mock(Policy.class);
        ValidationError error = mock(ValidationError.class);
        PolicyErrorValidator policyErrorValidator1 = mock(PolicyErrorValidator.class);
        PolicyErrorValidator policyErrorValidator2 = mock(PolicyErrorValidator.class);
        PolicyListErrorValidator policyListErrorValidator = mock(PolicyListErrorValidator.class);
        when(policyErrorValidator1.validate(policy)).thenReturn(Optional.of(error));
        when(policyErrorValidator2.validate(policy)).thenReturn(Optional.of(error));
        when(policyListErrorValidator.validate(policy)).thenReturn(List.of());
        ReflectionTestUtils.setField(policyValidation, "policyErrorValidators",
                List.of(policyErrorValidator1, policyErrorValidator2));
        ReflectionTestUtils.setField(policyValidation, "policyListErrorValidators",
                List.of(policyListErrorValidator));
        assertEquals(policyValidation.validate(policy).size(), 2);

    }
    @Test
    public void validateBothErrors() {
        Policy policy = mock(Policy.class);
        ValidationError error = mock(ValidationError.class);
        PolicyErrorValidator policyErrorValidator = mock(PolicyErrorValidator.class);
        PolicyListErrorValidator policyListErrorValidator = mock(PolicyListErrorValidator.class);
        when(policyErrorValidator.validate(policy)).thenReturn(Optional.of(error));
        when(policyListErrorValidator.validate(policy)).thenReturn(List.of(error, error));
        ReflectionTestUtils.setField(policyValidation, "policyErrorValidators",
                List.of(policyErrorValidator));
        ReflectionTestUtils.setField(policyValidation, "policyListErrorValidators",
                List.of(policyListErrorValidator));
        assertEquals(policyValidation.validate(policy).size(), 3);

    }
}
