package insurance.calculation.calculators.risk_calculators;

import insurance.calculation.dto.Policy;
import insurance.calculation.dto.PolicySubObject;

import java.math.BigDecimal;

public abstract class RiskSumInsuredCalculator {

    public BigDecimal calculateRiskSumInsured(Policy policy) {
        return policy.getPolicyObjects().stream()
                .flatMap(obj->obj.getSubObjects().stream())
                .filter(subObj->subObj.getRiskType().equals(getRiskName()))
                .map(PolicySubObject::getInsuranceCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    public abstract String getRiskName();
    public abstract BigDecimal getCoefficientByCost(BigDecimal insuranceCost);

}
