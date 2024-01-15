package insurance.calculation.core.calculation;

import insurance.calculation.dto.PremiumResult;
import insurance.calculation.dto.RiskPremium;
import insurance.calculation.core.calculation.calculators.RiskSumInsuredCalculator;
import insurance.calculation.dto.Policy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PremiumCalculator {
    @Autowired
    private List<RiskSumInsuredCalculator> riskSumInsuredCalculators;

    public PremiumResult calculate(Policy policy){
        List<RiskPremium> riskPremiumList = riskSumInsuredCalculators.stream()
                .map(calculator -> calculateRiskDTO(policy, calculator))
                .collect(Collectors.toList());

        BigDecimal totalPremium = riskPremiumList.stream()
                        .map(this::calculateRiskPremium)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                        .setScale(2, RoundingMode.HALF_UP)
                        .stripTrailingZeros();

        return new PremiumResult(policy.getPolicyNumber(), riskPremiumList, totalPremium);
    }
    private BigDecimal calculateRiskPremium(RiskPremium riskPremium){
        return riskPremium.getRiskSumInsured()
                .multiply(riskPremium.getRiskCoefficient())
                .setScale(2, RoundingMode.HALF_UP);
    }
    private RiskPremium calculateRiskDTO(Policy policy, RiskSumInsuredCalculator calculator){
        BigDecimal sumInsured = calculator.calculateRiskSumInsured(policy);
        return new RiskPremium(calculator.getRiskName(), sumInsured, calculator.getCoefficientByCost(sumInsured));
    }
}
