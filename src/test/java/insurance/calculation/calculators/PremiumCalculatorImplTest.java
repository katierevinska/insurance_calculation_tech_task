package insurance.calculation.calculators;

import insurance.calculation.calculators.risk_calculators.RiskSumInsuredCalculator;
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
public class PremiumCalculatorImplTest {
    @InjectMocks
    private PremiumCalculatorImpl premiumCalculatorImpl;

    @Test
    public void testCalculationPremiumOneCalculator() {
        Policy policy = mock(Policy.class);
        RiskSumInsuredCalculator calculator = mock(RiskSumInsuredCalculator.class);
        when(calculator.getRiskName()).thenReturn("RISK_NAME");
        when(calculator.calculateRiskSumInsured(policy)).thenReturn(BigDecimal.valueOf(100.0));
        when(calculator.getCoefficientByCost(BigDecimal.valueOf(100.0))).thenReturn(BigDecimal.valueOf(0.05));

        ReflectionTestUtils.setField(premiumCalculatorImpl, "riskSumInsuredCalculators",
                List.of(calculator));

        PremiumResult result = premiumCalculatorImpl.calculate(policy);
        assertEquals(result.getTotalPremium(), BigDecimal.valueOf(5));
        assertEquals(result.getRiskPremiums().size(), 1);

        assertEquals(result.getRiskPremiums().get(0).getRiskCoefficient(), BigDecimal.valueOf(0.05));
        assertEquals(result.getRiskPremiums().get(0).getRiskSumInsured(), BigDecimal.valueOf(100.00));
        assertEquals(result.getRiskPremiums().get(0).getRiskPremium().compareTo(BigDecimal.valueOf(5.0)), 0);
        assertEquals(result.getRiskPremiums().get(0).getRiskName(), "RISK_NAME");
    }
    @Test
    public void testCalculationPremiumTwoCalculator1() {
        Policy policy = mock(Policy.class);
        RiskSumInsuredCalculator calculator1 = mock(RiskSumInsuredCalculator.class);
        RiskSumInsuredCalculator calculator2 = mock(RiskSumInsuredCalculator.class);

        when(calculator1.calculateRiskSumInsured(policy)).thenReturn(BigDecimal.valueOf(100.0));
        when(calculator1.getCoefficientByCost(BigDecimal.valueOf(100.0))).thenReturn(BigDecimal.valueOf(0.014));
        when(calculator2.calculateRiskSumInsured(policy)).thenReturn(BigDecimal.valueOf(8.0));
        when(calculator2.getCoefficientByCost(BigDecimal.valueOf(8.0))).thenReturn(BigDecimal.valueOf(0.11));

        ReflectionTestUtils.setField(premiumCalculatorImpl, "riskSumInsuredCalculators",
                List.of(calculator1, calculator2));

        PremiumResult result = premiumCalculatorImpl.calculate(policy);
        assertEquals(result.getTotalPremium(), BigDecimal.valueOf(2.28));
        assertEquals(result.getRiskPremiums().size(), 2);
    }
    @Test
    public void testCalculationPremiumTwoCalculator2() {
        Policy policy = mock(Policy.class);
        RiskSumInsuredCalculator calculator1 = mock(RiskSumInsuredCalculator.class);
        RiskSumInsuredCalculator calculator2 = mock(RiskSumInsuredCalculator.class);

        when(calculator1.calculateRiskSumInsured(policy)).thenReturn(BigDecimal.valueOf(102.51));
        when(calculator1.getCoefficientByCost(BigDecimal.valueOf(102.51))).thenReturn(BigDecimal.valueOf(0.05));
        when(calculator2.calculateRiskSumInsured(policy)).thenReturn(BigDecimal.valueOf(500.0));
        when(calculator2.getCoefficientByCost(BigDecimal.valueOf(500.0))).thenReturn(BigDecimal.valueOf(0.024));

        ReflectionTestUtils.setField(premiumCalculatorImpl, "riskSumInsuredCalculators",
                List.of(calculator1, calculator2));

        PremiumResult result = premiumCalculatorImpl.calculate(policy);
        assertEquals(result.getTotalPremium(), BigDecimal.valueOf(17.13));
        assertEquals(result.getRiskPremiums().size(), 2);
    }
}
