package insurance.calculation.dto;

import java.math.BigDecimal;

public class RiskPremium {
    private final String riskName;
    private final BigDecimal riskPremium;

    private final BigDecimal riskCoefficient;

    public String getRiskName() {
        return riskName;
    }

    public BigDecimal getRiskPremium() {
        return riskPremium;
    }

    public BigDecimal getRiskCoefficient() {
        return riskCoefficient;
    }

    public RiskPremium(String riskName, BigDecimal riskPremium, BigDecimal riskCoefficient){
        this.riskName = riskName;
        this.riskPremium = riskPremium;
        this.riskCoefficient = riskCoefficient;
    }

}
