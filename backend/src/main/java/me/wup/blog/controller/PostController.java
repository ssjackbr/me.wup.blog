package me.wup.blog.controller;

import lombok.AllArgsConstructor;
import me.wup.blog.dto.PostDTO;
import me.wup.blog.entities.Post;
import me.wup.blog.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping (value = "/post")
public class PostController implements Serializable {
    private static final long serialVersionUID = 1L;

    private final PostService postService;

    @GetMapping
    public List<PostDTO> findAllPosts() {
        return postService.findAll();
    }

    @GetMapping (value = "/{id}")
    public ResponseEntity findById (@PathVariable Long id){
        PostDTO dto  = postService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping(value = "/new")
    public ResponseEntity<Post> insertNewPost (@RequestBody PostDTO postDTO){
        return postService.saveNewPost(postDTO);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Post> updatePost (@RequestBody PostDTO updatedContent,
                                            @PathVariable Long id){
        return postService.updatePost(updatedContent, id);
    }

}
