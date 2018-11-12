package vn.elca.training.dto;

import java.util.Date;

import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ProjectDTO {
    private Long id;
    @Min(value=1)
    @Max(value = 9999)
    private long projectNumber;
    @NotNull
    @Size(min=1, max=50)
    private String name;
    @NotNull
    @Size(min=1, max=50)
    private String customer;
    @NotNull
    private String group;
    private String member;
    @NotNull
    @Size(min=1, max=3)
    private String status;
    private String statusLable;
    @NotNull
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date startDate;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date endDate;
    @Min(value = 0)
    private long version;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
   
    public String getGroup() {
        return group;
    }
    public void setGroup(String group) {
        this.group = group;
    }
    public String getMember() {
        return member;
    }
    public void setMember(String member) {
        this.member = member;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatusLable() {
        return statusLable;
    }
    public void setStatusLable(String statusLable) {
        this.statusLable = statusLable;
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
}
