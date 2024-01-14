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
public class PremiumCalculatorTheftRiskTest {
    @InjectMocks
    private PremiumCalculatorTheftRisk calculatorTheftRisk;

    @Test
    public void testCalculationPremium() {
        Policy policy = mock(Policy.class);
        PolicySubObject policySubObject1 = mock(PolicySubObject.class);
        when(policySubObject1.getInsuranceCost()).thenReturn(BigDecimal.valueOf(100.00));
        when(policySubObject1.getRiskType()).thenReturn("THEFT");
        PolicySubObject policySubObject2 = mock(PolicySubObject.class);
        when(policySubObject2.getRiskType()).thenReturn("FIRE");

        PolicyObject policyObject = mock(PolicyObject.class);
        when(policyObject.getSubObjects()).thenReturn(List.of(policySubObject1, policySubObject2));
        when(policy.getPolicyObjects()).thenReturn(List.of(policyObject));
        assertEquals(calculatorTheftRisk.calculateRiskPremium(policy), BigDecimal.valueOf(100.00));

    }
    @Test
    public void testGetCoefficient() {
        assertEquals(calculatorTheftRisk.getCoefficientByCost(BigDecimal.valueOf(10)), BigDecimal.valueOf(0.11));
        assertEquals(calculatorTheftRisk.getCoefficientByCost(BigDecimal.valueOf(15)), BigDecimal.valueOf(0.05));
        assertEquals(calculatorTheftRisk.getCoefficientByCost(BigDecimal.valueOf(30)), BigDecimal.valueOf(0.05));
    }
}
