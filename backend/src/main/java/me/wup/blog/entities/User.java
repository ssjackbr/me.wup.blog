package me.wup.blog.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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

    @NotBlank(message  = "Required firstName field!")
    private String firstName;

    @NotBlank(message  = "Required lastName field!")
    private String lastName;

    @NotBlank(message  = "Required nickName field!")
    @Column (unique = true)
    private String nickName;

    @Column (unique = true)
    @NotBlank(message  = "Required email field!")
    @Email(message = "Required valid email!")
    private String email;

    @NotBlank(message  = "Required password field!")
    private String password;

    private int userType;
    private String userStatus = "ACTIVE";

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant dateRegistered;

    @PrePersist
    public void preCreated (){
        dateRegistered = Instant.now();
    }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_user_post",
    joinColumns = @JoinColumn (name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "post_id"))
    private Set<Post> post = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_user_role",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

}
