package com.gaborpeto.androidexercise.persistence.model;

import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
public class PersistableComment implements RealmModel {

    @PrimaryKey public int id;

    public int postId;
    public String name;
    public String email;
    public String body;

    public PersistableComment() {}

    public PersistableComment(int id, int postId, String name, String email, String body) {
        this.id = id;
        this.postId = postId;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    @Override
    public String toString() {
        return "PersistableComment{" +
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

        PersistableComment that = (PersistableComment) o;

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
