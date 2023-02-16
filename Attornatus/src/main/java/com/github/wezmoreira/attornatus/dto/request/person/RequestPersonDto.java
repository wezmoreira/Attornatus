package com.github.wezmoreira.attornatus.dto.request.person;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.wezmoreira.attornatus.dto.request.address.RequestAddressDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class RequestPersonDto {
    @NotBlank
    private String name;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;
    @NotNull
    @NotEmpty
    private List<@Valid RequestAddressDto> address;
}
