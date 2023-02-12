package com.github.wezmoreira.attornatus.dto.response.person;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.wezmoreira.attornatus.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePersonDto {
    private Long id;
    private String name;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;
    private List<Address> address;
}
