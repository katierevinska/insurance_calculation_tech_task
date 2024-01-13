package insurance.calculation.core.dto;

import java.math.BigDecimal;

public class RiskDTO {
    private String riskName;
    private BigDecimal riskPremium;

    private BigDecimal riskCoefficient;


    public RiskDTO(String riskName, BigDecimal riskPremium, BigDecimal riskCoefficient) {
        this.riskName = riskName;
        this.riskPremium = riskPremium;
        this.riskCoefficient = riskCoefficient;
    }

    public String getRiskName() {
        return riskName;
    }

    public BigDecimal getRiskPremium() {
        return riskPremium;
    }

    public BigDecimal getRiskCoefficient() {
        return riskCoefficient;
    }

    public void setRiskName(String riskName) {
        this.riskName = riskName;
    }

    public void setRiskPremium(BigDecimal riskPremium) {
        this.riskPremium = riskPremium;
    }

    public void setRiskCoefficient(BigDecimal riskCoefficient) {
        this.riskCoefficient = riskCoefficient;
    }
}
