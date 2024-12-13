package com.example.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseGroupDTO {
    private Integer id;
    private String name;
    private Integer teacherId;
    private String description;
}
