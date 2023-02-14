package com.github.wezmoreira.attornatus.serviceTests;

import com.github.wezmoreira.attornatus.builder.AddressEntityBuilder;
import com.github.wezmoreira.attornatus.builder.PersonEntityBuilder;
import com.github.wezmoreira.attornatus.builder.RequestAddressBuilder;
import com.github.wezmoreira.attornatus.dto.request.address.RequestAddressDto;
import com.github.wezmoreira.attornatus.dto.response.address.ResponseAddressDto;
import com.github.wezmoreira.attornatus.exceptions.PersonNotFoundException;
import com.github.wezmoreira.attornatus.model.Address;
import com.github.wezmoreira.attornatus.model.Person;
import com.github.wezmoreira.attornatus.repositories.AddressRepository;
import com.github.wezmoreira.attornatus.repositories.PersonRepository;
import com.github.wezmoreira.attornatus.service.AddressService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = AddressService.class)
public class AddressServiceTest {

    @Autowired
    AddressService service;

    @SpyBean
    ModelMapper modelMapper;

    @MockBean
    PersonRepository personRepository;

    @MockBean
    AddressRepository addressRepository;

    private ResponseAddressDto responseAddress = new ResponseAddressDto();


    @Test
    @DisplayName("should successful get a person adressess")
    void shouldSuccessfulGetPersonAddress() {
        Person personsss = PersonEntityBuilder.one().now();
        Address address1 = AddressEntityBuilder.one().now();

        when(personRepository.getPersonByMainAddress(1L)).thenReturn(personsss);
        when(modelMapper.map(address1, ResponseAddressDto.class)).thenReturn(responseAddress);

        List<ResponseAddressDto> result = service.getPersonAddressService(1L);

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(4, result.size());
    }

    @Test
    @DisplayName("should throw a exception when try get a person adressess who not exists")
    void shouldThrowAExceptionWhenTryGetAPersonAddressWhoNotExists() {
        Assertions.assertThrows(PersonNotFoundException.class, () -> service.getPersonAddressService(1L));
    }

    @Test
    @DisplayName("should have a success adding a address to a person")
    void shouldHaveSuccessWhenTryAddAddressToAPerson() {
        RequestAddressDto requestAddressDto = RequestAddressBuilder.one().now();
        Person person = PersonEntityBuilder.one().now();
        Address address = AddressEntityBuilder.one().now();

        Mockito.when(personRepository.findById(any())).thenReturn(Optional.of(person));
        Mockito.when(addressRepository.save(any())).thenReturn(address);

        ResponseAddressDto responseAddressDto = service.addAddressToPersonService(1L, requestAddressDto);

        Assertions.assertNotNull(responseAddressDto);
        Assertions.assertEquals(address.getStreet(), "Avenida principal");
        Assertions.assertEquals(address.getCep(), responseAddressDto.getCep());
        Assertions.assertEquals(address.getNumber(), responseAddressDto.getNumber());
        Assertions.assertEquals(address.getCity(), responseAddressDto.getCity());

    }

    @Test
    @DisplayName("should have a success set mainAdrress to a address")
    void shouldHaveSuccessWhenTrySetMainAddressToAPersonAddress() {
        RequestAddressDto requestAddressDto = RequestAddressBuilder.one().now();
        Person person = PersonEntityBuilder.one().now();
        Address address = AddressEntityBuilder.one().now();

        Mockito.when(personRepository.findById(any())).thenReturn(Optional.of(person));
        Mockito.when(addressRepository.findById(any())).thenReturn(Optional.of(address));
        Mockito.when(addressRepository.save(any())).thenReturn(address);

        Assertions.assertFalse(address.isMainAddress());

        ResponseAddressDto responseAddressDto = service.setMainAddressService(requestAddressDto, address.getId(), person.getId());

        Assertions.assertNotNull(responseAddressDto);
        Assertions.assertTrue(responseAddressDto.isMainAddress());
    }
}
