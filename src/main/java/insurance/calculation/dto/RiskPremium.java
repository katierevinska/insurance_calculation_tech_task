package insurance.calculation.dto;

import java.math.BigDecimal;

public class RiskPremium {
    private final String riskName;
    private final BigDecimal riskSumInsured;

    private final BigDecimal riskCoefficient;

    private final BigDecimal riskPremium;

    public String getRiskName() {
        return riskName;
    }

    public BigDecimal getRiskSumInsured() {
        return riskSumInsured;
    }

    public BigDecimal getRiskCoefficient() {
        return riskCoefficient;
    }

    public BigDecimal getRiskPremium() {
        return riskPremium;
    }


    public RiskPremium(String riskName, BigDecimal riskSumInsured, BigDecimal riskCoefficient, BigDecimal riskPremium) {
        this.riskName = riskName;
        this.riskSumInsured = riskSumInsured;
        this.riskCoefficient = riskCoefficient;
        this.riskPremium = riskPremium;
    }

}
