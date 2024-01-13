package insurance.calculation.core.services;

import insurance.calculation.core.dto.CalculatePremiumResult;
import insurance.calculation.core.dto.ErrorDTO;
import insurance.calculation.core.dto.RiskDTO;
import insurance.calculation.core.services.calculators.RiskPremiumCalculator;
import insurance.calculation.core.validators.PolicyValidation;
import insurance.calculation.dto.CalculatePremiumResponse;
import insurance.calculation.dto.Policy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
class CalculatePremiumServiceImpl implements CalculatePremiumService {
    @Autowired
    private PolicyValidation policyValidation;
    @Autowired
    private PremiumCalculator premiumCalculator;

    @Override
    public CalculatePremiumResponse calculatePremium(Policy policy) {
        List<ErrorDTO> errorDTOList = policyValidation.validate(policy);
        if (!errorDTOList.isEmpty()) {
            return new CalculatePremiumResponse(errorDTOList);
        }
        return new CalculatePremiumResponse(premiumCalculator.calculate(policy));
    }


}
