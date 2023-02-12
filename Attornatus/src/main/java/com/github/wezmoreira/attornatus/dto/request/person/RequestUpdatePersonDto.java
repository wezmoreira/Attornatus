package com.github.wezmoreira.attornatus.dto.request.person;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class RequestUpdatePersonDto {
    @NotBlank
    private String name;
    @NotBlank
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;
}
