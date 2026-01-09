package com.decisionlens.decision.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateDecisionRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String content;

    @NotBlank(message = "Author is required")
    private String author;

    @NotBlank(message = "Version is required")
    private String version;

    public CreateDecisionRequest() {
    }

    public CreateDecisionRequest(String title, String content, String author, String version) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.version = version;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
