package insurance.calculation.core.calculation.calculators;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@PropertySource("classpath:risk_coefficients.properties")
class SumInsuredCalculatorFireRisk extends RiskSumInsuredCalculator {
    @Value("${FIRE_RISK_COEFFICIENT_1}")
    private BigDecimal riskCoefficient1;
    @Value("${FIRE_RISK_COEFFICIENT_2}")
    private BigDecimal riskCoefficient2;
    @Value("${FIRE_RISK_THRESHOLD}")
    private BigDecimal riskThreshold;
    @Override
    public String getRiskName() {
        return "FIRE";
    }
    @Override
    public BigDecimal getCoefficientByCost(BigDecimal insuranceCost) {
        return insuranceCost.compareTo(riskThreshold) <= 0
                ? riskCoefficient1 : riskCoefficient2;
    }
}
