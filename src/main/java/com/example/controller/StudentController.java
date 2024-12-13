package com.example.controller;

import com.example.dto.CreateStudentDTO;
import com.example.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {
    @Autowired
    private StudentService studentService;


    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @Operation(summary = "bu api studentni create qilish uchun ishlatiladi")
    public ResponseEntity<?> addUser(@RequestBody CreateStudentDTO create) {
        return ResponseEntity.ok(studentService.add(create));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @Operation(summary = "bu api studentni update qilish uchun ishlatiladi")
    public ResponseEntity<?> update(@RequestBody CreateStudentDTO update, @PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentService.update(update,id));
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @Operation(summary = "bu api studentni delete qilish uchun ishlatiladi")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentService.delete(id));
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @Operation(summary = "bu api studentlarni korish uchun ishlatiladi")
    public ResponseEntity<?> all() {
        return ResponseEntity.ok(studentService.getAll());
    }

}
