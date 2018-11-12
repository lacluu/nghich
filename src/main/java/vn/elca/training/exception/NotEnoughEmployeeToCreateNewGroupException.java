package vn.elca.training.exception;

public class NotEnoughEmployeeToCreateNewGroupException extends Exception {
    private static final long serialVersionUID = 1L;
    
    public NotEnoughEmployeeToCreateNewGroupException(String message) {
        super(message);
    }

}
