package insurance.calculation.core.services.calculators;

import insurance.calculation.dto.Policy;
import insurance.calculation.dto.PolicyObject;
import insurance.calculation.dto.PolicySubObject;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class PremiumCalculatorFireRisk extends RiskPremiumCalculator{
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
