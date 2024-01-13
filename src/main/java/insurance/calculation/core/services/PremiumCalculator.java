package insurance.calculation.core.services;

import insurance.calculation.core.dto.CalculatePremiumResult;
import insurance.calculation.core.dto.RiskDTO;
import insurance.calculation.core.services.calculators.RiskPremiumCalculator;
import insurance.calculation.dto.CalculatePremiumResponse;
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

    public CalculatePremiumResult calculate(Policy policy){
        List<RiskDTO> riskDTOList = riskPremiumCalculators.stream()
                .map(calculator -> calculateRiskDTO(policy, calculator))
                .collect(Collectors.toList());
        BigDecimal totalPremium = riskDTOList.stream()
                        .map(this::calculatePremiumWithCoefficient)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                        .setScale(2, RoundingMode.HALF_UP)
                        .stripTrailingZeros();
        return new CalculatePremiumResult(totalPremium, riskDTOList);
    }
    private BigDecimal calculatePremiumWithCoefficient(RiskDTO riskDTO){
        return riskDTO.getRiskPremium()
                .multiply(riskDTO.getRiskCoefficient())
                .setScale(2, RoundingMode.HALF_UP);
    }
    private RiskDTO calculateRiskDTO(Policy policy, RiskPremiumCalculator calculator){
        BigDecimal premium = calculator.calculateRiskPremium(policy);
        return new RiskDTO(calculator.getRiskName(), premium, calculator.getCoefficientByCost(premium));
    }
}
