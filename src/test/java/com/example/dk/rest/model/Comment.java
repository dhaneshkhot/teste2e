package com.example.dk.rest.model;

import java.util.Objects;

public class Comment {
    int postId;
    int ie;
    String name;
    String email;
    String body;

    public Comment() {}

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getIe() {
        return ie;
    }

    public void setIe(int ie) {
        this.ie = ie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return postId == comment.postId &&
                ie == comment.ie &&
                name.equals(comment.name) &&
                email.equals(comment.email) &&
                body.equals(comment.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, ie, name, email, body);
    }
}
