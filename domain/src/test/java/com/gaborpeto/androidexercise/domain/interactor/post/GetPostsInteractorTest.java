package com.gaborpeto.androidexercise.domain.interactor.post;

import com.gaborpeto.androidexercise.domain.gateway.local.ILocalPostGateway;
import com.gaborpeto.androidexercise.domain.gateway.remote.IRemotePostGateway;
import com.gaborpeto.androidexercise.domain.model.Post;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.subscribers.TestSubscriber;

import static java.util.Arrays.*;
import static org.mockito.Mockito.when;

public class GetPostsInteractorTest {

    private static final List<Post> LOCAL_POSTS = asList(new Post());
    private static final List<Post> REMOTE_POSTS = asList(new Post(), new Post());
    private static final Throwable REMOTE_ERROR = new Exception();

    private GetPostsInteractor interactor;

    @Mock
    ILocalPostGateway mockLocalGateway;

    @Mock
    IRemotePostGateway mockRemoteGateway;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        interactor = new GetPostsInteractor(mockLocalGateway, mockRemoteGateway);
    }

    @Test public void givenEmptyLocalGatewayAndRemotePosts_whenGetPosts_thenReturnRemotePosts() {
        arrangeEmptyLocal();
        arrangeRemoteSuccess();

        TestSubscriber<List<Post>> observer = interactor.getPosts().test();

        observer.assertValue(REMOTE_POSTS);
        observer.assertNoErrors();
        observer.assertComplete();
    }

    @Test public void givenLocalAndRemotePosts_whenGetPosts_thenReturnLocalThenRemotePosts() {
        arrangeLocalSuccess();
        arrangeRemoteSuccess();

        TestSubscriber<List<Post>> observer = interactor.getPosts().test();

        observer.assertValues(LOCAL_POSTS, REMOTE_POSTS);
        observer.assertNoErrors();
        observer.assertComplete();
    }

    @Test public void givenLocalPostsAndRemoteError_whenGetPosts_thenReturnLocalPostsThenError() {
        arrangeLocalSuccess();
        arrangeRemoteError();

        TestSubscriber<List<Post>> observer = interactor.getPosts().test();

        observer.assertValue(LOCAL_POSTS);
        observer.assertError(REMOTE_ERROR);
    }

    private void arrangeRemoteError() {
        when(mockRemoteGateway.getPosts()).thenReturn(Single.error(REMOTE_ERROR));
    }

    private void arrangeRemoteSuccess() {
        when(mockRemoteGateway.getPosts()).thenReturn(Single.just(REMOTE_POSTS));
    }

    private void arrangeLocalSuccess() {
        when(mockLocalGateway.getPosts()).thenReturn(Maybe.just(LOCAL_POSTS));
    }

    private void arrangeEmptyLocal() {
        when(mockLocalGateway.getPosts()).thenReturn(Maybe.empty());
    }

}