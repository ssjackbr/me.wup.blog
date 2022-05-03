package me.wup.blog.controller;

import lombok.AllArgsConstructor;
import me.wup.blog.dto.UserDTO;
import me.wup.blog.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@AllArgsConstructor
@RestController
@RequestMapping (value = "/users")
public class UserController  implements Serializable {
    private static final long serialVersionUID = 1L;

    private final UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserDTO>> findAll(Pageable pageable) {
        Page<UserDTO> list = userService.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping (value = "/{id}")
    public ResponseEntity findById (@PathVariable Long id){
        UserDTO userDto = userService.findById(id);
        return ResponseEntity.ok().body(userDto);
    }

}
