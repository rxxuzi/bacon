package com.bacon.entity;

import java.time.LocalDateTime;

public class User {
    private Long id;
    private String userId;
    private String username;
    private String bio;
    private String color;  // User icon color for profile
    private String passwordHash;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructors
    public User() {}

    public User(String userId, String username, String passwordHash) {
        this.userId = userId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.color = "#6B73FF";
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}