package com.example.dto;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateGroupDTO {
    private String name;
    private Integer teacherId;
    private String description;
}
