package com.example.controller;

import com.example.dto.CreateProfileDTO;
import com.example.dto.ProfileDTO;
import com.example.dto.ProfileRequestAndResponseDTO;
import com.example.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")

public class AdminController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "bu api admin tomonidan profile qo'shish uchun ishlatiladi.", description = "admin tomonidan profile qo'shish")
    public ResponseEntity<?> create(@RequestBody CreateProfileDTO createProfileDTO) {
        return ResponseEntity.ok(profileService.create(createProfileDTO));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "bu api admin tomonidan profilelarni malumotlarini o'zgartirish uchun ishlatiladi", description = "admin tomonidan profilelarni malumotlarini o'zgartirish")
    public ResponseEntity<?> update(@RequestBody ProfileRequestAndResponseDTO dto,
                                    @PathVariable("id") Integer id) {
        return ResponseEntity.ok(profileService.update(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "bu api admin tomonidan profile ni o'chirish uchun ishlatiladi.", description = " admin tomonidan profile ni o'chirish")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(profileService.delete(id));
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "admin uchun api yani profilelar listini ko'rish uchun ishlatiladi", description = "profilelar listini ko'rish")
    public ResponseEntity<?> getList() {
        return ResponseEntity.ok(profileService.getList());
    }

    @PostMapping("/filter")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "admin uchun profilelar listini sortlab ko'rish uchun pagenation qilib ko'rish uchun", description = "profilelar listini sortlab ko'rish uchun pagenation qilib ko'rish uchun")
    public ResponseEntity<?> filterAndPagination(@RequestBody ProfileRequestAndResponseDTO dto
            , @RequestParam("page") Integer page
            , @RequestParam("size") Integer size) {
        return ResponseEntity.ok(profileService.filter(dto, page, size));

    }

}
