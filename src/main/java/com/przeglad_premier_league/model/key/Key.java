package com.przeglad_premier_league.model.key;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "keys_table")
@Data
public class Key {
    @Id
    private int id;
    private int keyId;
    private String year;
    private boolean isDownload;
}
