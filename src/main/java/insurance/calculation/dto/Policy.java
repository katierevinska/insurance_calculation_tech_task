package insurance.calculation.dto;

import java.util.List;

public class Policy {
   private final String policyNumber;
   private final String status;
   private final List<PolicyObject> policyObjects;

   public Policy(String policyNumber, String status, List<PolicyObject> policyObjects){
      this.policyNumber = policyNumber;
      this.status = status;
      this.policyObjects = policyObjects;
   }

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
