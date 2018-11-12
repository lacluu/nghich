package vn.elca.training.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import vn.elca.training.dom.Group;
@Repository
public interface GroupRepository extends JpaRepository<Group, Long>, QueryDslPredicateExecutor<Group>{
}
