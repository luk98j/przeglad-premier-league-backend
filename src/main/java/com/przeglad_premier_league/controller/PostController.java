package com.przeglad_premier_league.controller;

import com.przeglad_premier_league.dto.PostDTO;
import com.przeglad_premier_league.model.post.Post;
import com.przeglad_premier_league.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@ControllerAdvice
@RequestMapping("/rest/api/public")
public class PostController {

    private final PostService postService;

    @PostMapping(value = "/upload")
    public ResponseEntity<String> uploadPost(@RequestBody PostDTO postDTO){
        if(postService.savePost(postDTO).getId() != 0){
            return ResponseEntity.ok("Post added");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getPosts(){
        return ResponseEntity.ok(postService.getPosts());
    }
}
