package me.wup.blog.services;


import lombok.AllArgsConstructor;
import me.wup.blog.dto.PostDTO;
import me.wup.blog.entities.Post;
import me.wup.blog.repositories.PostRepository;
import me.wup.blog.services.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class PostService implements Serializable {
    private static final long serialVersionUID = 1L;

    private final PostRepository postRepository;


    @Transactional(readOnly = true)
    public PostDTO findById(Long id) {
        Optional<Post> postObject = postRepository.findById(id);
         Post entity = postObject.orElseThrow(() -> new ResourceNotFoundException("Entity not found!"));
        return new PostDTO(entity);
    }

    @Transactional(readOnly = true)
    public List<PostDTO> findAll (){
        List<Post> postList = postRepository.findAll();
        return postList.stream().map( post -> {
            return new PostDTO(post);
        }).collect(Collectors.toList());
    }

    public ResponseEntity<PostDTO> saveNewPost (PostDTO postDTO){
        if (postDTO == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(postDTO);
    }

}
