package vn.elca.training.exception;

public class ConcurrencyUpdateException extends Exception {
    private static final long serialVersionUID = 1L;
    public ConcurrencyUpdateException(String message) {
        super(message);
    }
    public ConcurrencyUpdateException(String message, Throwable e) {
        super(message, e);
    }
}
