package com.gaborpeto.androidexercise.api.gateway;

import com.gaborpeto.androidexercise.api.client.ApiClient;
import com.gaborpeto.androidexercise.api.mapper.RemotePostMapper;
import com.gaborpeto.androidexercise.api.model.RemotePost;
import com.gaborpeto.androidexercise.domain.mapper.Mapper;
import com.gaborpeto.androidexercise.domain.model.Post;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;

import static java.util.Collections.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RemotePostGatewayTest {

    private static final RemotePost REMOTE_POST = new RemotePost();
    private static final Post POST = new Post();
    private static final int POST_ID = 1;

    private RemotePostGateway gateway;

    @Mock
    ApiClient mockClient;

    @Before public void setup() {
        MockitoAnnotations.initMocks(this);
        arrangeClient();
        gateway = new RemotePostGateway(mockClient, new RemotePostMapper());

    }

    @Test public void getPosts_shouldGetPostsFromClient() {
        gateway.getPosts();

        verify(mockClient).getPosts();
    }

    @Test public void getPosts_shouldMapRemotePostToPost() {
        TestObserver<List<Post>> observer = gateway.getPosts().test();

        observer.assertValue(posts -> posts.get(0).equals(POST));
        observer.assertNoErrors();
        observer.assertComplete();
    }

    @Test public void getPostById_shouldGetPostByIdFromClient() {
        gateway.getPost(POST_ID);

        verify(mockClient).getPost(POST_ID);
    }

    @Test public void getPostById_shouldMapRemotePostToPost() {
        TestObserver<Post> observer = gateway.getPost(POST_ID).test();

        observer.assertValue(POST);
        observer.assertNoErrors();
        observer.assertComplete();
    }

    private void arrangeClient() {
        when(mockClient.getPosts()).thenReturn(Single.just(singletonList(REMOTE_POST)));
        when(mockClient.getPost(POST_ID)).thenReturn(Single.just(REMOTE_POST));
    }



}