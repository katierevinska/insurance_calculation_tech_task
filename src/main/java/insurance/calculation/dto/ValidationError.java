package insurance.calculation.dto;

public class ValidationError {
    private final String errorDescription;
    private final String errorCode;
    public ValidationError(String errorCode, String errorDescription){
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public String getErrorCode() {
        return errorCode;
    }
    public String getErrorDescription() {
        return errorDescription;
    }
}
