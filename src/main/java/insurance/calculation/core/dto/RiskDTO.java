package insurance.calculation.core.dto;

import java.math.BigDecimal;

public class RiskDTO {
    private String riskName;
    private BigDecimal riskPremium;

    public RiskDTO(String riskName, BigDecimal riskPremium) {
        this.riskName = riskName;
        this.riskPremium = riskPremium;
    }

    public String getRiskName() {
        return riskName;
    }

    public BigDecimal getRiskPremium() {
        return riskPremium;
    }

    public void setRiskName(String riskName) {
        this.riskName = riskName;
    }

    public void setRiskPremium(BigDecimal riskPremium) {
        this.riskPremium = riskPremium;
    }
}
