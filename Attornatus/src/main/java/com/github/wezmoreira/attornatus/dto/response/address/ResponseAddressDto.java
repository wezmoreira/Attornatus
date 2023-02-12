package com.github.wezmoreira.attornatus.dto.response.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAddressDto {
    private Long id;
    private String street;
    private String cep;
    private String number;
    private String city;
    private boolean mainAddress;
}
