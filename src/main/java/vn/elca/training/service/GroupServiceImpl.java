package vn.elca.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.elca.training.dao.GroupRepository;
import vn.elca.training.dom.Employee;
import vn.elca.training.dom.Group;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;
    
    @Override
    public Group create(Group group) {
        return groupRepository.saveAndFlush(group);
    }

    @Override
    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    @Override
    public Group save(Group group) {
        return groupRepository.save(group);
    }
    
    @Override
    public Group save(Employee learderGroup) {
        return groupRepository.save(new Group(learderGroup));
    }
    
}
