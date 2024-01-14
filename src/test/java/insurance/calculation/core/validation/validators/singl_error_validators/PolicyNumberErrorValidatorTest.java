package insurance.calculation.core.validation.validators.singl_error_validators;

import insurance.calculation.core.util.ValidationErrorFactory;
import insurance.calculation.dto.Policy;
import insurance.calculation.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PolicyNumberErrorValidatorTest {
    @InjectMocks
    private PolicyNumberErrorValidator policyNumberErrorValidator;
    @Mock
    ValidationErrorFactory errorFactory;

    @Test
    public void returnErrorNumberNull() {
        ValidationError error = mock(ValidationError.class);
        Policy policy = mock(Policy.class);
        when(policy.getPolicyNumber()).thenReturn(null);
        when(errorFactory.errorByCode("EMPTY_NUMBER")).thenReturn(error);
        assertTrue(policyNumberErrorValidator.validate(policy).isPresent());
        assertEquals(policyNumberErrorValidator.validate(policy).get(), error);
    }
    @Test
    public void returnErrorNumberEmpty() {
        ValidationError error = mock(ValidationError.class);
        Policy policy = mock(Policy.class);
        when(policy.getPolicyNumber()).thenReturn("");
        when(errorFactory.errorByCode("EMPTY_NUMBER")).thenReturn(error);
        assertTrue(policyNumberErrorValidator.validate(policy).isPresent());
        assertEquals(policyNumberErrorValidator.validate(policy).get(), error);
    }
    @Test
    public void returnNoError() {
        Policy policy = mock(Policy.class);
        when(policy.getPolicyNumber()).thenReturn("LV20-02-100000-5");
        assertTrue(policyNumberErrorValidator.validate(policy).isEmpty());
    }
}
