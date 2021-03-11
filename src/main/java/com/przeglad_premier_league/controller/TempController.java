package com.przeglad_premier_league.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rest/api")
public class TempController {

    @GetMapping("/temp")
    public ResponseEntity getTemp(){
        return ResponseEntity.ok("Dziala komunikacja");
    }
}
