package insurance.calculation.dto;

import java.util.ArrayList;

public class PolicyObject {
    private String objectName;
    private ArrayList<PolicySubObject> subObjects;

    public String getObjectName() {
        return objectName;
    }

    public ArrayList<PolicySubObject> getSubObjects() {
        return subObjects;
    }
}
