package com.github.wezmoreira.attornatus.builder;

import com.github.wezmoreira.attornatus.model.Address;

import java.util.ArrayList;
import java.util.List;

public class AddressEntityBuilder {

    Address address;

    public AddressEntityBuilder() {
    }

    public static AddressEntityBuilder one(){
        AddressEntityBuilder builder = new AddressEntityBuilder();
        builder.address = new Address();

        builder.address.setId(1L);
        builder.address.setCep("11122233344");
        builder.address.setCity("Sarandi");
        builder.address.setNumber("10");
        builder.address.setStreet("Avenida principal");

        return builder;
    }

    public List<Address> list() {
        List<Address> addressList = new ArrayList<>();
        addressList.add(this.address);
        addressList.add(this.address);
        addressList.add(this.address);
        addressList.add(this.address);
        return addressList;
    }

    public Address now() {
        return this.address;
    }
}
