package me.wup.blog.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToMany(mappedBy = "user",targetEntity = Post.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Post> posts ;

    public List<Post> getPosts() {
        return posts;
    }

}
