package com.github.wezmoreira.attornatus.handler;

import com.github.wezmoreira.attornatus.exceptions.AddressNotFoundException;
import com.github.wezmoreira.attornatus.exceptions.InvaliAddressException;
import com.github.wezmoreira.attornatus.exceptions.PersonNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalHandlerException {
    //extends ResponseEntityExceptionHandler
    private static final String PERSON_NOT_FOUND = "Pessoa não encontrada!";
    private static final String ADDRESS_NOT_FOUND = "Endereço não encontrado!";
    private static final String ADDRESS_INVALID = "Endereço inválido, verifique os endereços cadastrados!";

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handlerPersonNotFound(PersonNotFoundException personNotFoundException) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto(personNotFoundException.getMessage(), PERSON_NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }

    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handlerAddressNotFound(AddressNotFoundException addressNotFoundException) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto(addressNotFoundException.getMessage(), ADDRESS_NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }

    @ExceptionHandler(InvaliAddressException.class)
    public ResponseEntity<ExceptionResponseDto> handlerInvalidAddress(InvaliAddressException invaliAddressException) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto( invaliAddressException.getMessage(), ADDRESS_INVALID);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ExceptionResponseDto>> handleInvalidArgument(MethodArgumentNotValidException exception) {
        List<ExceptionResponseDto> responseDTOList = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ExceptionResponseDto error = new ExceptionResponseDto(e.getField(), message);
            responseDTOList.add(error);
        });
        return ResponseEntity.badRequest().body(responseDTOList);
    }

}
