package insurance.calculation.calculators;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

public class PremiumCalculatorTestCases extends PremiumCalculatorTest{
    @Test
    public void test1() throws JsonProcessingException {
        compareResponse("test_case_1");
    }
    @Test
    public void test2() throws JsonProcessingException {
        compareResponse("test_case_2");
    }
}
