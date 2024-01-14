package insurance.calculation.core.calculation.calculators;

import insurance.calculation.dto.Policy;
import insurance.calculation.dto.PolicyObject;
import insurance.calculation.dto.PolicySubObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PremiumCalculatorFireRiskTest {
    @InjectMocks
    PremiumCalculatorFireRisk calculatorFireRisk;

    @Test
    public void testCalculationPremium() {
        Policy policy = mock(Policy.class);
        PolicySubObject policySubObject1 = mock(PolicySubObject.class);
        when(policySubObject1.getInsuranceCost()).thenReturn(BigDecimal.valueOf(100.00));
        when(policySubObject1.getRiskType()).thenReturn("FIRE");
        PolicySubObject policySubObject2 = mock(PolicySubObject.class);
        when(policySubObject2.getRiskType()).thenReturn("THEFT");

        PolicyObject policyObject = mock(PolicyObject.class);
        when(policyObject.getSubObjects()).thenReturn(List.of(policySubObject1, policySubObject2));
        when(policy.getPolicyObjects()).thenReturn(List.of(policyObject));
        assertEquals(calculatorFireRisk.calculateRiskPremium(policy), BigDecimal.valueOf(100.00));

    }
    @Test
    public void testGetCoefficient() {
        assertEquals(calculatorFireRisk.getCoefficientByCost(BigDecimal.valueOf(90)), BigDecimal.valueOf(0.014));
        assertEquals(calculatorFireRisk.getCoefficientByCost(BigDecimal.valueOf(100)), BigDecimal.valueOf(0.014));
        assertEquals(calculatorFireRisk.getCoefficientByCost(BigDecimal.valueOf(110)), BigDecimal.valueOf(0.024));
    }
}
