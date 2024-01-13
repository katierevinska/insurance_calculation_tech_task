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
        List<RiskDTO> riskDTOList = calculateRiskDTOS(policy);
        BigDecimal totalPremium = riskDTOList.stream()
                        .map(RiskDTO::getRiskPremium)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                        .setScale(2, RoundingMode.HALF_UP)
                        .stripTrailingZeros();
        return new CalculatePremiumResult(totalPremium, riskDTOList);
    }
    private List<RiskDTO> calculateRiskDTOS(Policy policy){
        return riskPremiumCalculators.stream()
                .map(calculator ->
                        new RiskDTO(calculator.getRiskName(), calculator.calculateRiskPremium(policy)))
                .collect(Collectors.toList());

    }
}
