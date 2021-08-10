package me.wup.blog.services;

import me.wup.blog.dto.UserDTO;
import me.wup.blog.entities.User;
import me.wup.blog.repositories.UserRepository;
import me.wup.blog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Optional;

@Service
public class UserService implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserDTO findById (Long id) {
        Optional<User> userObject = userRepository.findById(id);
        User entityUser = userObject.orElseThrow(() ->new ResourceNotFoundException("Entity not found!"));
        return new UserDTO(entityUser);
    }
}
