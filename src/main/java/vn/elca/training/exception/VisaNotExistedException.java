package vn.elca.training.exception;

public class VisaNotExistedException extends Exception {
    private static final long serialVersionUID = 1L;
    
    public VisaNotExistedException(String message) {
        super(message);
    }
}
