package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.dto.GroupDto;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupMapper {

    private final ProductService productService;

    public Group mapToGroup(final GroupDto groupDto) {
        Group group =  new Group(groupDto.getGroupId(),
                groupDto.getGroupName(),
                new ArrayList<>()
        );
        for(int i = 0; i < groupDto.getProductIdList().size(); i++){
            try {
                group.getProductList().add(productService.getProductById(groupDto.getProductIdList().get(i)));
            } catch (ProductNotFoundException e) {

            }
        }
        return group;
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
