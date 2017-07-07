package com.gaborpeto.androidexercise.domain.model;

public class Comment {

    private int id;
    private int postId;
    private String name;
    private String email;
    private String body;

    public Comment() {}

    public Comment(int id, int postId, String name, String email, String body) {
        this.id = id;
        this.postId = postId;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", postId=" + postId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (id != comment.id) return false;
        if (postId != comment.postId) return false;
        if (name != null ? !name.equals(comment.name) : comment.name != null) return false;
        if (email != null ? !email.equals(comment.email) : comment.email != null) return false;
        return body != null ? body.equals(comment.body) : comment.body == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + postId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        return result;
    }
}
