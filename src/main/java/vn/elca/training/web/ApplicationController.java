package vn.elca.training.web;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ApplicationController {
    
    @Autowired
    private MessageSource messageSource;

    @RequestMapping("/")
    public ModelAndView main(HttpServletRequest request, Locale locale) {
        ModelAndView mav = new ModelAndView();
        
        mav.setViewName("list-project");
        
        HttpSession session = request.getSession();
        
        String searchName = (String) session.getAttribute("searchName");
        if (searchName != null) {
            mav.addObject("name", searchName);
        }
        
        String searchStatus = (String) session.getAttribute("searchStatus");
        if (session.getAttribute("searchStatus") != null) {
            mav.addObject("status", searchStatus);
        }

        String placeholderSearch =  messageSource.getMessage("list.project.page.textbox.search.hint", null, locale);
        mav.addObject("placeholderSearch", placeholderSearch);
        
        return mav;
    }

    @RequestMapping(value = "/newProject", method = RequestMethod.GET)
    public ModelAndView newProject(Locale locale) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("create-project");
        
        String title = messageSource.getMessage("create.update.page.main.new.project", null, locale);
        mav.addObject("titles", title);

        String titleButtonCreate =  messageSource.getMessage("create.update.page.button.create.project", null, locale);
        mav.addObject("titleButtonSave", titleButtonCreate);
        return mav;
    }

    @RequestMapping(value = "/editProject/{id}", method = RequestMethod.GET)
    public ModelAndView editProject(@PathVariable Long id, Locale locale) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("create-project");
        
        String title = messageSource.getMessage("create.update.page.title.edit.project", null, locale);
        mav.addObject("titles", title);
        
        mav.addObject("pageStatus", "edit");
        mav.addObject("projectId", id);
        
        String titleButton =  messageSource.getMessage("create.update.page.button.edit.project", null, locale);
        mav.addObject("titleButtonSave", titleButton);
        return mav;
    }
  

}
