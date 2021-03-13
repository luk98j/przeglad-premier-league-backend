package com.przeglad_premier_league.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/private")
public class TempController {

    @GetMapping("/temp")
    public ResponseEntity<String> getTemp(){
        return ResponseEntity.ok().body("Autoryzowany request");
    }


}
