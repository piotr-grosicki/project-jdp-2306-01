package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.GroupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/groups")
@CrossOrigin("*")
@RequiredArgsConstructor
public class GroupController {
    @GetMapping
    public List<GroupDto> getGroups() {
        return new ArrayList<>();
    }

    @GetMapping(value = "/{groupId}")
    public GroupDto getGroup(@PathVariable Long groupId) {
        return new GroupDto();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createGroup(@RequestBody GroupDto groupDto) {
    }

    @PutMapping(value = "/{groupId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateGroup(@PathVariable Long groupId, @RequestBody GroupDto groupDto) {
    }
}
