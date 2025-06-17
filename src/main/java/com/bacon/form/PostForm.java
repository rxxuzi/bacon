package com.bacon.form;

import jakarta.validation.constraints.*;

public class PostForm {

    @NotBlank(message = "Content is required")
    @Size(max = 500, message = "Post must be less than 500 characters")
    private String content;

    @Pattern(regexp = "^(public|private)$",
            message = "Invalid visibility setting")
    private String visibility = "public";

    // Getters and Setters
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }
}