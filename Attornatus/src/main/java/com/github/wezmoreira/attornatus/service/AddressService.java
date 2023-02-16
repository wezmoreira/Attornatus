package com.github.wezmoreira.attornatus.service;

import com.github.wezmoreira.attornatus.dto.request.address.RequestAddressDto;
import com.github.wezmoreira.attornatus.dto.response.address.ResponseAddressDto;
import com.github.wezmoreira.attornatus.exceptions.AddressNotFoundException;
import com.github.wezmoreira.attornatus.exceptions.InvaliAddressException;
import com.github.wezmoreira.attornatus.exceptions.PersonNotFoundException;
import com.github.wezmoreira.attornatus.model.Address;
import com.github.wezmoreira.attornatus.model.Person;
import com.github.wezmoreira.attornatus.repositories.AddressRepository;
import com.github.wezmoreira.attornatus.repositories.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AddressRepository addressrepository;


    /**
     * Adiciona um novo Address a um Person.
     */
    public ResponseAddressDto addAddressToPersonService(Long id, RequestAddressDto request) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        Address address = modelMapper.map(request, Address.class);
        List<Address> addressList = person.getAddress();

        addressList.add(address);
        person.setAddress(addressList);

        Address addressSave = addressrepository.save(address);
        return modelMapper.map(addressSave, ResponseAddressDto.class);
    }


    /**
     * Retorna todos os Address de um Person, ordenado pelo endereço principal.
     */
    public List<ResponseAddressDto> getPersonAddressService(Long id) {
        Person person = personRepository.getPersonByMainAddress(id);
        if(person == null)
            throw new PersonNotFoundException();

        return person.getAddress().stream()
                .map(a -> modelMapper.map(a, ResponseAddressDto.class)).collect(Collectors.toList());
    }


    /**
     * Seleciona um novo endereço principal, pode haver apenas um único endereço principal então o antigo endereço
     * voltara ao status normal!
     */
    public ResponseAddressDto setMainAddressService(Long idAddress, Long idPerson) {
        Person person = personRepository.findById(idPerson).orElseThrow(PersonNotFoundException::new);
        Address address = addressrepository.findById(idAddress).orElseThrow(AddressNotFoundException::new);

        boolean addressExists = verifyAddressExist(person, idAddress);
        if (!addressExists)
            throw new InvaliAddressException();

        var addressPerson = addressrepository.findPersonByMainAddress(person.getId());
        if(addressPerson != null)
            addressPerson.setMainAddress(false);

        address.setMainAddress(true);
        addressrepository.save(address);
        return modelMapper.map(address, ResponseAddressDto.class);
    }


    /**
     * Verifica se o endereço pertence ao Person.
     */
    private boolean verifyAddressExist(Person person, Long idAddress) {
        List<Address> addressList = person.getAddress();
        boolean addressExists = false;
        for (var a : addressList) {
            if(a.getId().equals(idAddress)){
                addressExists = true;
                break;
            }
        }
        return addressExists;
    }
}
