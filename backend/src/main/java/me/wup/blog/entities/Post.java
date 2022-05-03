package me.wup.blog.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "tb_post")
public class Post implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size (min = 1, max = 40)
    private String title;

    @NotBlank
    @Size (min = 1, max = 40)
    private String author;

    @NotBlank
    @Lob
    private String content;

    @NotNull
    private Boolean status;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant createdAt;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant updateAt;

    @PrePersist
    public void preCreated (){
        createdAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate (){
        updateAt = Instant.now();
    }

    @ManyToOne (fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    public Post (Long id, String title, User user, String content, Boolean status, Instant createdAt, Instant updateAt) {

        this.id = id;
        this.title = title;
        this.author = user.getFirstName().concat(" "+user.getLastName());
        this.content = content;
        this.status = status;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    public void authorPost (User user){

    }

}
