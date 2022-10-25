package exeptions;

public class FailedToLoginException extends Exception{
    public FailedToLoginException(String message) {
        super(message);
    }
}
