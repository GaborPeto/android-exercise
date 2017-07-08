package com.gaborpeto.androidexercise.domain.interactor.post;

import com.gaborpeto.androidexercise.domain.gateway.local.ILocalPostGateway;
import com.gaborpeto.androidexercise.domain.gateway.remote.IRemotePostGateway;
import com.gaborpeto.androidexercise.domain.model.Post;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.subscribers.TestSubscriber;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetPostInteractorTest {

    private static final int POST_ID = 1;
    private static final Post LOCAL_POST = buildPost("local");
    private static final Post REMOTE_POST = buildPost("remote");
    private static final Throwable REMOTE_ERROR = new Exception();

    private GetPostInteractor interactor;

    @Mock
    ILocalPostGateway mockLocalGateway;

    @Mock
    IRemotePostGateway mockRemoteGateway;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        interactor = new GetPostInteractor(mockLocalGateway, mockRemoteGateway);
    }

    @Test
    public void givenEmptyLocalGatewayAndRemotePost_whenGetPost_thenReturnRemotePostAndUpdateLocalGateway() {
        arrangeEmptyLocal();
        arrangeRemoteSuccess();

        TestSubscriber<Post> observer = interactor.getPost(POST_ID).test();

        observer.assertValue(REMOTE_POST);
        observer.assertNoErrors();
        observer.assertComplete();
        verify(mockLocalGateway).updatePost(REMOTE_POST);
    }

    @Test public void givenLocalAndRemotePost_whenGetPost_thenReturnLocalThenRemotePostAndUpdateLocalGateway() {
        arrangeLocalSuccess();
        arrangeRemoteSuccess();

        TestSubscriber<Post> observer = interactor.getPost(POST_ID).test();

        observer.assertValues(LOCAL_POST, REMOTE_POST);
        observer.assertNoErrors();
        observer.assertComplete();
        verify(mockLocalGateway).updatePost(REMOTE_POST);
    }

    @Test public void givenLocalPostAndRemoteError_whenGetPost_thenReturnLocalPostThenError() {
        arrangeLocalSuccess();
        arrangeRemoteError();

        TestSubscriber<Post> observer = interactor.getPost(POST_ID).test();

        observer.assertValue(LOCAL_POST);
        observer.assertError(REMOTE_ERROR);
    }

    private void arrangeRemoteError() {
        when(mockRemoteGateway.getPost(POST_ID)).thenReturn(Single.error(REMOTE_ERROR));
    }

    private void arrangeRemoteSuccess() {
        when(mockRemoteGateway.getPost(POST_ID)).thenReturn(Single.just(REMOTE_POST));
    }

    private void arrangeLocalSuccess() {
        when(mockLocalGateway.getPost(POST_ID)).thenReturn(Maybe.just(LOCAL_POST));
    }

    private void arrangeEmptyLocal() {
        when(mockLocalGateway.getPost(POST_ID)).thenReturn(Maybe.empty());
    }

    private static Post buildPost(String body) {
        return new Post(POST_ID, 99, "title", body);
    }

}