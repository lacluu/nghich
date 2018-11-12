package vn.elca.training.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {
    
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        return "error-page";
    }
 
    @Override
    public String getErrorPath() {
        return "/error";
    }
    
}
