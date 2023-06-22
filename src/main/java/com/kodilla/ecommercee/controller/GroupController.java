package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.GroupDto;
import com.kodilla.ecommercee.exceptions.GroupNotFoundException;
import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService service;
    private final GroupMapper groupMapper;

    //all
    @GetMapping
    public ResponseEntity<List<GroupDto>> getGroups() {
        List<Group> groups = service.getGroups();
        return ResponseEntity.ok(groupMapper.mapToGroupDtoList(groups));
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<GroupDto> getGroup(@PathVariable Long id) throws GroupNotFoundException {
        return ResponseEntity.ok(groupMapper.mapToGroupDto(service.getGroup(id).orElseThrow(GroupNotFoundException::new)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createGroup(@RequestBody GroupDto groupDto) {
        Group group = groupMapper.mapToGroup(groupDto);
        service.saveGroup(group);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<GroupDto> updateGroup(@RequestBody GroupDto groupDto) {
        Group group = groupMapper.mapToGroup(groupDto);
        Group updatedGroup = service.saveGroup(group);
        return ResponseEntity.ok(groupMapper.mapToGroupDto(updatedGroup));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        service.deleteGroup(id);
        return ResponseEntity.ok().build();
    }
}