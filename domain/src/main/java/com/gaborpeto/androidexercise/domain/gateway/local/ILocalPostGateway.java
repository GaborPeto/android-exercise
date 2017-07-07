package com.gaborpeto.androidexercise.domain.gateway.local;

import com.gaborpeto.androidexercise.domain.model.Post;

import java.util.List;

import io.reactivex.Single;

public interface ILocalPostGateway {

    Single<List<Post>> getPosts();

    Single<Post> getPost(int postId);

    void updatePosts(List<Post> posts);

    void updatePost(Post post);

}
