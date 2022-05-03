package me.wup.blog.controller;

import lombok.AllArgsConstructor;
import me.wup.blog.dto.PostDTO;
import me.wup.blog.services.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.Serializable;
import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping (value = "/posts")
public class PostController implements Serializable {
    private static final long serialVersionUID = 1L;

    private final PostService postService;

    @GetMapping
    public ResponseEntity<Page<PostDTO>> findAll(Pageable pageable) {
        Page<PostDTO> list = postService.findAll(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping (value = "/{id}")
    public ResponseEntity<PostDTO> findById (@PathVariable Long id){
        PostDTO dto  = postService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping(value = "/insert")
    public ResponseEntity<PostDTO> insertPost (@Valid @RequestBody PostDTO postDTO){
        PostDTO newPostDTO = postService.insertPost(postDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(newPostDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(newPostDTO);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<PostDTO> updatePost (@Valid @RequestBody PostDTO updatedContent,
                                            @PathVariable Long id){
        postService.updatePost(updatedContent, id);
        return ResponseEntity.ok().body(postService.updatePost(updatedContent, id));
    }

}
