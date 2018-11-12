package vn.elca.training.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.mysema.query.jpa.impl.JPAQuery;

import vn.elca.training.dom.Project;
import vn.elca.training.dom.QProject;
import vn.elca.training.dto.page.PageListProject;

@Repository
public class ProjectRepositoryImpl implements ProjectRepositoryCustom {
    @PersistenceContext
    private EntityManager em;
    private QProject project = QProject.project;
    private final int LIMIT_RESULT_NUMBER = 5;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Project> customSearchAllProject() {
        return new JPAQuery(em)
                    .from(project)
                    .list(project);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageListProject customSearchLikeProjectName(String searchString, String searchStatus, int offset) {
        List<Project> result = new JPAQuery(em)
                .from(QProject.project)
                .where(QProject.project.name.containsIgnoreCase(searchString)
                        .and(QProject.project.status.eq(searchStatus)))
                .limit(LIMIT_RESULT_NUMBER)
                .offset(offset)
                .list(QProject.project);
        
        long countProject = new JPAQuery(em)
                .from(QProject.project)
                .where(QProject.project.name.containsIgnoreCase(searchString)
                        .and(QProject.project.status.eq(searchStatus)))
                .count();
        
        return new PageListProject(result, countProject);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Project> customSearchByCustomerAndGroupLeaderId(String customer, Long groupLeaderId) {
        return new JPAQuery(em)
                    .from(project)
                    .where(project.customer.eq(customer), 
                           project.group.groupLeader.id.eq(groupLeaderId))
                    .orderBy(project.name.asc())
                    .groupBy(project.customer)
                    .list(project);
    }
    
}
