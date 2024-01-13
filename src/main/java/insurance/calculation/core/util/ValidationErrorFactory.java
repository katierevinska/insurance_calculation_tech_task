package insurance.calculation.core.util;

import insurance.calculation.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@PropertySource("classpath:errorCodes.properties")
public class ValidationErrorFactory {
    @Autowired
    Environment environment;

    public ValidationError errorByCode(String errorCode) {
        return new ValidationError(errorCode,
                environment.getProperty(errorCode, "description is missing"));
    }

    public ValidationError errorByCode(String errorCode, List<Placeholder> placeholders) {
        String description = environment.getProperty(errorCode, "description is missing");
        placeholders.forEach(placeholder ->
                description.replace("{" + placeholder.getName() + "}", placeholder.getValue()));
        return new ValidationError(errorCode, description);
    }
}
