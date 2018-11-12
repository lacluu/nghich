package vn.elca.training.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import vn.elca.training.dto.ProjectDTO;
import vn.elca.training.dto.page.ActionResult;
import vn.elca.training.dto.page.PageListProjectDTO;
import vn.elca.training.exception.ConcurrencyUpdateException;
import vn.elca.training.exception.InvalidStatusOfProjectDeleteException;
import vn.elca.training.exception.NotEnoughEmployeeToCreateNewGroupException;
import vn.elca.training.exception.ProjectNumberAlreadyExistsException;
import vn.elca.training.exception.VisaNotExistedException;
import vn.elca.training.service.ProjectService;

@RestController
@RequestMapping(value = "/projects")
public class ProjectApiController {

    @Autowired
    private ProjectService projectService;
    
    /**
     * User can filter the project with search criteria: Name, Number, Customer, and Status The search result is sorted
     * by project number ascending
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    PageListProjectDTO search(@RequestParam String name, @RequestParam String status, Locale locale,
            @RequestParam(required = false, defaultValue = "0") int page, HttpServletRequest request) {
        
        HttpSession session = request.getSession();
        session.setAttribute("searchName", name);
        session.setAttribute("searchStatus", status);
        
        PageListProjectDTO pageListProjectDTO = projectService.findProject(name, status, page, locale);
        
        return pageListProjectDTO;
    }
    
    /**
     * Perform deletion single project
     */
    @RequestMapping(value = "/deleteSingleProject", method = RequestMethod.DELETE)
    @ResponseBody
    ActionResult<ProjectDTO> deleteSingleProject(@RequestBody ProjectDTO projectDTO, Locale locale) {
        ActionResult<ProjectDTO> actionResult = new ActionResult<>();
        try {
            projectService.delete(projectDTO, locale);
            actionResult.setSuccess(true);
        } catch (ConcurrencyUpdateException | InvalidStatusOfProjectDeleteException e) {
            actionResult.setErrorType(e.getClass().getSimpleName());
            actionResult.setErrorMessage(e.getMessage());
            actionResult.setSuccess(false);
        }
        return actionResult;
    }
    
    /**
     * Perform deletion multiple project
     */
    @RequestMapping(value = "/deleteMultipleProject", method = RequestMethod.DELETE)
    @ResponseBody
    public ActionResult<ProjectDTO> deleteMultipleProject(@RequestBody List<ProjectDTO> projectDTOs, Locale locale) {
        ActionResult<ProjectDTO> actionResult = new ActionResult<>();
        try {
            projectService.deletes(projectDTOs, locale);
            actionResult.setStatus("Delete projects success!");
            actionResult.setSuccess(true);
        } catch (ConcurrencyUpdateException | InvalidStatusOfProjectDeleteException e) {
            actionResult.setErrorType(e.getClass().getSimpleName());
            actionResult.setErrorMessage(e.getMessage());
            actionResult.setSuccess(false);
        }
        return actionResult;
    }

    /**
     * Perform find project by id
     */
    @RequestMapping(value = "/findProjectById", method = RequestMethod.GET)
    public ActionResult<ProjectDTO> findProjectById(@RequestParam Long id, Locale locale) {
        ActionResult<ProjectDTO> actionResult = new ActionResult<>();
        ProjectDTO createResult = projectService.findById(id, locale);
        if (createResult == null) {
            actionResult.setSuccess(false);
            throw new RuntimeException();
        } else {
            actionResult.setItem(createResult);
            actionResult.setSuccess(true);
        }
        return actionResult;
    }
    
    @InitBinder
    public void customizeBinding (WebDataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        dateFormatter.setLenient(false);
        binder.registerCustomEditor(Date.class, "startDate", new CustomDateEditor(dateFormatter, true));
    }
    
    /**
     * Perform save or update project
     */
    @RequestMapping(value = "/createOrUpdateProject", method = RequestMethod.POST)
    @ResponseBody
    public ActionResult<ProjectDTO> createOrUpdateProject(@Valid @RequestBody ProjectDTO projectDTO, BindingResult bindingResult, Locale locale) {
        
        ActionResult<ProjectDTO> actionResult = new ActionResult<>();
       
        if(!bindingResult.hasErrors()) {
            try {
                ProjectDTO createResult = projectService.save(projectDTO, locale);
                
                actionResult.setItem(createResult);
                actionResult.setStatus("Success");
                actionResult.setSuccess(true);
            } catch (ProjectNumberAlreadyExistsException | NotEnoughEmployeeToCreateNewGroupException | VisaNotExistedException | ConcurrencyUpdateException e) {
                actionResult.setErrorMessage(e.getMessage());
                actionResult.setErrorType(e.getClass().getSimpleName());
                actionResult.setSuccess(false);
            } 
        }else {
             actionResult.setSuccess(false);
        }
       
        return actionResult;
    }

}
