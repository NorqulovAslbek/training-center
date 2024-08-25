package com.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthDTO {
    @Pattern(regexp = "^\\+?998[1-9][0-9]{8}$|^9[0-9]{8}$", message = "Iltimos, to‘g‘ri O‘zbekiston telefon raqamini kiriting.")
    private String phone;
    @NotNull
    private String password;
}
