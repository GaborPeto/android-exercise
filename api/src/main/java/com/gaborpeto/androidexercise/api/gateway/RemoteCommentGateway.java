package com.gaborpeto.androidexercise.api.gateway;

import com.gaborpeto.androidexercise.api.client.ApiClient;
import com.gaborpeto.androidexercise.api.model.RemoteComment;
import com.gaborpeto.androidexercise.domain.gateway.remote.IRemoteCommentGateway;
import com.gaborpeto.androidexercise.domain.mapper.Mapper;
import com.gaborpeto.androidexercise.domain.model.Comment;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class RemoteCommentGateway implements IRemoteCommentGateway {

    private ApiClient client;
    private Mapper<RemoteComment, Comment> mapper;

    @Inject
    public RemoteCommentGateway(ApiClient client, Mapper<RemoteComment, Comment> mapper) {
        this.client = client;
        this.mapper = mapper;
    }

    @Override
    public Single<List<Comment>> getCommentsForPost(int postId) {
        return client.getCommentsForPost(postId).flatMap(mapper::flatMapItems);
    }

}
