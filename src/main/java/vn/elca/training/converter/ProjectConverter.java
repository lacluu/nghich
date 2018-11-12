package vn.elca.training.converter;

import static vn.elca.training.dom.QEmployee.employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import vn.elca.training.dao.EmployeeRepository;
import vn.elca.training.dao.GroupRepository;
import vn.elca.training.dao.ProjectRepository;
import vn.elca.training.dom.Employee;
import vn.elca.training.dom.Group;
import vn.elca.training.dom.Project;
import vn.elca.training.dto.ProjectDTO;
import vn.elca.training.exception.ConcurrencyUpdateException;
import vn.elca.training.exception.NotEnoughEmployeeToCreateNewGroupException;
import vn.elca.training.util.ApplicationUtil;

@Component
public class ProjectConverter {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private MessageSource messageSource;
    
    @PersistenceContext
    private EntityManager em;

    /**
     * convert from Project to ProjectDTO
     */
    public ProjectDTO convertToDTO(Project project, Locale locale) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setProjectNumber(project.getProjectNumber());
        projectDTO.setName(project.getName());
        projectDTO.setCustomer(project.getCustomer());
        projectDTO.setMember(changeListEmployeeToStringVisa(project.getEmployees()));
        projectDTO.setGroup(project.getGroup().getId().toString());
        projectDTO.setStatus(project.getStatus());
        projectDTO.setStatusLable(getStatusLable(project.getStatus(), locale));
        projectDTO.setStartDate(project.getStartDate());
        projectDTO.setEndDate(project.getEndDate());
        projectDTO.setVersion(project.getVersion());
        return projectDTO;
    }

    /**
     * convert from ProjectDTO to Project
     * @throws ConcurrencyUpdateException 
     * 
     * @throws NotEnoughEmployeeToCreateNewGroupException
     */
    public Project convertToProject(ProjectDTO projectDTO, Locale locale) throws ConcurrencyUpdateException {
        Project project = createOrFindProject(projectDTO.getId(), locale);
        List<Employee> employees = findAllEmployeeFromVisas(projectDTO.getMember());
        Group group = findGroup(projectDTO.getGroup());
        project.setProjectNumber(projectDTO.getProjectNumber());
        project.setName(projectDTO.getName());
        project.setCustomer(projectDTO.getCustomer());
        project.setGroup(group);
        project.setEmployees(employees);
        project.setStatus(projectDTO.getStatus());
        project.setStartDate(projectDTO.getStartDate());
        project.setEndDate(projectDTO.getEndDate());
        project.setVersion(projectDTO.getVersion());
        return project;
    }

    /**
     * handle list employee to string visa
     */
    public String changeListEmployeeToStringVisa(List<Employee> employees) {
        String result = "";
        if (!employees.isEmpty()) {
           result += String.join(ApplicationUtil.VISA_JOIN_CHARACTOR, employees.stream().map(Employee :: getVisa).collect(Collectors.toList()));
        }
        return result;
    }

    /**
     * handle create or find project
     * @throws ConcurrencyUpdateException 
     */
    public Project createOrFindProject(Long id, Locale locale) throws ConcurrencyUpdateException {
        Project project = new Project();
        if (id != null) {
            project = projectRepository.findOne(id);
            if(project == null) {
                String message = messageSource.getMessage("error.concurrencyUpdate", null, locale);
                throw new ConcurrencyUpdateException(message);
            }
            em.detach(project);
        }
        return project;
    }

    /**
     * handle find employee by visas
     */
    public List<Employee> findAllEmployeeFromVisas(String visas) {
        List<Employee> employees = new ArrayList<>();
        
        List<String> employeesError = new ArrayList<>();
        if (!visas.isEmpty()) {
            String[] visaEmployee = visas.split(ApplicationUtil.VISA_JOIN_CHARACTOR);
            for (String visa : visaEmployee) {
                Employee e = employeeRepository.findOne(employee.visa.containsIgnoreCase(visa));
                if (e != null) {
                    employees.add(e);
                } else {
                    employeesError.add(visa);
                }
            }
        }
        return employees;
    }
    
    public String getStatusLable(String status, Locale locale) {
        return messageSource.getMessage("project.status." + status, null, locale);
    }

    /**
     * handle find existed group
     * 
     * @throws NotEnoughEmployeeToCreateNewGroupException
     */
    public Group findGroup(String groupString) {
        if (!ApplicationUtil.GROUP_STATUS_NEW.equalsIgnoreCase(groupString)) {
            return groupRepository.findOne((Long.valueOf(groupString)));
        }
        return null;
    }

    /**
     * convert from list Project to list ProjectDTO
     */
    public List<ProjectDTO> convertToListDTO(Iterable<Project> projects, Locale locale) {
        List<ProjectDTO> result = new ArrayList<>();
        for (Project project : projects) {
            result.add(convertToDTO(project, locale));
        }
        return result;
    }

    /**
     * convert from list ProjectDTO to list Project
     * @throws ConcurrencyUpdateException 
     */
    public List<Project> convertToListProject(List<ProjectDTO> projectDTOs, Locale locale) throws ConcurrencyUpdateException {
        List<Project> result = new ArrayList<>();
        for (ProjectDTO projectDTO : projectDTOs) {
            result.add(convertToProject(projectDTO, locale));
        }
        return result;
    }
}
