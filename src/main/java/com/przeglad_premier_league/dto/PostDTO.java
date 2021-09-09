package com.przeglad_premier_league.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Getter
@Setter
public class PostDTO {
    private String title;
    private String text;
    private String date;

    public PostDTO(String title, String text, String date, String fileId) {
        this.title = title;
        this.text = text;
        this.date = date;
    }

    public PostDTO() {
    }
}
