package insurance.calculation.core.calculation.calculators;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class PremiumCalculatorTheftRisk extends RiskPremiumCalculator {
    @Override
    public String getRiskName() {
        return "THEFT";
    }

    @Override
    public BigDecimal getCoefficientByCost(BigDecimal insuranceCost) {
        if(insuranceCost.compareTo(BigDecimal.valueOf(15)) < 0){
            return BigDecimal.valueOf(0.11);
        }
        return BigDecimal.valueOf(0.05);
    }
}
