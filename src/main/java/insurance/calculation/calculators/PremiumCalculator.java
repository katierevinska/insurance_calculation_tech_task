package insurance.calculation.calculators;

import insurance.calculation.dto.Policy;
import insurance.calculation.dto.PremiumResult;
import org.springframework.stereotype.Component;

public interface PremiumCalculator {
    PremiumResult calculate(Policy policy);

}
