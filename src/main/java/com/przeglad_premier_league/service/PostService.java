package com.przeglad_premier_league.service;

import com.przeglad_premier_league.dto.PostDTO;
import com.przeglad_premier_league.model.post.Post;
import com.przeglad_premier_league.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post savePost(PostDTO postDTO){
        Timestamp ts=new Timestamp(Long.parseLong(postDTO.getDate()));
        Date date=new Date(ts.getTime());
        Post post =  Post.builder()
                .title(postDTO.getTitle())
                .text(postDTO.getText())
                .date(date)
                .build();

        return postRepository.save(post);
    }

    public List<Post> getPosts(){
        return postRepository.findAllByOrderByIdDesc();
    }
}
