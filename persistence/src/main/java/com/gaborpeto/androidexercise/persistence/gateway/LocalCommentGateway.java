package com.gaborpeto.androidexercise.persistence.gateway;

import com.gaborpeto.androidexercise.domain.gateway.local.ILocalCommentGateway;
import com.gaborpeto.androidexercise.domain.model.Comment;
import com.gaborpeto.androidexercise.persistence.mapper.PersistableMapper;
import com.gaborpeto.androidexercise.persistence.model.PersistableComment;

import java.util.List;

import io.reactivex.Single;
import io.realm.Realm;

public class LocalCommentGateway implements ILocalCommentGateway {

    private static final String FIELD_COMMENT_ID = "id";
    private static final String FIELD_POST_ID = "postId";

    private PersistableMapper<Comment, PersistableComment> mapper;

    public LocalCommentGateway(PersistableMapper<Comment, PersistableComment> mapper) {
        this.mapper = mapper;
    }

    @Override
    public Single<List<Comment>> getComments(int postId) {
        try (Realm realm = getRealm()) {
            List<PersistableComment> persistableComments = realm
                    .where(PersistableComment.class)
                    .equalTo(FIELD_POST_ID, postId)
                    .findAllSorted(FIELD_COMMENT_ID);
            return mapper.inverseFlatMapItems(realm.copyFromRealm(persistableComments));
        }
    }

    @Override
    public void updateComments(List<Comment> comments) {
        try(Realm realm = getRealm()) {
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(mapper.mapItems(comments));
            realm.commitTransaction();
        }
    }

    private Realm getRealm() {
        return Realm.getDefaultInstance();
    }
}
