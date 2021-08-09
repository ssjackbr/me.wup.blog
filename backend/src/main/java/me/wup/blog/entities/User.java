package me.wup.blog.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.*;

@Entity
@Table(name = "tb_user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String displayName;

    @Column (unique = true)
    private String login;

    @Column (unique = true)
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


    @OneToMany (mappedBy = "user")
    private List<Post> post = new ArrayList<>();

    public User() {
    }

    public User(Long id, String userName, String displayName, String login, String email, String password,
                int userType, String userStatus, Instant dateRegistered) {
        this.id = id;
        this.userName = userName;
        this.displayName = displayName;
        this.login = login;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.userStatus = userStatus;
        this.dateRegistered = dateRegistered;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
