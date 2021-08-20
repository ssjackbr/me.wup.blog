package me.wup.blog.services;


import me.wup.blog.dto.PostDTO;;
import me.wup.blog.entities.Post;
import me.wup.blog.repositories.PostRepository;
import me.wup.blog.repositories.UserRepository;
import me.wup.blog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Optional;

@Service
public class PostService implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public PostDTO findById(Long id) {
        Optional<Post> postObject = postRepository.findById(id);
         Post entity = postObject.orElseThrow(() -> new ResourceNotFoundException("Entity not found!"));
        return new PostDTO(entity);
    }

}
