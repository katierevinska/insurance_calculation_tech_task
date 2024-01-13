package insurance.calculation.core.validators;

import insurance.calculation.core.dto.ErrorDTO;
import insurance.calculation.dto.Policy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PolicyValidation {
    public List<ErrorDTO> validate(Policy policy){
        return List.of();
    }
}
