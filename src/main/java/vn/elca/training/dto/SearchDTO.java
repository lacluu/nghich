package vn.elca.training.dto;

public class SearchDTO {
    private String searchName;
    private String searchStatus;
    private int page;

    public SearchDTO() {
    }
    
    public SearchDTO(String searchName, String searchStatus) {
        super();
        this.searchName = searchName;
        this.searchStatus = searchStatus;
        this.page = 0;
    }

    public SearchDTO(String searchName, String searchStatus, int page) {
        super();
        this.searchName = searchName;
        this.searchStatus = searchStatus;
        this.page = page;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getSearchStatus() {
        return searchStatus;
    }

    public void setSearchStatus(String searchStatus) {
        this.searchStatus = searchStatus;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
    
}
