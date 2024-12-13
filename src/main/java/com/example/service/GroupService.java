package com.example.service;

import com.example.dto.CreateGroupDTO;
import com.example.dto.ResponseGroupDTO;
import com.example.dto.UpdateGroup;
import com.example.entity.GroupEntity;
import com.example.exp.AppBadException;
import com.example.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    public ResponseGroupDTO addGroup(CreateGroupDTO request) {
        Optional<GroupEntity> optional = groupRepository.getByGroupName(request.getName());
        if (optional.isPresent()) {
            throw new AppBadException("This group already exists!");
        }
        GroupEntity group = toEntity(request);
        groupRepository.save(group);
        return toDTO(group);
    }

    public GroupEntity toEntity(CreateGroupDTO request) {
        GroupEntity group = new GroupEntity();
        group.setName(request.getName());
        group.setTeacherId(request.getTeacherId());
        group.setDescription(request.getDescription());
        return group;
    }

    public ResponseGroupDTO toDTO(GroupEntity group) {
        ResponseGroupDTO dto = new ResponseGroupDTO();
        dto.setId(group.getId());
        dto.setName(group.getName());
        dto.setDescription(group.getDescription());
        dto.setTeacherId(group.getTeacherId());
        return dto;
    }

    public ResponseGroupDTO update(Integer id, UpdateGroup updateGroup) {
        Optional<GroupEntity> optional = groupRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadException("Group not found!");
        }
        GroupEntity group = optional.get();
        if (updateGroup.getName() != null) group.setName(updateGroup.getName());
        if (updateGroup.getTeacherId() != null) group.setTeacherId(updateGroup.getTeacherId());
        if (updateGroup.getDescription() != null) group.setDescription(updateGroup.getDescription());
        groupRepository.save(group);
        return toDTO(group);
    }

    public boolean delete(Integer id) {
        if (groupRepository.existsById(id)) {
            groupRepository.deleteById(id);
            return true;
        }
        throw new AppBadException("bunaqa id li user mavjudmas!");
    }

    public List<ResponseGroupDTO> getAll() {
        List<ResponseGroupDTO> list = new LinkedList<>();
        groupRepository.findAll().forEach(group -> list.add(toDTO(group)));
        return list;
    }
}
