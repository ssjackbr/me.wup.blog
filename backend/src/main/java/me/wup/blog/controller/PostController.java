package me.wup.blog.controller;

import me.wup.blog.dto.PostDTO;
import me.wup.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
@RequestMapping (value = "/posts")
public class PostController implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private PostService postService ;

    @GetMapping (value = "/{id}")
    public ResponseEntity findById (@PathVariable Long id){
        PostDTO dto  = postService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

}
