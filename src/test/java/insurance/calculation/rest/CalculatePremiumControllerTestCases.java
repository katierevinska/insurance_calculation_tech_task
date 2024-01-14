package insurance.calculation.rest;

import org.junit.jupiter.api.Test;

public class CalculatePremiumControllerTestCases extends CalculatePremiumControllerTest{
    @Test
    public void testCase1() throws Exception {
        equalsJsonFiles("EMPTY_NUMBER_test_case");
    }
    @Test
    public void testCase2() throws Exception {
        equalsJsonFiles("EMPTY_OBJECT_LIST_test_case");
    }
    @Test
    public void testCase3() throws Exception {
        equalsJsonFiles("EMPTY_OBJECT_NAME_test_case");
    }
    @Test
    public void testCase4() throws Exception {
        equalsJsonFiles("EMPTY_STATUS_test_case");
    }
    @Test
    public void testCase5() throws Exception {
        equalsJsonFiles("EMPTY_SUB_OBJECT_COST_test_case");
    }
    @Test
    public void testCase6() throws Exception {
        equalsJsonFiles("EMPTY_SUB_OBJECT_NAME_test_case");
    }
    @Test
    public void testCase7() throws Exception {
        equalsJsonFiles("EMPTY_SUB_OBJECT_RISK_TYPE_test_case");
    }
    @Test
    public void testCase8() throws Exception {
        equalsJsonFiles("NO_ERRORS_test_case_1");
    }
    @Test
    public void testCase9() throws Exception {
        equalsJsonFiles("NO_ERRORS_test_case_2");
    }

}
