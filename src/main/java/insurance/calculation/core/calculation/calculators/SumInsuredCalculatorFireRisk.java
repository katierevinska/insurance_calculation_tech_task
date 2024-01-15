package insurance.calculation.core.calculation.calculators;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class SumInsuredCalculatorFireRisk extends RiskSumInsuredCalculator {
    @Override
    public String getRiskName() {
        return "FIRE";
    }
    @Override
    public BigDecimal getCoefficientByCost(BigDecimal insuranceCost) {
        if(insuranceCost.compareTo(BigDecimal.valueOf(100)) <= 0){
            return BigDecimal.valueOf(0.014);
        }
        return BigDecimal.valueOf(0.024);
    }
}
