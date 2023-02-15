package com.github.wezmoreira.attornatus.controller;

import com.github.wezmoreira.attornatus.dto.request.person.RequestPersonDto;
import com.github.wezmoreira.attornatus.dto.request.person.RequestUpdatePersonDto;
import com.github.wezmoreira.attornatus.dto.response.person.ResponsePersonDto;
import com.github.wezmoreira.attornatus.service.PersonService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("v1/api/person")
public class PersonController {

    @Autowired
    private PersonService service;

    @PostMapping
    @Transactional
    public ResponseEntity<ResponsePersonDto> createPerson(@RequestBody @Valid RequestPersonDto requestPersonDto,
                                                          UriComponentsBuilder uriComponentsBuilder){
        ResponsePersonDto personDto = service.createPersonService(requestPersonDto);
        URI uri = uriComponentsBuilder.path("/api/pedidos/{id}").buildAndExpand(personDto.getId()).toUri();
        return ResponseEntity.created(uri).body(personDto);
    }

    @GetMapping
    public ResponseEntity<List<ResponsePersonDto>> getAllPerson(){
        List<ResponsePersonDto> personDtoList = service.getAllPersonService();
        return ResponseEntity.ok(personDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePersonDto> getPersonById(@PathVariable @NotNull Long id){
        ResponsePersonDto person = service.getPersonByIdService(id);
        return ResponseEntity.ok(person);
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponsePersonDto> updatePersonById(@PathVariable @NotNull Long id, @RequestBody RequestUpdatePersonDto request){
        ResponsePersonDto person = service.updatePersonService(id, request);
        return ResponseEntity.ok(person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonById(@PathVariable Long id){
        service.deletePersonService(id);
        return ResponseEntity.noContent().build();
    }

}
