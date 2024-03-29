package insurance.calculation.calculators;

import insurance.calculation.dto.PremiumResult;
import insurance.calculation.dto.RiskPremium;
import insurance.calculation.calculators.risk_calculators.RiskSumInsuredCalculator;
import insurance.calculation.dto.Policy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Component
class PremiumCalculatorImpl implements PremiumCalculator {
    @Autowired
    private List<RiskSumInsuredCalculator> riskSumInsuredCalculators;

    public PremiumResult calculate(Policy policy) {
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

    private BigDecimal calculateRiskPremium(RiskPremium riskPremium) {
        return riskPremium.getRiskSumInsured()
                .multiply(riskPremium.getRiskCoefficient())
                .setScale(2, RoundingMode.HALF_UP);
    }

    private RiskPremium calculateRiskDTO(Policy policy, RiskSumInsuredCalculator calculator) {
        BigDecimal sumInsured = calculator.calculateRiskSumInsured(policy);
        BigDecimal coefficient = calculator.getCoefficientByCost(sumInsured);
        BigDecimal premium = sumInsured.multiply(coefficient).setScale(2, RoundingMode.HALF_UP);
        return new RiskPremium(calculator.getRiskName(), sumInsured, coefficient, premium);
    }
}
