package vn.elca.training.dao;

import java.util.List;

import vn.elca.training.dom.Project;
import vn.elca.training.dto.page.PageListProject;

public interface ProjectRepositoryCustom {
    List<Project> customSearchAllProject();

    PageListProject customSearchLikeProjectName(String searchString, String searchStatus, int offset);

    List<Project> customSearchByCustomerAndGroupLeaderId(String customer, Long groupLeaderId);
}