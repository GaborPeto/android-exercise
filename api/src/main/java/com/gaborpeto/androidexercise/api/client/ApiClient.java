package com.gaborpeto.androidexercise.api.client;

import com.gaborpeto.androidexercise.api.model.RemoteComment;
import com.gaborpeto.androidexercise.api.model.RemotePost;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiClient {

    String ENDPOINT = "https://jsonplaceholder.typicode.com";

    @GET("posts")
    Single<List<RemotePost>> getPosts();

    @GET("posts/{postId}")
    Single<RemotePost> getPost(@Path("postId") int postId);

    @GET("posts/{postId}/comments")
    Single<List<RemoteComment>> getCommentsForPost(@Path("postId") int postId);
}
