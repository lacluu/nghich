package vn.elca.training.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import vn.elca.training.dom.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>, QueryDslPredicateExecutor<Project>, ProjectRepositoryCustom {
    Page<Project> findByStatusAndNameLikeOrProjectNumberOrCustomerIgnoreCase(String status, String name, 
                       long projectNumber, String customer, Pageable pageable);
    Page<Project> findByNameLikeIgnoreCase(String name, Pageable pageable);
}
