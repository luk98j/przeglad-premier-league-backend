package com.przeglad_premier_league.dto;

import lombok.Builder;

@Builder
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String password;
}
