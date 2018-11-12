package vn.elca.training.exception;

public class ProjectNumberAlreadyExistsException extends Exception {
    private static final long serialVersionUID = 1L;

    public ProjectNumberAlreadyExistsException(String message, Throwable e) {
        super(message, e);
    }
}
