package insurance.calculation.core.validation.validators.list_error_validators;

import insurance.calculation.core.validation.util.ValidationErrorFactory;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NamePolicyObjectValidatorTest {
    @InjectMocks
    private NamePolicyObjectValidator namePolicyObjectValidator;
    @Mock
    private ValidationErrorFactory errorFactory;

    @Test
    public void returnErrorNameIsNull(){
        ValidationError error = mock(ValidationError.class);
        Policy policy = mock(Policy.class);
        PolicyObject policyObject = mock(PolicyObject.class);
        when(policyObject.getObjectName()).thenReturn(null);
        when(policy.getPolicyObjects()).thenReturn(List.of(policyObject));
        when(errorFactory.errorByCode("EMPTY_OBJECT_NAME")).thenReturn(error);
        assertFalse(namePolicyObjectValidator.validate(policy).isEmpty());
        assertEquals(namePolicyObjectValidator.validate(policy).get(0), error);
    }
    @Test
    public void returnErrorNameIsEmpty(){
        ValidationError error = mock(ValidationError.class);
        Policy policy = mock(Policy.class);
        PolicyObject policyObject = mock(PolicyObject.class);
        when(policyObject.getObjectName()).thenReturn("");
        when(policy.getPolicyObjects()).thenReturn(List.of(policyObject));
        when(errorFactory.errorByCode("EMPTY_OBJECT_NAME")).thenReturn(error);
        assertFalse(namePolicyObjectValidator.validate(policy).isEmpty());
        assertEquals(namePolicyObjectValidator.validate(policy).get(0), error);
    }
    @Test
    public void returnNoError(){
        Policy policy = mock(Policy.class);
        PolicyObject policyObject = mock(PolicyObject.class);
        when(policyObject.getObjectName()).thenReturn("TV");
        when(policy.getPolicyObjects()).thenReturn(List.of(policyObject));
        assertTrue(namePolicyObjectValidator.validate(policy).isEmpty());
    }
}
