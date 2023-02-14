package com.github.wezmoreira.attornatus.builder;

import com.github.wezmoreira.attornatus.dto.request.address.RequestAddressDto;

import java.util.ArrayList;
import java.util.List;

public class RequestAddressBuilder {

    RequestAddressDto requestAddressDto;

    public RequestAddressBuilder() {
    }

    public static RequestAddressBuilder one(){
        RequestAddressBuilder builder = new RequestAddressBuilder();
        builder.requestAddressDto = new RequestAddressDto();

        builder.requestAddressDto.setCep("11122233344");
        builder.requestAddressDto.setCity("Sarandi");
        builder.requestAddressDto.setNumber("10");
        builder.requestAddressDto.setStreet("Avenida principal");

        return builder;
    }

    public List<RequestAddressDto> list() {
        List<RequestAddressDto> requestAddressDtos = new ArrayList<>();
        requestAddressDtos.add(this.requestAddressDto);
        requestAddressDtos.add(this.requestAddressDto);
        requestAddressDtos.add(this.requestAddressDto);
        requestAddressDtos.add(this.requestAddressDto);
        return requestAddressDtos;
    }

    public RequestAddressDto now() {
        return this.requestAddressDto;
    }
}
