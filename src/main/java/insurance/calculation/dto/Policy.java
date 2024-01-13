package insurance.calculation.dto;

import java.util.ArrayList;

public class Policy {
   private String policyNumber;
   private String status;
   private ArrayList<PolicyObject> policyObjects;

   public ArrayList<PolicyObject> getPolicyObjects() {
      return policyObjects;
   }

   public String getPolicyNumber() {
      return policyNumber;
   }

   public String getStatus() {
      return status;
   }
}
