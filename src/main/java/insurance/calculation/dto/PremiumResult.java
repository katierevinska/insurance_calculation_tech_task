package insurance.calculation.dto;

import java.math.BigDecimal;
import java.util.List;

public class PremiumResult {

    private final String policyNumber;
    private final List<RiskPremium> riskPremiums;
    private final BigDecimal totalPremium;

    public BigDecimal getTotalPremium() {
        return totalPremium;
    }

    public List<RiskPremium> getRiskPremiums() {
        return riskPremiums;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public PremiumResult(String policyNumber, List<RiskPremium> riskPremiums, BigDecimal totalPremium) {
        this.policyNumber = policyNumber;
        this.riskPremiums = riskPremiums;
        this.totalPremium = totalPremium;
    }

}
