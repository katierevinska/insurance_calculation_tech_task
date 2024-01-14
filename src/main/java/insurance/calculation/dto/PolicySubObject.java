package insurance.calculation.dto;

import java.math.BigDecimal;

public class PolicySubObject {
    private final String subObjectName;
    private final BigDecimal insuranceCost;
    private final String riskType;

    public PolicySubObject(String subObjectName,BigDecimal insuranceCost, String riskType){
        this.subObjectName = subObjectName;
        this.insuranceCost = insuranceCost;
        this.riskType = riskType;
    }

    public String getSubObjectName() {
        return subObjectName;
    }

    public BigDecimal getInsuranceCost() {
        return insuranceCost;
    }

    public String getRiskType() {
        return riskType;
    }
}
