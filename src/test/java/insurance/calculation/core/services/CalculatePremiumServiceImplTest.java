package insurance.calculation.core.services;

import insurance.calculation.core.calculation.PremiumCalculator;
import insurance.calculation.core.validation.PolicyValidation;
import insurance.calculation.dto.CalculatePremiumResponse;
import insurance.calculation.dto.Policy;
import insurance.calculation.dto.PremiumResult;
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

public class CalculatePremiumServiceImplTest {
    @InjectMocks
    private CalculatePremiumServiceImpl service;
    @Mock
    private PolicyValidation policyValidation;
    @Mock
    private PremiumCalculator premiumCalculator;
    @Test
    public void returnResponseWithErrors(){
        Policy policy = mock(Policy.class);
        ValidationError validationError = mock(ValidationError.class);
        when(policyValidation.validate(policy)).thenReturn(List.of(validationError));
        CalculatePremiumResponse response = service.calculatePremium(policy);
        assertNull(response.getCalculatePremiumResult());
        assertEquals(response.getValidationErrors().size(), 1);
        assertEquals(response.getValidationErrors().get(0), validationError);
    }
    @Test
    public void returnResponseWithoutErrors(){
        Policy policy = mock(Policy.class);
        when(policyValidation.validate(policy)).thenReturn(List.of());
        PremiumResult result = mock(PremiumResult.class);
        when(premiumCalculator.calculate(policy)).thenReturn(result);
        CalculatePremiumResponse response = service.calculatePremium(policy);
        assertNull(response.getValidationErrors());
        assertEquals(response.getCalculatePremiumResult(), result);
    }
}
