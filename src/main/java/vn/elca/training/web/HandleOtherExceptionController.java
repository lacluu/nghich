package vn.elca.training.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandleOtherExceptionController {

    @ExceptionHandler(RuntimeException.class)
    public String handleCourseNotFoundException(RuntimeException e) {
        e.printStackTrace();
        return "error-page";
    }
}
