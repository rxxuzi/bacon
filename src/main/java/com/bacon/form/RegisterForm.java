package com.bacon.form;

import jakarta.validation.constraints.*;

public class RegisterForm {

    @NotBlank(message = "User ID is required")
    @Pattern(regexp = "^[a-zA-Z0-9_]{3,20}$",
            message = "User ID must be 3-20 characters, letters, numbers, and underscores only")
    private String userId;

    @NotBlank(message = "Display name is required")
    @Size(max = 100, message = "Display name must be less than 100 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    // Getters and Setters
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}