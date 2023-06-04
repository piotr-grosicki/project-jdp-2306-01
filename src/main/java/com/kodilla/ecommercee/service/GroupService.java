package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.security.acl.Group;
import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group addGroup(Group group) {
        return groupRepository.save(group);
    }

    public Group getGroupById(Long id) {
        return groupRepository.findById(id).orElse(null);
    }

    public Group updateGroup(Long id, Group updatedGroup) {

        Group existingGroup = groupRepository.findById(id).orElse(null);
        if (existingGroup != null) {
            return groupRepository.save(existingGroup);
        }
        return null;
    }
}