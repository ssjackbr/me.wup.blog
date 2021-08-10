package me.wup.blog.dto;

import me.wup.blog.entities.Post;
import me.wup.blog.entities.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.*;

public class UserDTO implements Serializable {
        private static final long serialVersionUID = 1L;

        private Long id;

        @NotBlank(message  = "Campo obrigatório!")
        private String userName;

        @NotBlank(message  = "Campo obrigatório!")
        private String displayName;

        @Column (unique = true)
        private String login;

        @Column (unique = true)
        @Email(message = "Insira um e-mail válido!")
        private String email;

        private String password;
        private int userType;
        private String userStatus;

        @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
        private Instant dateRegistered;

        @PrePersist
        public void preCreated (){
            dateRegistered = Instant.now();
        }

        // Criar PostDTO para aplicar o set
        private List<Post> posts = new ArrayList<>();

        public UserDTO() {
        }

        public UserDTO(Long id, @NotBlank(message = "Campo obrigatório!") String userName,
                   @NotBlank(message = "Campo obrigatório!") String displayName,
                   String login, @Email(message = "Insira um e-mail válido!") String email,
                   int userType, String userStatus, Instant dateRegistered) {
        this.id = id;
        this.userName = userName;
        this.displayName = displayName;
        this.login = login;
        this.email = email;
        this.userType = userType;
        this.userStatus = userStatus;
        this.dateRegistered = dateRegistered;
    }

    public UserDTO (User user) {
        id = user.getId();
        userName = user.getUserName();
        displayName = user.getDisplayName();
        login = user.getLogin();
        email = user.getEmail();
        userType = user.getUserType();
        userStatus = user.getUserStatus();
        dateRegistered = user.getDateRegistered();
        user.getPost().forEach(post -> this.posts.add(new Post(post)));
    }

    public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public String getUserStatus() {
            return userStatus;
        }

        public void setUserStatus(String userStatus) {
            this.userStatus = userStatus;
        }

        public Instant getDateRegistered() {
            return dateRegistered;
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
