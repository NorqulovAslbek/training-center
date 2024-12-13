package com.example.controller;

import com.example.dto.CreateGroupDTO;
import com.example.dto.UpdateGroup;
import com.example.service.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/group")
public class GroupController {
    @Autowired
    private GroupService groupService; // Dependency Injection via Constructor


    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @Operation(summary = "This API allows only ADMIN and TEACHER to add a group.")
    public ResponseEntity<?> addGroup(@RequestBody CreateGroupDTO request) {
        return ResponseEntity.ok(groupService.addGroup(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @Operation(summary = "This API allows only ADMIN and TEACHER to update a group.")
    public ResponseEntity<?> update(@RequestBody UpdateGroup updateGroup, @PathVariable("id") Integer id) {
        return ResponseEntity.ok(groupService.update(id, updateGroup));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @Operation(summary = "groppani ochirish id si boyicha")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(groupService.delete(id));
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @Operation(summary = "groppani listini ko'rish")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(groupService.getAll());
    }
}
