package com.bacon.entity;

import java.time.LocalDateTime;

public class Bookmark {
    private Long id;
    private Long userId;
    private Long postId;
    private LocalDateTime createdAt;

    // Additional fields for joined data
    private Post post;

    // Constructors
    public Bookmark() {}

    public Bookmark(Long userId, Long postId) {
        this.userId = userId;
        this.postId = postId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}