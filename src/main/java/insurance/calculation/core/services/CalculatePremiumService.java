package insurance.calculation.core.services;

import insurance.calculation.dto.CalculatePremiumResponse;
import insurance.calculation.dto.Policy;

public interface CalculatePremiumService {

    CalculatePremiumResponse calculatePremium(Policy policy);

}
