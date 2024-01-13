package insurance.calculation.core.services;

import insurance.calculation.core.calculation.PremiumCalculator;
import insurance.calculation.dto.ValidationError;
import insurance.calculation.core.validation.PolicyValidation;
import insurance.calculation.dto.CalculatePremiumResponse;
import insurance.calculation.dto.Policy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CalculatePremiumServiceImpl implements CalculatePremiumService {
    @Autowired
    private PolicyValidation policyValidation;
    @Autowired
    private PremiumCalculator premiumCalculator;

    @Override
    public CalculatePremiumResponse calculatePremium(Policy policy) {
        List<ValidationError> validationErrorList = policyValidation.validate(policy);
        if (!validationErrorList.isEmpty()) {
            return new CalculatePremiumResponse(validationErrorList);
        }
        return new CalculatePremiumResponse(premiumCalculator.calculate(policy));
    }


}
