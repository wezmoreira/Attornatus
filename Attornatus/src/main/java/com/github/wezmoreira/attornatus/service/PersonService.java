package com.github.wezmoreira.attornatus.service;

import com.github.wezmoreira.attornatus.dto.request.person.RequestPersonDto;
import com.github.wezmoreira.attornatus.dto.request.person.RequestUpdatePersonDto;
import com.github.wezmoreira.attornatus.dto.response.person.ResponsePersonDto;
import com.github.wezmoreira.attornatus.exceptions.PersonNotFoundException;
import com.github.wezmoreira.attornatus.model.Address;
import com.github.wezmoreira.attornatus.model.Person;
import com.github.wezmoreira.attornatus.repositories.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PersonRepository repository;


    /**
     * Cria um Person onde o primeiro endereço informado é selecionado como principal.
     */
    public ResponsePersonDto createPersonService(RequestPersonDto requestPersonDto) {
        Person person = modelMapper.map(requestPersonDto, Person.class);
        List<Address> addresses = person.getAddress();
        if (!addresses.isEmpty()) {
            addresses.get(0).setMainAddress(true);
        }

        repository.save(person);
        return modelMapper.map(person, ResponsePersonDto.class);
    }

    /**
     * Retorna todos Persons com apenas o endereço principal.
     */
    public List<ResponsePersonDto> getAllPersonService() {
        return repository.findAllPersonByMainAddress().stream()
                .map(p -> modelMapper.map(p, ResponsePersonDto.class))
                .collect(Collectors.toList());
    }

    /**
     * Retorna um Person pelo "id".
     */
    public ResponsePersonDto getPersonByIdService(Long id) {
        Person person = repository.findById(id).orElseThrow(PersonNotFoundException::new);
        return modelMapper.map(person, ResponsePersonDto.class);
    }

    /**
     * Atualiza uma propriedade ou todas de um Person.
     */
    public ResponsePersonDto updatePersonService(Long id, RequestUpdatePersonDto request) {
        Person person = repository.findById(id).orElseThrow(PersonNotFoundException::new);
        modelMapper.map(request, person);
        Person personSaved = repository.save(person);
        return modelMapper.map(personSaved, ResponsePersonDto.class);
    }

    /**
     * Deleta um person pelo "Id".
     */
    public void deletePersonService(Long id) {
        Person person = repository.findById(id).orElseThrow(PersonNotFoundException::new);
        repository.delete(person);
    }
}
