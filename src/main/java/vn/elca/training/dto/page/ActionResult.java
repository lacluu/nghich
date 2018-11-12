package vn.elca.training.dto.page;

public class ActionResult<T> {
    private T item;
    private boolean success;
    private String status;
    private String errorMessage;
    private String errorType;

    public ActionResult() {
    }

    public ActionResult(boolean success) {
        super();
        this.success = success;
    }

    public ActionResult(T item, boolean success) {
        super();
        this.item = item;
        this.success = success;
    }

    public ActionResult(T item, boolean success, String status) {
        super();
        this.item = item;
        this.success = success;
        this.status = status;
    }

    public ActionResult(boolean success, String errorMessage) {
        super();
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }
    
}
