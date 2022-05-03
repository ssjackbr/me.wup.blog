package me.wup.blog.services;

import lombok.AllArgsConstructor;
import me.wup.blog.dto.PostDTO;
import me.wup.blog.dto.RoleDTO;
import me.wup.blog.dto.UserDTO;
import me.wup.blog.dto.UserInsertDTO;
import me.wup.blog.entities.Post;
import me.wup.blog.entities.Role;
import me.wup.blog.entities.User;
import me.wup.blog.repositories.PostRepository;
import me.wup.blog.repositories.RoleRepository;
import me.wup.blog.repositories.UserRepository;
import me.wup.blog.services.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService implements Serializable {
    private static final long serialVersionUID = 1L;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PostRepository postRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public Page<UserDTO> findAllUsersPaged(Pageable pageable) {
        Page<User> list = userRepository.findAll(pageable);
        return list.map(user -> new UserDTO(user));
    }

    @Transactional(readOnly = true)
    public UserDTO findUserById(Long id) {
        Optional<User> userObject = userRepository.findById(id);
        User entityUser = userObject.orElseThrow(() ->new ResourceNotFoundException("ERROR: Entity not found!"));
        return new UserDTO(entityUser);
    }

    @Transactional
    public UserDTO insertUser (UserInsertDTO userDTO){
        User userEntity = new User();
        convertUSerDtoToUserEntity(userDTO,userEntity);
        userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return new UserDTO(userRepository.save(userEntity));
    }

    @Transactional
    public UserDTO updateUser(UserDTO userDTO, Long id){
        try {
            Optional<User> userEntity = userRepository.findById(id);
            User user = userEntity.orElseThrow(() ->new ResourceNotFoundException("ERROR: Entity not found!"));
            convertUSerDtoToUserEntity(userDTO, user);
            return new UserDTO(userRepository.save(user));
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("ERROR: User ID not found: "+ id);
        }
    }

    public void deleteUser (Long id){
        try{
            userRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e ){
            throw new ResourceNotFoundException("ERROR: User ID not found: "+ id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("ERROR: Integrity violation");
        }
    }

    private void convertUSerDtoToUserEntity(UserDTO userDTO, User userEntity) {

        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setUserStatus(userDTO.getUserStatus());
        userEntity.setUserType(userDTO.getUserType());
        userEntity.setNickName(userDTO.getNickName());
        userEntity.getRoles().clear();

        for (RoleDTO roleDTO : userDTO.getRoles()){
            Role role = roleRepository.getById(roleDTO.getId());
            userEntity.getRoles().add(role);
        }

        for (PostDTO postDTO : userDTO.getPosts()){
            Post post = postRepository.getById(postDTO.getId());
            userEntity.getPost().add(post);
        }

    }

}
