package insurance.calculation.core.calculation;

import insurance.calculation.dto.PremiumResult;
import insurance.calculation.dto.RiskPremium;
import insurance.calculation.core.calculation.calculators.RiskPremiumCalculator;
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
    private List<RiskPremiumCalculator> riskPremiumCalculators;

    public PremiumResult calculate(Policy policy){
        List<RiskPremium> riskPremiumList = riskPremiumCalculators.stream()
                .map(calculator -> calculateRiskDTO(policy, calculator))
                .collect(Collectors.toList());

        BigDecimal totalPremium = riskPremiumList.stream()
                        .map(this::calculatePremiumWithCoefficient)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                        .setScale(2, RoundingMode.HALF_UP)
                        .stripTrailingZeros();

        return new PremiumResult(policy.getPolicyNumber(), riskPremiumList, totalPremium);
    }
    private BigDecimal calculatePremiumWithCoefficient(RiskPremium riskPremium){
        return riskPremium.getRiskPremium()
                .multiply(riskPremium.getRiskCoefficient())
                .setScale(2, RoundingMode.HALF_UP);
    }
    private RiskPremium calculateRiskDTO(Policy policy, RiskPremiumCalculator calculator){
        BigDecimal premium = calculator.calculateRiskPremium(policy);
        return new RiskPremium(calculator.getRiskName(), premium, calculator.getCoefficientByCost(premium));
    }
}
