package me.wup.blog.dto;

import lombok.*;
import me.wup.blog.entities.User;

import javax.persistence.Column;
import javax.persistence.PrePersist;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message  = "Campo obrigatório!")
    private String firstName;

    @NotBlank(message  = "Campo obrigatório!")
    private String lastName;

    @Column (unique = true)
    private String nickName;

    @Column (unique = true)
    @Email(message = "Insira um e-mail válido!")
    private String email;

    private int userType;
    private String userStatus;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant dateRegistered;

    Set<RoleDTO> roles = new HashSet<>();

    Set<PostDTO> posts = new HashSet<>();

    public UserDTO(User entityUser) {
        id = entityUser.getId();
        firstName = entityUser.getFirstName();
        lastName = entityUser.getLastName();
        nickName = entityUser.getNickName();
        email = entityUser.getEmail();
        userStatus = entityUser.getUserStatus();
        userType = entityUser.getUserType();
        dateRegistered = entityUser.getDateRegistered();
        entityUser.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
        entityUser.getPost().forEach(post -> this.posts.add(new PostDTO(post)));
    }

    @PrePersist
    public void preCreated (){
        dateRegistered = Instant.now();
    }

}
