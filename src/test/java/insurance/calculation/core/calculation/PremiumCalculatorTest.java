package insurance.calculation.core.calculation;

import insurance.calculation.core.calculation.calculators.RiskPremiumCalculator;
import insurance.calculation.dto.Policy;
import insurance.calculation.dto.PremiumResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PremiumCalculatorTest {
    @InjectMocks
    private PremiumCalculator premiumCalculator;

    @Test
    public void testCalculationPremiumOneCalculator() {
        Policy policy = mock(Policy.class);
        RiskPremiumCalculator calculator = mock(RiskPremiumCalculator.class);
        ReflectionTestUtils.setField(premiumCalculator, "riskPremiumCalculators",
                List.of(calculator));
        when(calculator.getRiskName()).thenReturn("RISK_NAME");
        when(calculator.calculateRiskPremium(policy)).thenReturn(BigDecimal.valueOf(100.0));
        when(calculator.getCoefficientByCost(BigDecimal.valueOf(100.0))).thenReturn(BigDecimal.valueOf(0.05));

        PremiumResult result = premiumCalculator.calculate(policy);
        assertEquals(result.getTotalPremium(), BigDecimal.valueOf(5));
        assertEquals(result.getRiskDTOS().size(), 1);

        assertEquals(result.getRiskDTOS().get(0).getRiskCoefficient(), BigDecimal.valueOf(0.05));
        assertEquals(result.getRiskDTOS().get(0).getRiskPremium(), BigDecimal.valueOf(100.00));
        assertEquals(result.getRiskDTOS().get(0).getRiskName(), "RISK_NAME");
    }
    @Test
    public void testCalculationPremiumTwoCalculator() {
        Policy policy = mock(Policy.class);
        RiskPremiumCalculator calculator1 = mock(RiskPremiumCalculator.class);
        RiskPremiumCalculator calculator2 = mock(RiskPremiumCalculator.class);

        ReflectionTestUtils.setField(premiumCalculator, "riskPremiumCalculators",
                List.of(calculator1, calculator2));
        when(calculator1.calculateRiskPremium(policy)).thenReturn(BigDecimal.valueOf(100.0));
        when(calculator1.getCoefficientByCost(BigDecimal.valueOf(100.0))).thenReturn(BigDecimal.valueOf(0.05));

        when(calculator2.calculateRiskPremium(policy)).thenReturn(BigDecimal.valueOf(80.0));
        when(calculator2.getCoefficientByCost(BigDecimal.valueOf(80.0))).thenReturn(BigDecimal.valueOf(0.4));

        PremiumResult result = premiumCalculator.calculate(policy);
        assertEquals(result.getTotalPremium(), BigDecimal.valueOf(37));
        assertEquals(result.getRiskDTOS().size(), 2);
    }
}
