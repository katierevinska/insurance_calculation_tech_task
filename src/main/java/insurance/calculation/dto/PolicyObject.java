package insurance.calculation.dto;

import java.util.List;

public class PolicyObject {
    private String objectName;
    private List<PolicySubObject> subObjects;

    public String getObjectName() {
        return objectName;
    }

    public List<PolicySubObject> getSubObjects() {
        return subObjects;
    }
}
