package com.example.dto;


import com.example.enums.ProfileRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtDTO {
    private Integer id;
    private ProfileRole role;
    private String phone;
    public JwtDTO(Integer id) {
        this.id = id;
    }

    public JwtDTO(Integer id, ProfileRole role) {
        this.id = id;
        this.role = role;
    }

    public JwtDTO(String phone, ProfileRole role) {
        this.role = role;
        this.phone = phone;
    }
}
