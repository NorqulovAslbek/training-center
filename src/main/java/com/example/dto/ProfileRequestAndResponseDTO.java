package com.example.dto;

import com.example.enums.ProfileRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileRequestAndResponseDTO {
    private Integer id;
    private String name;
    private String surname;
    private String password;
    private String phone;
    private ProfileRole role;
}
