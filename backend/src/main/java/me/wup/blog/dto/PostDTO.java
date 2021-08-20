package me.wup.blog.dto;

import me.wup.blog.entities.Post;
import me.wup.blog.entities.User;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;

public class PostDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 40)
    private String postTitle;

    @NotBlank
    @Size (min = 1, max = 40)
    private String postAuthor;

    @NotBlank
    @Lob
    private String postContent;

    @NotNull
    private String postStatus;

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


    public PostDTO() {
    }

    public PostDTO(Long id, @NotBlank @Size(min = 1, max = 40) String postTitle, @NotBlank @Size(min = 1, max = 40) String postAuthor, @NotBlank String postContent, @NotNull String postStatus, Instant createdAt, Instant updateAt) {
        this.id = id;
        this.postTitle = postTitle;
        this.postAuthor = postAuthor;
        this.postContent = postContent;
        this.postStatus = postStatus;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    public PostDTO (Post post){
        this.id = post.getId();
        this.postTitle = post.getPostTitle();
        this.postAuthor = post.getPostAuthor();
        this.postContent = post.getPostContent();
        this.postStatus = post.getPostStatus();
        this.createdAt = post.getCreatedAt();
        this.updateAt = post.getUpdateAt();
    }

    public PostDTO (Post post, Set<User> users){
        this(post);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostAuthor() {
        return postAuthor;
    }

    public void setPostAuthor(String postAuthor) {
        this.postAuthor = postAuthor;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(String postStatus) {
        this.postStatus = postStatus;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdateAt(Instant updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostDTO postDTO = (PostDTO) o;
        return Objects.equals(id, postDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
