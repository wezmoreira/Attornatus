package com.github.wezmoreira.attornatus.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionResponseDto {
    private String message;
    private String type;
}
