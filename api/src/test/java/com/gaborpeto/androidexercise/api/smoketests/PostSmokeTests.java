package com.gaborpeto.androidexercise.api.smoketests;

import com.gaborpeto.androidexercise.api.client.ApiClientFactory;
import com.gaborpeto.androidexercise.api.gateway.RemotePostGateway;
import com.gaborpeto.androidexercise.api.mapper.RemotePostMapper;
import com.gaborpeto.androidexercise.domain.model.Post;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import io.reactivex.observers.TestObserver;

public class PostSmokeTests {

    private static final int POST_ID = 1;

    private RemotePostGateway gateway;

    @Before
    public void setup() {
        gateway = new RemotePostGateway(new ApiClientFactory().create(), new RemotePostMapper());
    }

    @Test
    public void testGetPosts() {
        TestObserver<List<Post>> observer = gateway.getPosts().test();

        observer.assertValue(posts -> !posts.isEmpty());
        observer.assertNoErrors();
        observer.assertComplete();
    }

    @Test
    public void testGetPostById() {
        TestObserver<Post> observer = gateway.getPost(POST_ID).test();

        observer.assertValue(post -> post.getId() == POST_ID);
        observer.assertNoErrors();
        observer.assertComplete();
    }

}
