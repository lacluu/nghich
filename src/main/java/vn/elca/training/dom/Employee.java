package vn.elca.training.dom;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 3)
    private String visa;
    @Column(nullable = false, length = 50)
    private String firtName;
    @Column(nullable = false, length = 50)
    private String lastName;
    @Column(nullable = false)
    private Date birthDate;
    @Column(nullable = false)
    private long version;
    
    @OneToMany(mappedBy="groupLeader", fetch = FetchType.LAZY)
    private List<Group> groups;
    
    @ManyToMany(mappedBy = "employees")
    private List<Project> projects;

    public Employee() {
    }
    
    public Employee(String visa, String firtName, String lastName, Date birthDate, long version) {
        super();
        this.visa = visa;
        this.firtName = firtName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVisa() {
        return visa;
    }

    public void setVisa(String visa) {
        this.visa = visa;
    }

    public String getFirtName() {
        return firtName;
    }

    public void setFirtName(String firtName) {
        this.firtName = firtName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

}
