package vn.elca.training.service;

import java.util.List;

import vn.elca.training.dom.Employee;
import vn.elca.training.dom.Group;

public interface GroupService {
    Group create(Group group);
    Group save(Group group);
    List<Group> findAll();
    Group save(Employee learderGroup);
}
