package com.gaborpeto.androidexercise.domain.gateway.local;

import com.gaborpeto.androidexercise.domain.model.Post;

import java.util.List;

import io.reactivex.Maybe;

public interface ILocalPostGateway {

    Maybe<List<Post>> getPosts();

    Maybe<Post> getPost(int postId);

    void updatePosts(List<Post> posts);

    void updatePost(Post post);

}
