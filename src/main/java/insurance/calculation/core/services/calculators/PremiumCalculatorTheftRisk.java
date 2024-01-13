package insurance.calculation.core.services.calculators;

import insurance.calculation.dto.Policy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class PremiumCalculatorTheftRisk extends RiskPremiumCalculator {
    @Override
    public String getRiskName() {
        return "THEFT";
    }
}
