package me.wup.blog.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.Instant;

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

    @NotBlank(message  = "Required title field!")
    private String title;

    @NotBlank(message  = "Required author field!")
    private String author;

    @NotBlank(message  = "Required content field!")
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

}
