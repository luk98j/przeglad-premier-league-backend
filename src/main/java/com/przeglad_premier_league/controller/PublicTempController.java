package com.przeglad_premier_league.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/public")
public class PublicTempController {

    @GetMapping("/temp")
    public ResponseEntity<String> getPublicTemp(){
        return ResponseEntity.ok().body("Nieautoryzowany request");
    }
}
