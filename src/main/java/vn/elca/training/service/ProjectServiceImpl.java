package vn.elca.training.service;

import static vn.elca.training.dom.QProject.project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringJoiner;

import javax.annotation.PostConstruct;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.mysema.query.types.expr.BooleanExpression;

import vn.elca.training.converter.ProjectConverter;
import vn.elca.training.dao.EmployeeRepository;
import vn.elca.training.dao.GroupRepository;
import vn.elca.training.dao.ProjectRepository;
import vn.elca.training.dom.Employee;
import vn.elca.training.dom.Group;
import vn.elca.training.dom.Project;
import vn.elca.training.dto.ProjectDTO;
import vn.elca.training.dto.page.PageListProjectDTO;
import vn.elca.training.exception.ConcurrencyUpdateException;
import vn.elca.training.exception.InvalidStatusOfProjectDeleteException;
import vn.elca.training.exception.NotEnoughEmployeeToCreateNewGroupException;
import vn.elca.training.exception.ProjectNumberAlreadyExistsException;
import vn.elca.training.exception.VisaNotExistedException;
import vn.elca.training.util.ApplicationUtil;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private ProjectConverter projectConverter;
    

    @Autowired
    private MessageSource messageSource;
    
    /**
     * function initial data when application create project service 
     * */
    @SuppressWarnings("deprecation")
    @PostConstruct
    public void init() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("QMV", "nghich", "thien", new Date(1990, 10, 20), 1));
        employees.add(new Employee("TQP", "nam", "cao", new Date(1991, 11, 21), 1));
        employees.add(new Employee("HNH", "tam", "thanh", new Date(1992, 11, 02), 1));
        employees.add(new Employee("NQN", "tin", "nha", new Date(1990, 10, 20), 1));
        employees.add(new Employee("PLH", "tran", "linh", new Date(1991, 07, 14), 1));
        employees.add(new Employee("HNL", "linh", "tam", new Date(1992, 8, 26), 1));
        employees.add(new Employee("TBH", "tam", "nhan", new Date(1990, 9, 21), 1));
        employees.add(new Employee("NQT", "nhan", "quan", new Date(1990, 10, 20), 1));
        employees.add(new Employee("TDN", "quan", "long", new Date(1990, 10, 20), 1));
        List<Employee> employeesGroup2 = new ArrayList<>();
        employees.add(new Employee("HPN", "dat", "nhien", new Date(1990, 10, 20), 1));
        employees.add(new Employee("HUN", "tin", "huynh", new Date(1990, 10, 20), 1));
        employees.add(new Employee("BNN", "tran", "ninh", new Date(1990, 10, 20), 1));
        employees.add(new Employee("PNH", "linh", "phuc", new Date(1990, 10, 20), 1));
        employees.add(new Employee("VVT", "tam", "van", new Date(1990, 10, 20), 1));
        
        employeeRepository.save(employees);
        employeeRepository.save(employeesGroup2);
        
        employeeRepository.flush();
        
        Group group1 = new Group(1, employees.get(0));
        Group group2 = new Group(2, employees.get(2));
        
        groupRepository.saveAndFlush(group1);
        groupRepository.saveAndFlush(group2);
        
        List<Project> projects = new ArrayList<>();
        
        projects.add(new Project(1111, "EFV", "Thai", "PLA", java.sql.Date.valueOf(LocalDate.of(2013, 03, 13)), 2, true, group1,
                employees.subList(1, 4)));
        projects.add(new Project(1212, "CXTRANET", "Thien", "NEW", java.sql.Date.valueOf(LocalDate.of(2014, 04, 14)), 1, true, group1,
                employees.subList(4, 6)));
        projects.add(new Project(1313, "CRYSTAL BALL", "Thanh", "NEW", java.sql.Date.valueOf(LocalDate.of(2013, 04, 14)), 1, true, group1,
                employees.subList(6, 9)));
        projects.add(new Project(1414, "IOC CLIENT EXTRANET", "Thai", "NEW", java.sql.Date.valueOf(LocalDate.of(2013, 04, 14)), 1, true, group2,
                employees.subList(9, 13)));
        
        List<Employee> listEmployeeOfProjectKSTA = new ArrayList<>();
        listEmployeeOfProjectKSTA.add(employees.get(0));
        listEmployeeOfProjectKSTA.add(employees.get(13));
        projects.add(new Project(1515, "KSTA MIGRATION", "Thai", "NEW", java.sql.Date.valueOf(LocalDate.of(2013, 04, 14)), 1, true, group2,
                listEmployeeOfProjectKSTA));
        
        projects.add(new Project(1616, "FACEBOOK", "Dat", "PLA", java.sql.Date.valueOf(LocalDate.of(2013, 03, 13)), 2, true, group1,
                employees.subList(1, 3)));
        projects.add(new Project(1717, "GOOGLE", "Giang", "NEW", java.sql.Date.valueOf(LocalDate.of(2014, 04, 14)), 1, true, group1,
                employees.subList(3, 7)));
        projects.add(new Project(1818, "YOUTUBE", "Danh", "NEW", java.sql.Date.valueOf(LocalDate.of(2013, 04, 14)), 1, true, group2,
                employees.subList(7, 10)));
        projects.add(new Project(1919, "SKY BEE", "Loi", "FIN", java.sql.Date.valueOf(LocalDate.of(2013, 04, 14)), 1, true, group2,
                employees.subList(10, 13)));
        
        projects.add(new Project(2000, "QUERY DSL", "Danh", "PLA", java.sql.Date.valueOf(LocalDate.of(2013, 03, 13)), 2, true, group1,
                employees.subList(1, 3)));
        projects.add(new Project(2121, "SPRING FRAMEWORK", "Giong", "NEW", java.sql.Date.valueOf(LocalDate.of(2014, 04, 14)), 1, true, group1,
                employees.subList(3, 7)));
        projects.add(new Project(2020, "JAVASCRIPT", "Dung", "PLA", java.sql.Date.valueOf(LocalDate.of(2013, 04, 14)), 1, true, group2,
                employees.subList(7, 10)));
        projects.add(new Project(2222, "JQUERY", "Nhan", "NEW", java.sql.Date.valueOf(LocalDate.of(2013, 04, 14)), 1, true, group2,
                employees.subList(10, 13)));
        
        projects.add(new Project(3030, "MICROSOFT 360", "Danh", "NEW", java.sql.Date.valueOf(LocalDate.of(2013, 03, 13)), 2, true, group1,
                employees.subList(1, 3)));
        projects.add(new Project(3131, "MICROSOFT SQL SERVER", "Giong", "PLA", java.sql.Date.valueOf(LocalDate.of(2014, 04, 14)), 1, true, group1,
                employees.subList(3, 7)));
        projects.add(new Project(3232, "MICROSOFT SKYPE", "Dung", "NEW", java.sql.Date.valueOf(LocalDate.of(2013, 04, 14)), 1, true, group2,
                employees.subList(7, 10)));
        projects.add(new Project(3333, "MICROSOFT ACCESS", "Nhan", "FIN", java.sql.Date.valueOf(LocalDate.of(2013, 04, 14)), 1, true, group2,
                employees.subList(10, 13)));
        
        projects.add(new Project(3434, "MICROSOFT  ONENOTE", "Danh", "PLA", java.sql.Date.valueOf(LocalDate.of(2013, 03, 13)), 2, true, group1,
                employees.subList(1, 3)));
        projects.add(new Project(3535, "MICROSOFT OUTLOOK", "Giong", "FIN", java.sql.Date.valueOf(LocalDate.of(2014, 04, 14)), 1, true, group1,
                employees.subList(3, 7)));
        projects.add(new Project(3636, "MICROSOFT WORD", "Dung", "INP", java.sql.Date.valueOf(LocalDate.of(2013, 04, 14)), 1, true, group2,
                employees.subList(7, 10)));
        projects.add(new Project(3737, "MICROSOFT EXCEL", "Nhan", "INP", java.sql.Date.valueOf(LocalDate.of(2013, 04, 14)), 1, true, group2,
                employees.subList(10, 13)));
        
        projects.add(new Project(2323, "GOOGLE TRANSLATE", "Danh", "PLA", java.sql.Date.valueOf(LocalDate.of(2013, 03, 13)), 2, true, group1,
                employees.subList(1, 3)));
        projects.add(new Project(2424, "SPRING MVC", "Giong", "PLA", java.sql.Date.valueOf(LocalDate.of(2014, 04, 14)), 1, true, group1,
                employees.subList(3, 7)));
        projects.add(new Project(2525, "SPRING BOOT", "Dung", "FIN", java.sql.Date.valueOf(LocalDate.of(2013, 04, 14)), 1, true, group2,
                employees.subList(7, 10)));
        projects.add(new Project(2626, "SPRING CLOUD", "Nhan", "FIN", java.sql.Date.valueOf(LocalDate.of(2013, 04, 14)), 1, true, group2,
                employees.subList(10, 13)));
        
        projects.add(new Project(2727, "GOOGLE  ANALYTICS", "Danh", "PLA", java.sql.Date.valueOf(LocalDate.of(2013, 03, 13)), 2, true, group1,
                employees.subList(1, 3)));
        projects.add(new Project(2828, "GOOGLE IMAGE", "Giong", "FIN", java.sql.Date.valueOf(LocalDate.of(2014, 04, 14)), 1, true, group1,
                employees.subList(3, 7)));
        projects.add(new Project(2929, "GOOGLE MAPS", "Dung", "INP", java.sql.Date.valueOf(LocalDate.of(2013, 04, 14)), 1, true, group2,
                employees.subList(7, 10)));
        projects.add(new Project(3000, "GOOGLE PLAY", "Nhan", "INP", java.sql.Date.valueOf(LocalDate.of(2013, 04, 14)), 1, true, group2,
                employees.subList(10, 13)));
        
        projectRepository.save(projects);
        projectRepository.flush();
    }

    /**
     * function create or update project
     * @throws NotEnoughEmployeeToCreateNewGroupException 
     * @throws ProjectNumberAlreadyExistsException 
     * @throws VisaNotExistedException 
     * @throws ConcurrencyUpdateException 
     * */
    @Override
    public ProjectDTO save(ProjectDTO projectDTO, Locale locale) throws NotEnoughEmployeeToCreateNewGroupException,
            ProjectNumberAlreadyExistsException, VisaNotExistedException, ConcurrencyUpdateException {
        Project project = projectConverter.convertToProject(projectDTO, locale);
        
        List<Employee> employees = validateEmployees(project, projectDTO);
        
        createGroupIfNotExist(projectDTO, project, employees);
        
        try {
            project = projectRepository.save(project);
            projectDTO = projectConverter.convertToDTO(project, locale);
        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof ConstraintViolationException) {
                throw new ProjectNumberAlreadyExistsException("Projet number already existed!", e);
            } else {
                throw e;
            }
        } catch (ObjectOptimisticLockingFailureException e) {
            String message = messageSource.getMessage("error.concurrencyUpdate", new String[] {projectDTO.getName(), projectDTO.getId().toString()}, locale);
            throw new ConcurrencyUpdateException(message, e);
        }
        return projectDTO;
    }

    private void createGroupIfNotExist(ProjectDTO projectDTO, Project project, List<Employee> employees)
            throws NotEnoughEmployeeToCreateNewGroupException {
        // if group status = 'NEW' then create group in this line
        if (projectDTO.getGroup().equalsIgnoreCase(ApplicationUtil.PROJECT_STATUS_NEW)) {
            if (!employees.isEmpty()) {
                Group group = new Group();
                group.setGroupLeader(employees.get(ApplicationUtil.FIRST_NUMBER));
                group = groupRepository.save(group);
                project.setGroup(group);

            } else {
                throw new NotEnoughEmployeeToCreateNewGroupException("Have at least one employee to create new a group!");
            }
        }
    }

    private List<Employee> validateEmployees(Project project, ProjectDTO projectDTO) throws VisaNotExistedException {
        List<Employee> employees = new ArrayList<>();
        // StringJoiner listVisaError = new StringJoiner(",", "{", "}") {;
        StringJoiner listVisaError = new StringJoiner(ApplicationUtil.VISA_JOIN_CHARACTOR, ApplicationUtil.PREFIX,
                ApplicationUtil.SUFFIX);
        String visas = projectDTO.getMember();
        if (!visas.isEmpty()) {
            String[] visaEmployee = visas.split(ApplicationUtil.VISA_JOIN_CHARACTOR);
            employees = Lists.newArrayList(project.getEmployees());
            for (String visa : visaEmployee) {
                if (!Iterables.any(employees, (Employee e) -> e.getVisa().equals(visa))) {
                    listVisaError.add(visa);
                }
            }
        }
        if (listVisaError.length() > 2) {
            throw new VisaNotExistedException(listVisaError.toString());
        }
        return employees;
    }

    /**
     * find project with criteria: Name, Number, Customer, and Status
     * result is sorted by project number ascending
     * */
    @Override
    public PageListProjectDTO findProject(String searchString, String searchStatus, int page, Locale locale) {
        Pageable pageable = new PageRequest(page, ApplicationUtil.LIMIT_RESULT_NUMBER, new Sort(Sort.Direction.ASC, "projectNumber"));
        
        BooleanExpression critial = project.name.startsWithIgnoreCase(searchString)
                                    .or(project.projectNumber.stringValue().startsWithIgnoreCase(searchString))
                                    .or(project.customer.startsWithIgnoreCase(searchString));
        
        if (!searchStatus.trim().isEmpty()) {
            critial = critial.and(project.status.equalsIgnoreCase(searchStatus));
        } 
        
        Page<Project> projects =  projectRepository.findAll(critial, pageable);
        
        List<ProjectDTO> projectDTOs = projectConverter.convertToListDTO(projects, locale);
        
        long size = (long) Math.ceil(projects.getTotalElements() / (double) ApplicationUtil.LIMIT_RESULT_NUMBER);
        
        return new PageListProjectDTO(projectDTOs, size, page);
    }

    /**
     * find project by project id
     * */
    @Override
    public ProjectDTO findById(Long id, Locale locale) {
        Project project = projectRepository.findOne(id);
        ProjectDTO projectDTO = null;
        if(project!= null) {
            projectDTO = projectConverter.convertToDTO(project, locale);
        }
        return projectDTO;
    }
    
    /**
     * function delete single project
     * @throws ConcurrencyUpdateException 
     * @throws InvalidStatusOfProjectDeleteException 
     * */
    @Override
    public void delete(ProjectDTO projectDTO, Locale locale) throws ConcurrencyUpdateException, InvalidStatusOfProjectDeleteException {
        try {
            if(projectDTO.getStatus().equalsIgnoreCase(ApplicationUtil.PROJECT_STATUS_NEW)) {
                Project project = projectConverter.convertToProject(projectDTO, locale);
                projectRepository.delete(project);
            }else {
                String message = messageSource.getMessage("error.project.delete.status.must.be.new", new String[] {}, locale);
                throw new InvalidStatusOfProjectDeleteException(message);
            }
        } catch (ObjectOptimisticLockingFailureException e) {
            String message = messageSource.getMessage("error.concurrencyUpdate", new String[] {projectDTO.getName(), projectDTO.getId().toString()}, locale);
            throw new ConcurrencyUpdateException(message, e);
        } 
    }
        
    /**
     * function delete multiple project
     * @throws ConcurrencyUpdateException 
     * @throws InvalidStatusOfProjectDeleteException 
     * */
    @Override
    public void deletes(List<ProjectDTO> projectDTOs, Locale locale) throws ConcurrencyUpdateException, InvalidStatusOfProjectDeleteException {
        try {
            List<Project> projects = projectConverter.convertToListProject(projectDTOs, locale);
            for (Project project : projects) {
                if(project.getStatus().equalsIgnoreCase(ApplicationUtil.PROJECT_STATUS_NEW)) {
                    projectRepository.delete(project);
                }else {
                    String message = messageSource.getMessage("error.project.delete.status.must.be.new", new String[] {}, locale);
                    throw new InvalidStatusOfProjectDeleteException(message);
                }
            }
        } catch (ObjectOptimisticLockingFailureException e) {
            String message = messageSource.getMessage("error.concurrencyUpdate", new String[] {}, locale);
            throw new ConcurrencyUpdateException(message, e);
        }
    }
}
