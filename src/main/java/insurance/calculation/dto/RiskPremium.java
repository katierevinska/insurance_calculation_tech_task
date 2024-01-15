package insurance.calculation.dto;

import java.math.BigDecimal;

public class RiskPremium {
    private final String riskName;
    private final BigDecimal riskSumInsured;

    private final BigDecimal riskCoefficient;

    public String getRiskName() {
        return riskName;
    }

    public BigDecimal getRiskSumInsured() {
        return riskSumInsured;
    }

    public BigDecimal getRiskCoefficient() {
        return riskCoefficient;
    }

    public RiskPremium(String riskName, BigDecimal riskSumInsured, BigDecimal riskCoefficient){
        this.riskName = riskName;
        this.riskSumInsured = riskSumInsured;
        this.riskCoefficient = riskCoefficient;
    }

}
