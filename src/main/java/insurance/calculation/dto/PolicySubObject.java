package insurance.calculation.dto;

import java.math.BigDecimal;

public class PolicySubObject {
    private String subObjectName;
    private BigDecimal insuranceCost;
    private String riskType;

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
