package me.wup.blog.dto;

import lombok.*;
import me.wup.blog.entities.Post;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class PostDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 100)
    private String title;

    @Size (min = 1, max = 100)
    private String author;

    @NotBlank
    @Lob
    private String content;

    private String imageUrl;

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

     public PostDTO (Post post) {
         this.id = post.getId();
         this.title = post.getTitle();
         this.author = post.getAuthor();
         this.content = post.getContent();
         this.imageUrl = post.getImageUrl();
         this.status = post.getStatus();
         this.createdAt = post.getCreatedAt();
         this.updateAt = post.getUpdateAt();
     }

}
