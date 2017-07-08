package com.gaborpeto.androidexercise.persistence.gateway;

import com.gaborpeto.androidexercise.domain.gateway.local.ILocalPostGateway;
import com.gaborpeto.androidexercise.domain.model.Post;
import com.gaborpeto.androidexercise.persistence.mapper.PersistableMapper;
import com.gaborpeto.androidexercise.persistence.model.PersistablePost;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.realm.Realm;

public class LocalPostGateway implements ILocalPostGateway {

    private static final String FIELD_POST_ID = "id";

    private PersistableMapper<Post, PersistablePost> mapper;

    @Inject
    public LocalPostGateway(PersistableMapper<Post, PersistablePost> mapper) {
        this.mapper = mapper;
    }

    @Override
    public Maybe<List<Post>> getPosts() {
        try (Realm realm = getRealm()) {
            List<PersistablePost> persistablePosts = realm
                    .where(PersistablePost.class)
                    .findAllSorted(FIELD_POST_ID);
            return persistablePosts.isEmpty()
                    ? Maybe.empty()
                    : mapper.inverseFlatMapItems(realm.copyFromRealm(persistablePosts)).toMaybe();
        }
    }

    @Override
    public Maybe<Post> getPost(int postId) {
        try (Realm realm = getRealm()){
            PersistablePost post = realm
                    .where(PersistablePost.class)
                    .equalTo(FIELD_POST_ID, postId)
                    .findFirst();
            return post == null
                    ? Maybe.empty()
                    : mapper.inverseFlatMapItem(post).toMaybe();
        }
    }

    @Override
    public void updatePosts(List<Post> posts) {
        try(Realm realm = getRealm()) {
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(mapper.mapItems(posts));
            realm.commitTransaction();
        }
    }

    @Override
    public void updatePost(Post post) {
        try(Realm realm = getRealm()) {
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(mapper.map(post));
            realm.commitTransaction();
        }
    }

    private Realm getRealm() {
        return Realm.getDefaultInstance();
    }
}
