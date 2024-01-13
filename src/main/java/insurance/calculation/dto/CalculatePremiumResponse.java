package insurance.calculation.dto;

import insurance.calculation.core.dto.CalculatePremiumResult;
import insurance.calculation.core.dto.ErrorDTO;

import java.util.List;

public class CalculatePremiumResponse {
    private List<ErrorDTO> validationErrors;
    private CalculatePremiumResult calculatePremiumResult;

    public CalculatePremiumResponse(List<ErrorDTO> validationErrors) {
        this.validationErrors = validationErrors;
    }
    public CalculatePremiumResponse(CalculatePremiumResult premiumResult){
        this.calculatePremiumResult = premiumResult;
    }

    public CalculatePremiumResult getCalculatePremiumResult() {
        return calculatePremiumResult;
    }

    public List<ErrorDTO> getValidationErrors() {
        return validationErrors;
    }
}
