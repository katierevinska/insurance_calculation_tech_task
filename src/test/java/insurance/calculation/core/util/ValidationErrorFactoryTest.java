package insurance.calculation.core.util;

import insurance.calculation.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ValidationErrorFactoryTest {
    @InjectMocks
    private ValidationErrorFactory errorFactory;
    @Mock
    private Environment environment;
    @Test
    public void returnErrorByCode(){
        when(environment.getProperty(eq("ERROR_CODE"))).thenReturn("error description");
        assertEquals(errorFactory.errorByCode("ERROR_CODE").getErrorCode(), "ERROR_CODE");
        assertEquals(errorFactory.errorByCode("ERROR_CODE").getErrorDescription(), "error description");
    }
    @Test
    public void returnErrorByCodeAndPlaceholders(){
        when(environment.getProperty(eq("ERROR_CODE"))).thenReturn("{NAME_1} {NAME_2}");
        ValidationError error = errorFactory.errorByCode("ERROR_CODE", List.of(new Placeholder("NAME_1", "VALUE_1"),
                new Placeholder("NAME_2", "VALUE_2")));
        assertEquals(error.getErrorCode(), "ERROR_CODE");
        assertEquals(error.getErrorDescription(), "VALUE_1 VALUE_2");

    }
}
