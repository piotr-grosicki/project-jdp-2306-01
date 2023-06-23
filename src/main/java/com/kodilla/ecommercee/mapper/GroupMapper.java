package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.dto.GroupDto;
import com.kodilla.ecommercee.service.GroupService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupMapper {

    public Group mapToGroup(final GroupDto groupDto) {
        return new Group(groupDto.getGroupId(),
                groupDto.getGroupName(),
                new ArrayList<>()
        );
        //docelowo w miejsce new ArrayList<>() trzeba wstawić listę produktów zmapowaną na podstawie ich id (pobraną z productService)
        /*return new Group(groupDto.getGroupId(),
                groupDto.getGroupName(),
                groupDto.getProductIdList().stream()
                        .map(id -> productService.getProductById(id))
                        .toList()
        );*/
    }

    public GroupDto mapToGroupDto(final Group group) {
        return new GroupDto(
                group.getGroupId(),
                group.getGroupName(),
                group.getProductList().stream()
                        .map(p -> p.getProductId())
                        .toList()
        );
    }

    public List<GroupDto> mapToGroupDtoList(final List<Group> groupList) {
        return groupList.stream()
                .map(this::mapToGroupDto)
                .toList();
    }
}
