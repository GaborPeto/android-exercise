package com.gaborpeto.androidexercise.api.model;

import com.google.gson.annotations.SerializedName;

public class RemotePost {

    @SerializedName("id") public int id;
    @SerializedName("userId") public int userId;
    @SerializedName("title") public String title;
    @SerializedName("body") public String body;

    @Override
    public String toString() {
        return "RemotePost{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RemotePost that = (RemotePost) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        return body != null ? body.equals(that.body) : that.body == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        return result;
    }
}
