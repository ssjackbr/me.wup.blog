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
        return postList.stream().map(PostDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public ResponseEntity<Post> saveNewPost (PostDTO postDTO){

            if (postDTO.getContent() == null || postDTO.getContent().isBlank() || postDTO.getContent().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

        return ResponseEntity.ok().body(postRepository.save(convertDtoToEntity(postDTO)));
    }

    @Transactional
    public ResponseEntity<Post> updatePost (PostDTO updatedContent, Long id) {
        Post postToUpdated = postRepository.getById(id);
        postToUpdated.setTitle(updatedContent.getTitle());
        postToUpdated.setContent(updatedContent.getContent());
        postToUpdated.setStatus(updatedContent.getStatus());
        return ResponseEntity.ok().body(postRepository.save(postToUpdated));
    }

    private Post convertDtoToEntity (PostDTO postDTO){
        Post newPost = new Post();
        newPost.setContent(postDTO.getContent());
        newPost.setTitle(postDTO.getTitle());
        newPost.setStatus(postDTO.getStatus());
        return newPost;
    }

}
