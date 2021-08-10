package me.wup.blog.controller;

import me.wup.blog.dto.UserDTO;
import me.wup.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
@RequestMapping (value = "/users")
public class UserController  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private UserService userService;

    @GetMapping (value = "/{id}")
    public ResponseEntity findById (@PathVariable Long id){
        UserDTO userDto = userService.findById(id);
        return ResponseEntity.ok().body(userDto);
    }

}
