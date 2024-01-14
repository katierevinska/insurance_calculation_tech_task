package insurance.calculation.core.validation.validators.singl_error_validators;

import insurance.calculation.core.util.ValidationErrorFactory;
import insurance.calculation.dto.Policy;
import insurance.calculation.dto.PolicyObject;
import insurance.calculation.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListPolicyObjectsErrorValidatorTest {
    @InjectMocks
    ListPolicyObjectsErrorValidator listPolicyObjectsErrorValidator;
    @Mock
    ValidationErrorFactory errorFactory;

    @Test
    public void returnErrorListNull() {
        ValidationError error = mock(ValidationError.class);
        Policy policy = mock(Policy.class);
        when(policy.getPolicyObjects()).thenReturn(null);
        when(errorFactory.errorByCode("EMPTY_OBJECT_LIST")).thenReturn(error);
        assertTrue(listPolicyObjectsErrorValidator.validate(policy).isPresent());
        assertEquals(listPolicyObjectsErrorValidator.validate(policy).get(), error);
    }
    @Test
    public void returnErrorListEmpty() {
        ValidationError error = mock(ValidationError.class);
        Policy policy = mock(Policy.class);
        when(policy.getPolicyObjects()).thenReturn(List.of());
        when(errorFactory.errorByCode("EMPTY_OBJECT_LIST")).thenReturn(error);
        assertTrue(listPolicyObjectsErrorValidator.validate(policy).isPresent());
        assertEquals(listPolicyObjectsErrorValidator.validate(policy).get(), error);
    }
    @Test
    public void returnNoError() {
        Policy policy = mock(Policy.class);
        PolicyObject policyObject = mock(PolicyObject.class);
        when(policy.getPolicyObjects()).thenReturn(List.of(policyObject));
        assertTrue(listPolicyObjectsErrorValidator.validate(policy).isEmpty());
    }
}
