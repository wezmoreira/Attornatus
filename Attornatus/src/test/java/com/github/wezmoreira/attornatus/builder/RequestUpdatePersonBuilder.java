package com.github.wezmoreira.attornatus.builder;

import com.github.wezmoreira.attornatus.dto.request.person.RequestUpdatePersonDto;

import java.util.Date;

public class RequestUpdatePersonBuilder {

    RequestUpdatePersonDto requestUpdatePersonDto;

    public RequestUpdatePersonBuilder() {
    }

    public static RequestUpdatePersonBuilder one(){
        RequestUpdatePersonBuilder builder = new RequestUpdatePersonBuilder();
        builder.requestUpdatePersonDto = new RequestUpdatePersonDto();
        builder.requestUpdatePersonDto.setName("Wesley Update");
        builder.requestUpdatePersonDto.setBirthDate(new Date(1998, 6, 20));
        return builder;
    }

    public RequestUpdatePersonDto now() {
        return this.requestUpdatePersonDto;
    }

}
