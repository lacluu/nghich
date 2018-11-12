package vn.elca.training.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.elca.training.converter.GroupConverter;
import vn.elca.training.dto.GroupDTO;
import vn.elca.training.service.GroupService;

@RestController
@RequestMapping(value = "/groups")
public class GroupApiController {
    @Autowired
    private GroupService groupService;
    
    @RequestMapping(method = RequestMethod.GET)
    public List<GroupDTO> getAllGroup(){
        return GroupConverter.convertToDTO(groupService.findAll());
    }
}
