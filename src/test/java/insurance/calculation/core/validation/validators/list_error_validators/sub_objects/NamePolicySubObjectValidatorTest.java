package insurance.calculation.core.validation.validators.list_error_validators.sub_objects;

import insurance.calculation.core.validation.util.ValidationErrorFactory;
import insurance.calculation.dto.Policy;
import insurance.calculation.dto.PolicyObject;
import insurance.calculation.dto.PolicySubObject;
import insurance.calculation.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NamePolicySubObjectValidatorTest {
    @InjectMocks
    private NamePolicySubObjectValidator namePolicySubObjectValidator;
    @Mock
    private ValidationErrorFactory errorFactory;
    @Test
    public void returnErrorNameIsNull(){
        ValidationError error = mock(ValidationError.class);
        Policy policy = mock(Policy.class);
        PolicySubObject policySubObject = mock(PolicySubObject.class);
        when(policySubObject.getSubObjectName()).thenReturn(null);
        PolicyObject policyObject = mock(PolicyObject.class);
        when(policyObject.getSubObjects()).thenReturn(List.of(policySubObject));
        when(policy.getPolicyObjects()).thenReturn(List.of(policyObject));
        when(errorFactory.errorByCode(eq("EMPTY_SUB_OBJECT_NAME"), anyList())).thenReturn(error);
        assertFalse(namePolicySubObjectValidator.validate(policy).isEmpty());
        assertEquals(namePolicySubObjectValidator.validate(policy).get(0), error);
    }
    @Test
    public void returnErrorNameIsEmpty(){
        ValidationError error = mock(ValidationError.class);
        Policy policy = mock(Policy.class);
        PolicySubObject policySubObject = mock(PolicySubObject.class);
        when(policySubObject.getSubObjectName()).thenReturn("");
        PolicyObject policyObject = mock(PolicyObject.class);
        when(policyObject.getSubObjects()).thenReturn(List.of(policySubObject));
        when(policy.getPolicyObjects()).thenReturn(List.of(policyObject));
        when(errorFactory.errorByCode(eq("EMPTY_SUB_OBJECT_NAME"), anyList())).thenReturn(error);
        assertFalse(namePolicySubObjectValidator.validate(policy).isEmpty());
        assertEquals(namePolicySubObjectValidator.validate(policy).get(0), error);
    }
    @Test
    public void returnNoError(){
        Policy policy = mock(Policy.class);
        PolicySubObject policySubObject = mock(PolicySubObject.class);
        when(policySubObject.getSubObjectName()).thenReturn("House");
        PolicyObject policyObject = mock(PolicyObject.class);
        when(policyObject.getSubObjects()).thenReturn(List.of(policySubObject));
        when(policy.getPolicyObjects()).thenReturn(List.of(policyObject));
        assertTrue(namePolicySubObjectValidator.validate(policy).isEmpty());
    }
}
