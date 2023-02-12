package com.github.wezmoreira.attornatus.service;

import com.github.wezmoreira.attornatus.dto.request.address.RequestAddressDto;
import com.github.wezmoreira.attornatus.dto.response.address.ResponseAddressDto;
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


    public ResponseAddressDto addAddressToPersonService(Long id, RequestAddressDto request) {
        Person person = personRepository.findById(id).orElseThrow(NullPointerException::new);
        Address address = modelMapper.map(request, Address.class);
        List<Address> addressList = person.getAddress();

        addressList.add(address);
        person.setAddress(addressList);

        Address addressSave = addressrepository.save(address);
        return modelMapper.map(addressSave, ResponseAddressDto.class);
    }


    public List<ResponseAddressDto> getPersonAddressService(Long id) {
        Person person = personRepository.getPersonByMainAddress(id);
        if(person == null)
            throw new NullPointerException();

        return person.getAddress().stream()
                .map(a -> modelMapper.map(a, ResponseAddressDto.class)).collect(Collectors.toList());
    }


    public ResponseAddressDto setMainAddressService(RequestAddressDto requestAddress, Long idAddress, Long idPerson) {
        Person person = personRepository.findById(idPerson).orElseThrow(NullPointerException::new);

        var addressPerson = addressrepository.findPersonByMainAddress(person.getId());
        if(addressPerson != null)
            addressPerson.setMainAddress(false);

        Address address = addressrepository.findById(idAddress).orElseThrow(NullPointerException::new);
        address.setMainAddress(true);
        addressrepository.save(address);
        return modelMapper.map(address, ResponseAddressDto.class);
    }
}
