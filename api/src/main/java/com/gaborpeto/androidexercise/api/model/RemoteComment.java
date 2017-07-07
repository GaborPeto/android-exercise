package com.gaborpeto.androidexercise.api.model;

import com.google.gson.annotations.SerializedName;

public class RemoteComment {

    @SerializedName("id") public int id;
    @SerializedName("postId") public int postId;
    @SerializedName("name") public String name;
    @SerializedName("email") public String email;
    @SerializedName("body") public String body;

    @Override
    public String toString() {
        return "RemoteComment{" +
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

        RemoteComment that = (RemoteComment) o;

        if (id != that.id) return false;
        if (postId != that.postId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        return body != null ? body.equals(that.body) : that.body == null;
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
