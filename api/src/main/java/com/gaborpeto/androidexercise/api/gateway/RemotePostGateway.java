package com.gaborpeto.androidexercise.api.gateway;

import com.gaborpeto.androidexercise.api.client.ApiClient;
import com.gaborpeto.androidexercise.api.model.RemotePost;
import com.gaborpeto.androidexercise.domain.gateway.remote.IRemotePostGateway;
import com.gaborpeto.androidexercise.domain.mapper.Mapper;
import com.gaborpeto.androidexercise.domain.model.Post;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class RemotePostGateway implements IRemotePostGateway {

    private ApiClient client;
    private Mapper<RemotePost, Post> mapper;

    @Inject public RemotePostGateway(ApiClient client, Mapper<RemotePost, Post> mapper) {
        this.client = client;
        this.mapper = mapper;
    }

    @Override
    public Single<List<Post>> getPosts()
    {
        return client.getPosts().flatMap(mapper::flatMapItems);
    }

    @Override
    public Single<Post> getPost(int postId) {
        return client.getPost(postId).flatMap(mapper::flatMapItem);
    }

}
