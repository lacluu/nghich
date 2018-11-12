package vn.elca.training.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import vn.elca.training.dom.Group;
import vn.elca.training.dto.GroupDTO;


@Component
public class GroupConverter {
    
    /**
     * convert from Group to GroupDTO
     * */
    public static GroupDTO convertToDTO(Group group) {
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setId(group.getId());
        
        return groupDTO;
    }
    
    /**
     * convert from list Group to list GroupDTO
     * */
    public static List<GroupDTO> convertToDTO(List<Group> groups) {
        List<GroupDTO> result = new ArrayList<>();
        for (Group group : groups) {
            GroupDTO groupDTO = convertToDTO(group);
            result.add(groupDTO);
        }
        return result;
    }
}
