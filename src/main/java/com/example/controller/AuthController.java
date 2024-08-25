package com.example.controller;

import com.example.dto.AuthDTO;
import com.example.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Api for login", description = "this api used for login")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDTO authDTO){
         return ResponseEntity.ok(authService.login(authDTO));
    }

}
