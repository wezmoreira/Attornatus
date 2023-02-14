package com.github.wezmoreira.attornatus.builder;

import com.github.wezmoreira.attornatus.dto.request.person.RequestPersonDto;

import java.util.Date;

public class RequestPersonBuilder {

    RequestPersonDto requestPersonDto;

    public RequestPersonBuilder() {
    }

    public static RequestPersonBuilder one() {
        RequestPersonBuilder builder = new RequestPersonBuilder();
        builder.requestPersonDto = new RequestPersonDto();

        builder.requestPersonDto.setName("Wesley");
        builder.requestPersonDto.setBirthDate(new Date(1997, 6, 20));
        builder.requestPersonDto.setAddress(RequestAddressBuilder.one().list());

        return builder;
    }

    public RequestPersonDto now() {
        return this.requestPersonDto;
    }
}
