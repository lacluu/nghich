package vn.elca.training.dom;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = true, unique=true)
    private long projectNumber;
    @Column(nullable = true, length = 50)
    private String name;
    @Column(nullable = true, length = 50)
    private String customer;
    @Column(nullable = true, length = 3)
    private String status;
    @Column(nullable = true)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date startDate;
    @Column(nullable = true)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date endDate;
    @Column(nullable = true)
    @Version
    private long version;
    @Column(nullable = true)
    private boolean activated;
    @ManyToOne()
    @JoinColumn(name = "groupId")
    private Group group;
    @ManyToMany
    private List<Employee> employees;
    
    @Column
    private Date finishingDate;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private Set<Task> tasks = new HashSet<>();

    public Project() {
    }

    public Project(long projectNumber) {
        super();
        this.projectNumber = projectNumber;
    }

    public Project(long projectNumber, String name, String customer, String status, Date startDate, long version,
            boolean activated, Group group, List<Employee> employees) {
        super();
        this.projectNumber = projectNumber;
        this.name = name;
        this.customer = customer;
        this.status = status;
        this.startDate = startDate;
        this.version = version;
        this.activated = activated;
        this.group = group;
        this.employees = employees;
    }
    
    public Project(String name, Date finishingDate) {
        this.name = name;
        this.finishingDate = finishingDate;
    }

    public Project(Long id, String name, Date finishingDate) {
        this.id = id;
        this.name = name;
        this.finishingDate = finishingDate;
    }

    public Date getFinishingDate() {
        return finishingDate;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }


    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getId() {
        return id;
    }

    public long getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(long projectNumber) {
        this.projectNumber = projectNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }
    
}