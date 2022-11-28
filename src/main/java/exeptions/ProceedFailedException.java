package exeptions;

public class ProceedFailedException extends RuntimeException {
    public ProceedFailedException(String message) {
        super(message);
    }
}
