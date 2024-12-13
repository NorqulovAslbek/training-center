package com.example.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseStudent {
    private Integer id;
    private String name;
    private String surname;
    private String phone;
    private String parentPhone;
}
