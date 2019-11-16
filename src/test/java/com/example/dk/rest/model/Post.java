package com.example.dk.rest.model;

import java.util.Objects;

public class Post {
    int userId;
    int id;
    String title;
    String body;

    public Post() {}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        Post post = (Post) o;
        return userId == post.userId &&
                id == post.id &&
                title.equals(post.title) &&
                body.equals(post.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, id, title, body);
    }
}
