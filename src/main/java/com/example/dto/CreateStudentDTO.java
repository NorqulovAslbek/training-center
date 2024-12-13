package com.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStudentDTO {
    private Integer groupId;
    private String name;
    private String surname;
    private String phone;
    private String parentPhone;
}
