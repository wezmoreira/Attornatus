package com.github.wezmoreira.attornatus.controller;

import com.github.wezmoreira.attornatus.dto.request.address.RequestAddressDto;
import com.github.wezmoreira.attornatus.dto.response.address.ResponseAddressDto;
import com.github.wezmoreira.attornatus.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/address")
public class AddressController {

    @Autowired
    private AddressService service;

    @PostMapping("/{id}/person")
    public ResponseEntity<ResponseAddressDto> AddAddressToPerson(@PathVariable Long id, @RequestBody RequestAddressDto request){
        ResponseAddressDto personAddress = service.addAddressToPersonService(id, request);
        return ResponseEntity.ok(personAddress);
    }

    @GetMapping("/{id}/person")
    public ResponseEntity<List<ResponseAddressDto>> getCartao(@PathVariable Long id) {
        List<ResponseAddressDto> personAddress = service.getPersonAddressService(id);
        return ResponseEntity.ok(personAddress);
    }

    @PutMapping("/{idAddress}/person/{idPerson}")
    public  ResponseEntity<ResponseAddressDto> setMainAddressPerson(@RequestBody @Valid RequestAddressDto requestAddress, @PathVariable Long idAddress,
                                                                    @PathVariable Long idPerson) {
        ResponseAddressDto addressDto = service.setMainAddressService(requestAddress, idAddress, idPerson);
        return ResponseEntity.ok(addressDto);
    }
}
