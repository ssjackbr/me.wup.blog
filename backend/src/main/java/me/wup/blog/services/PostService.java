package me.wup.blog.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import me.wup.blog.dto.PostDTO;
import me.wup.blog.entities.Post;
import me.wup.blog.repositories.PostRepository;
import me.wup.blog.services.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService implements Serializable {
    private static final long serialVersionUID = 1L;

    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public PostDTO findById(Long id) {
        Optional<Post> postObject = postRepository.findById(id);
         Post entity = postObject.orElseThrow(() -> new ResourceNotFoundException("ERROR: Entity not found!"));
        return new PostDTO(entity);
    }


    @Transactional(readOnly = true)
    public Page<PostDTO> findAll(Pageable pageable){
        Page<Post> postList = postRepository.findAll(pageable);
        return postList.map(post -> new PostDTO(post));
    }

    @Transactional
    public PostDTO insertPost (PostDTO postDTO){

            if (postDTO.getContent() == null || postDTO.getContent().isBlank() || postDTO.getContent().isEmpty()) {
                throw new ResourceNotFoundException("ERROR: Entity not found!");
            }

        return new PostDTO(postRepository.save(convertDtoToEntity(postDTO)));
    }

    @Transactional
    public PostDTO updatePost (PostDTO updatedContent, Long id) {

        Optional<Post> entityPost = Optional.of(postRepository.getById(id));
        Post postToUpdated = entityPost.orElseThrow(() ->new ResourceNotFoundException("ERROR: Entity not found!"));

        postToUpdated.setTitle(updatedContent.getTitle());
        postToUpdated.setAuthor(updatedContent.getAuthor());
        postToUpdated.setContent(updatedContent.getContent());
        postToUpdated.setStatus(updatedContent.getStatus());
        postToUpdated.setImageUrl(updatedContent.getImageUrl());

        return new PostDTO(postRepository.save(postToUpdated));
    }

    private Post convertDtoToEntity (PostDTO postDTO){
        Post newPost = new Post();
        newPost.setContent(postDTO.getContent());
        newPost.setTitle(postDTO.getTitle());
        newPost.setAuthor(postDTO.getAuthor());
        newPost.setStatus(postDTO.getStatus());
        newPost.setImageUrl(postDTO.getImageUrl());
        return newPost;
    }

}
