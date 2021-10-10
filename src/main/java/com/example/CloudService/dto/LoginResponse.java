package com.example.CloudService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Setter
public class LoginResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;

    private String token;

    @JsonProperty("auth-token")
    public String getToken() {
        return token;
    }
}
