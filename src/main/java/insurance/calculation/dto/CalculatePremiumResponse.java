package insurance.calculation.dto;

import java.util.List;

public class CalculatePremiumResponse {

    private List<ValidationError> validationErrors;
    private PremiumResult premiumResult;

    public CalculatePremiumResponse(List<ValidationError> validationErrors) {
        this.validationErrors = validationErrors;
    }
    public CalculatePremiumResponse(PremiumResult premiumResult){
        this.premiumResult = premiumResult;
    }

    public PremiumResult getCalculatePremiumResult() {
        return premiumResult;
    }

    public List<ValidationError> getValidationErrors() {
        return validationErrors;
    }
}
