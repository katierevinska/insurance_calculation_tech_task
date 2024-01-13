package insurance.calculation.core.dto;

import java.math.BigDecimal;
import java.util.List;

public class CalculatePremiumResult {
    private List<RiskDTO> riskDTOS;
    private BigDecimal totalPremium;
    public CalculatePremiumResult(BigDecimal totalPremium, List<RiskDTO> riskDTOS){
        this.totalPremium = totalPremium;
        this.riskDTOS = riskDTOS;
    }

    public BigDecimal getTotalPremium() {
        return totalPremium;
    }

    public List<RiskDTO> getRiskDTOS() {
        return riskDTOS;
    }
}
