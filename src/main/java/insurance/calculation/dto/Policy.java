package insurance.calculation.dto;

import java.util.ArrayList;
import java.util.List;

public class Policy {
   private String policyNumber;
   private String status;
   private List<PolicyObject> policyObjects;

   public List<PolicyObject> getPolicyObjects() {
      return policyObjects;
   }

   public String getPolicyNumber() {
      return policyNumber;
   }

   public String getStatus() {
      return status;
   }
}
