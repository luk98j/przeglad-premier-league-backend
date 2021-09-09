package com.przeglad_premier_league.model.authentication;

import javax.persistence.*;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column
    private ERole name;

    public Role() {
    }

    public Role(ERole name) {
        this.name = name;
    }
}