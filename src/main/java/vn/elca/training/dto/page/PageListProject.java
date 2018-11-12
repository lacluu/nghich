package vn.elca.training.dto.page;

import java.util.List;

import vn.elca.training.dom.Project;

public class PageListProject {
    private List<Project> projects;
    private long size;
    
    public PageListProject() {
    }
    
    public PageListProject(List<Project> projects, long size) {
        super();
        this.projects = projects;
        this.size = size;
    }
    
    public List<Project> getProjects() {
        return projects;
    }
    
    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
    
    public long getSize() {
        return size;
    }
    
    public void setSize(long size) {
        this.size = size;
    }
 
}
