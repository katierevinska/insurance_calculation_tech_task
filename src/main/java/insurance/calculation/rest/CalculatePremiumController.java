package insurance.calculation.rest;

import insurance.calculation.core.services.CalculatePremiumService;
import insurance.calculation.dto.CalculatePremiumResponse;
import insurance.calculation.dto.Policy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insurance/calculate")
public class CalculatePremiumController {

	@Autowired private CalculatePremiumService calculatePremiumService;

	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public CalculatePremiumResponse calculatePremium(@RequestBody Policy policy) {
		return calculatePremiumService.calculatePremium(policy);
	}

}