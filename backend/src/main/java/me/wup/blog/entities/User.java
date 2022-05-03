package me.wup.blog.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private String firstName;
    private String lastName;
    private String nickName;

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

    @OneToMany(mappedBy = "user",targetEntity = Post.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Post> posts ;

    @ManyToMany
    @JoinTable(name = "tb_user_role",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public List<Post> getPosts() {
        return posts;
    }

}
