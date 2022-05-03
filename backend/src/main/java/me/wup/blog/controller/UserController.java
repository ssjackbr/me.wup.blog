package me.wup.blog.controller;

import lombok.AllArgsConstructor;
import me.wup.blog.dto.UserDTO;
import me.wup.blog.dto.UserInsertDTO;
import me.wup.blog.services.UserService;
import org.hibernate.annotations.common.reflection.XMethod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.Serializable;
import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping (value = "/users")
public class UserController  implements Serializable {
    private static final long serialVersionUID = 1L;

    private final UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserDTO>> findAll(Pageable pageable) {
        Page<UserDTO> list = userService.findAllUsersPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping (value = "/{id}")
    public ResponseEntity<UserDTO> findById (@PathVariable Long id){
        UserDTO userDto = userService.findUserById(id);
        return ResponseEntity.ok().body(userDto);
    }

    @PostMapping(value = "/insert")
    public ResponseEntity<UserDTO> insertUser (@RequestBody UserInsertDTO userDTO){
        UserDTO newUserDTO = userService.insertUser(userDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(newUserDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(newUserDTO);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<UserDTO> updateUser (@RequestBody UserDTO userDTO, @PathVariable Long id){
        return ResponseEntity.ok().body(userService.updateUser(userDTO,id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
