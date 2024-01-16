package insurance.calculation.calculators;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import insurance.calculation.dto.Policy;
import insurance.calculation.dto.PremiumResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.nio.file.Files;
import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;

@SpringBootTest
public abstract class PremiumCalculatorTest {
    @Autowired
    private PremiumCalculatorImpl premiumCalculatorImpl;
    void compareResponse(String testCase) throws JsonProcessingException {
        String requestFile = testCase + "/request.json";
        String responseFile = testCase + "/response.json";
        ObjectMapper mapper = new ObjectMapper();
        Policy policy = mapper.readValue(parseJSONIntoString(requestFile), Policy.class);
        PremiumResult result = premiumCalculatorImpl.calculate(policy);
        assertJson(mapper.readTree(mapper.writeValueAsString(result)))
                .where()
                .keysInAnyOrder()
                .arrayInAnyOrder()
                .isEqualTo(parseJSONIntoString(responseFile));
    }
    private String parseJSONIntoString(String filePath) {
        try {
            File file = ResourceUtils.getFile("classpath:" + filePath);
            return new String(Files.readAllBytes(file.toPath()));
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            throw new RuntimeException();
        }
    }
}
