package insurance.calculation.dto;

import java.util.List;

public class PolicyObject {
    private final String objectName;
    private final List<PolicySubObject> subObjects;

    public PolicyObject(String objectName, List<PolicySubObject> subObjects) {
        this.objectName = objectName;
        this.subObjects = subObjects;
    }

    public String getObjectName() {
        return objectName;
    }

    public List<PolicySubObject> getSubObjects() {
        return subObjects;
    }
}
