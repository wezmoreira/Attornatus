package com.github.wezmoreira.attornatus.serviceTests;

import com.github.wezmoreira.attornatus.builder.PersonEntityBuilder;
import com.github.wezmoreira.attornatus.builder.RequestAddressBuilder;
import com.github.wezmoreira.attornatus.builder.RequestPersonBuilder;
import com.github.wezmoreira.attornatus.builder.RequestUpdatePersonBuilder;
import com.github.wezmoreira.attornatus.dto.request.address.RequestAddressDto;
import com.github.wezmoreira.attornatus.dto.request.person.RequestPersonDto;
import com.github.wezmoreira.attornatus.dto.request.person.RequestUpdatePersonDto;
import com.github.wezmoreira.attornatus.dto.response.person.ResponsePersonDto;
import com.github.wezmoreira.attornatus.exceptions.PersonNotFoundException;
import com.github.wezmoreira.attornatus.model.Person;
import com.github.wezmoreira.attornatus.repositories.PersonRepository;
import com.github.wezmoreira.attornatus.service.PersonService;
import org.hibernate.mapping.Any;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(classes = PersonService.class)
public class PersonServiceTest {

    @Autowired
    PersonService service;

    @SpyBean
    ModelMapper modelMapper;

    @MockBean
    PersonRepository repository;


    @Test
    @DisplayName("should have success when try create a person")
    void shouldCreateASuccessfulPerson() {
        RequestPersonDto requestPersonDto = RequestPersonBuilder.one().now();
        Person person = PersonEntityBuilder.one().now();

        Mockito.when(repository.save(any())).thenReturn(person);

        ResponsePersonDto responsePersonDto = service.createPersonService(requestPersonDto);

        Assertions.assertEquals(person.getName(), responsePersonDto.getName());
        Assertions.assertEquals(person.getBirthDate(), responsePersonDto.getBirthDate());
        Assertions.assertEquals(person.getAddress().get(0).getNumber(), responsePersonDto.getAddress().get(0).getNumber());
    }

    @Test
    @DisplayName("should successful update a person")
    void shouldHasSuccessWhenTryUpdateAPerson(){
        RequestPersonDto requestPersonDto = RequestPersonBuilder.one().now();
        Person person = PersonEntityBuilder.one().now();

        RequestUpdatePersonDto personUpdate = RequestUpdatePersonBuilder.one().now();

        Mockito.when(repository.findById(any())).thenReturn(Optional.of(person));
        Mockito.when(repository.save(any())).thenReturn(person);

        ResponsePersonDto responseUserDTO = service.updatePersonService(1L, personUpdate);

        Assertions.assertEquals(person.getName(), responseUserDTO.getName());
        Assertions.assertEquals(person.getName(), "Wesley Update");
        Assertions.assertEquals(person.getBirthDate(), responseUserDTO.getBirthDate());
        Assertions.assertEquals(person.getBirthDate(), new Date(1998, 6, 20));
    }

    @Test
    @DisplayName("should throw an exception when try update a person who not exists")
    void shouldThrowAnExceptionWhenTryToUpdateAPersonWhoNotExists(){
        RequestUpdatePersonDto personUpdate = RequestUpdatePersonBuilder.one().now();

        Assertions.assertThrows(PersonNotFoundException.class, () -> service.updatePersonService(1L, personUpdate));
    }

    @Test
    @DisplayName("should throw an exception when try delete a person who not exists")
    void shouldThrowAnExceptionWhenTryDeleteAPersonWhoNotExists(){
        RequestUpdatePersonDto personUpdate = RequestUpdatePersonBuilder.one().now();

        Assertions.assertThrows(PersonNotFoundException.class, () -> service.deletePersonService(1L));
    }

    @Test
    @DisplayName("should have success when try get a person by id")
    void shouldHaveSuccessWhenTryGetAPersonById(){
        Person person = PersonEntityBuilder.one().now();

        Mockito.when(repository.findById(any())).thenReturn(Optional.of(person));
        Mockito.when(repository.save(any())).thenReturn(person);

        ResponsePersonDto responsePersonDto = service.getPersonByIdService(person.getId());

        Assertions.assertEquals(responsePersonDto.getBirthDate(), new Date(1997, 6, 20));
        Assertions.assertEquals(responsePersonDto.getName(), "Wesley");
    }

    @Test
    @DisplayName("should have success when get all persons")
    void shouldHaveSuccessWhenGetAllPersons(){
        Mockito.when(repository.findAllPersonByMainAddress()).thenReturn(Arrays.asList(PersonEntityBuilder.one().now()));
        List<ResponsePersonDto> userList = service.getAllPersonService();
        Assertions.assertNotNull(userList);
        Assertions.assertEquals(1, userList.size());
        Assertions.assertEquals("Wesley", userList.get(0).getName());
    }
}
