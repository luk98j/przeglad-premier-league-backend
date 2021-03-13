package com.przeglad_premier_league.config;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {
    @NotNull
    private String userName;
    @NotNull
    private String password;
}
