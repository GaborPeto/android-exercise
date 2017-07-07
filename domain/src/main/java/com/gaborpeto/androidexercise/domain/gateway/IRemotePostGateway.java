package com.gaborpeto.androidexercise.domain.gateway;

import com.gaborpeto.androidexercise.domain.model.Post;

import java.util.List;

import io.reactivex.Single;

public interface IRemotePostGateway {


    Single<List<Post>> getPosts();

    Single<Post> getPost(int postId);

}
