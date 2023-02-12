package com.github.wezmoreira.attornatus.dto.request.address;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestAddressDto {
    @NotBlank
    private String street;
    @NotBlank
    private String cep;
    @NotBlank
    private String number;
    @NotBlank
    private String city;
}
