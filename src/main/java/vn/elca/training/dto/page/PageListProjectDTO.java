package vn.elca.training.dto.page;

import java.util.List;

import vn.elca.training.dto.ProjectDTO;

public class PageListProjectDTO {
    
    private List<ProjectDTO> projects;
    private long totalPage;
    private long page;
    
    public PageListProjectDTO() {
    }

    public PageListProjectDTO(List<ProjectDTO> projects, long totalPage, long page) {
        super();
        this.projects = projects;
        this.totalPage = totalPage;
        this.page = page;
    }

    public List<ProjectDTO> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectDTO> projects) {
        this.projects = projects;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }
}
