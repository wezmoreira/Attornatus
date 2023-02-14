package com.github.wezmoreira.attornatus.builder;

import com.github.wezmoreira.attornatus.dto.request.address.RequestAddressDto;
import com.github.wezmoreira.attornatus.dto.request.person.RequestPersonDto;
import com.github.wezmoreira.attornatus.model.Address;
import com.github.wezmoreira.attornatus.model.Person;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PersonEntityBuilder {

    Person person;

    public PersonEntityBuilder() {
    }

    public static PersonEntityBuilder one() {
        PersonEntityBuilder builder = new PersonEntityBuilder();
        builder.person = new Person();

        builder.person.setId(1L);
        builder.person.setName("Wesley");
        builder.person.setBirthDate(new Date(1997, 6, 20));
        builder.person.setAddress(AddressEntityBuilder.one().list());

        return builder;
    }

    public Person now() {
        return this.person;
    }
}
