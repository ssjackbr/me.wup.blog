package me.wup.blog.dto;

import lombok.*;
import me.wup.blog.entities.Post;
import me.wup.blog.entities.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.*;

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

    public UserDTO(User entityUser, List<Post> posts) {
    }

    public UserDTO(User entityUser) {
    }

    @PrePersist
        public void preCreated (){
            dateRegistered = Instant.now();
        }

        // Criar PostDTO para aplicar o set
        private List<Post> posts = new ArrayList<>();

        public UserDTO(Long id, @NotBlank(message = "Campo obrigatório!") String firstName,
                       @NotBlank(message = "Campo obrigatório!") String lastName,
                       String nickName, @Email(message = "Insira um e-mail válido!") String email,
                       int userType, String userStatus, Instant dateRegistered) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.email = email;
        this.userType = userType;
        this.userStatus = userStatus;
        this.dateRegistered = dateRegistered;
    }

}
