package com.example.dto;

import com.example.enums.ProfileRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProfileDTO {
    private String name;
    private String surname;
    private String phone;
    private String password;
    private  ProfileRole role;
}
