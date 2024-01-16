package insurance.calculation.calculators.risk_calculators;

import insurance.calculation.dto.Policy;
import insurance.calculation.dto.PolicyObject;
import insurance.calculation.dto.PolicySubObject;
import org.junit.jupiter.api.BeforeEach;
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
public class SumInsuredCalculatorFireRiskTest {
    @InjectMocks
    private SumInsuredCalculatorFireRisk calculatorFireRisk;
    @BeforeEach
    public void setProperties(){
        ReflectionTestUtils.setField(calculatorFireRisk, "riskCoefficient1", BigDecimal.valueOf(0.014));
        ReflectionTestUtils.setField(calculatorFireRisk, "riskCoefficient2", BigDecimal.valueOf(0.024));
        ReflectionTestUtils.setField(calculatorFireRisk, "riskThreshold", BigDecimal.valueOf(100));
    }

    @Test
    public void testCalculateRiskSumInsuranceSubObject1() {
        Policy policy = mock(Policy.class);

        PolicySubObject policySubObject1 = mock(PolicySubObject.class);
        when(policySubObject1.getInsuranceCost()).thenReturn(BigDecimal.valueOf(100.00));
        when(policySubObject1.getRiskType()).thenReturn("FIRE");

        PolicySubObject policySubObject2 = mock(PolicySubObject.class);
        when(policySubObject2.getRiskType()).thenReturn("THEFT");

        PolicyObject policyObject = mock(PolicyObject.class);
        when(policyObject.getSubObjects()).thenReturn(List.of(policySubObject1, policySubObject2));
        when(policy.getPolicyObjects()).thenReturn(List.of(policyObject));

        assertEquals(calculatorFireRisk.calculateRiskSumInsured(policy), BigDecimal.valueOf(100.00));
    }
    @Test
    public void testCalculateRiskSumInsuranceSubObject2() {
        Policy policy = mock(Policy.class);

        PolicySubObject policySubObject1 = mock(PolicySubObject.class);
        when(policySubObject1.getInsuranceCost()).thenReturn(BigDecimal.valueOf(100.00));
        when(policySubObject1.getRiskType()).thenReturn("FIRE");

        PolicySubObject policySubObject2 = mock(PolicySubObject.class);
        when(policySubObject2.getInsuranceCost()).thenReturn(BigDecimal.valueOf(20.00));
        when(policySubObject2.getRiskType()).thenReturn("FIRE");

        PolicyObject policyObject = mock(PolicyObject.class);
        when(policyObject.getSubObjects()).thenReturn(List.of(policySubObject1, policySubObject2));
        when(policy.getPolicyObjects()).thenReturn(List.of(policyObject));

        assertEquals(calculatorFireRisk.calculateRiskSumInsured(policy), BigDecimal.valueOf(120.00));
    }
    @Test
    public void testCalculateRiskSumInsuranceSubObject3() {
        Policy policy = mock(Policy.class);

        PolicySubObject policySubObject1 = mock(PolicySubObject.class);
        when(policySubObject1.getInsuranceCost()).thenReturn(BigDecimal.valueOf(100.00));
        when(policySubObject1.getRiskType()).thenReturn("FIRE");

        PolicySubObject policySubObject2 = mock(PolicySubObject.class);
        when(policySubObject2.getInsuranceCost()).thenReturn(BigDecimal.valueOf(20.00));
        when(policySubObject2.getRiskType()).thenReturn("FIRE");

        PolicyObject policyObject1 = mock(PolicyObject.class);
        PolicyObject policyObject2 = mock(PolicyObject.class);
        when(policyObject1.getSubObjects()).thenReturn(List.of(policySubObject1));
        when(policyObject2.getSubObjects()).thenReturn(List.of(policySubObject2));

        when(policy.getPolicyObjects()).thenReturn(List.of(policyObject1, policyObject2));

        assertEquals(calculatorFireRisk.calculateRiskSumInsured(policy), BigDecimal.valueOf(120.00));
    }
    @Test
    public void testGetCoefficient() {
        assertEquals(calculatorFireRisk.getCoefficientByCost(BigDecimal.valueOf(90)), BigDecimal.valueOf(0.014));
        assertEquals(calculatorFireRisk.getCoefficientByCost(BigDecimal.valueOf(100)), BigDecimal.valueOf(0.014));
        assertEquals(calculatorFireRisk.getCoefficientByCost(BigDecimal.valueOf(110)), BigDecimal.valueOf(0.024));
    }
}
