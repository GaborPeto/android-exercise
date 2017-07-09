package com.gaborpeto.androidexercise.domain.interactor.comment;

import com.gaborpeto.androidexercise.domain.gateway.local.ILocalCommentGateway;
import com.gaborpeto.androidexercise.domain.gateway.remote.IRemoteCommentGateway;
import com.gaborpeto.androidexercise.domain.model.Comment;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.subscribers.TestSubscriber;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;

public class GetCommentsInteractorTest {

    private static final int POST_ID = 1;
    private static final List<Comment> LOCAL_COMMENTS = asList(new Comment());
    private static final List<Comment> REMOTE_COMMENTS = asList(new Comment(), new Comment());
    private static final Throwable REMOTE_ERROR = new Exception();

    private GetCommentsInteractor interactor;

    @Mock
    ILocalCommentGateway mockLocalGateway;

    @Mock
    IRemoteCommentGateway mockRemoteGateway;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        interactor = new GetCommentsInteractor(mockLocalGateway, mockRemoteGateway);
    }

    @Test
    public void givenEmptyLocalGatewayAndRemoteComments_whenGetComments_thenReturnRemoteComments() throws InterruptedException {
        arrangeEmptyLocal();
        arrangeRemoteSuccess();

        TestSubscriber<List<Comment>> observer = interactor.getComments(POST_ID).test();

        Thread.sleep(200);
        observer.assertValue(REMOTE_COMMENTS);
        observer.assertNoErrors();
        observer.assertComplete();
    }

    @Test public void givenLocalAndRemoteComments_whenGetComments_thenReturnLocalThenRemoteComments() throws InterruptedException {
        arrangeLocalSuccess();
        arrangeRemoteSuccess();

        TestSubscriber<List<Comment>> observer = interactor.getComments(POST_ID).test();

        Thread.sleep(200);
        observer.assertValues(LOCAL_COMMENTS, REMOTE_COMMENTS);
        observer.assertNoErrors();
        observer.assertComplete();
    }

    @Test public void givenLocalCommentsAndRemoteError_whenGetPosts_thenReturnLocalCommentsThenError() throws InterruptedException {
        arrangeLocalSuccess();
        arrangeRemoteError();

        TestSubscriber<List<Comment>> observer = interactor.getComments(POST_ID).test();

        Thread.sleep(200);
        observer.assertValue(LOCAL_COMMENTS);
        observer.assertError(REMOTE_ERROR);
    }

    private void arrangeRemoteError() {
        when(mockRemoteGateway.getComments(POST_ID)).thenReturn(Single.error(REMOTE_ERROR));
    }

    private void arrangeRemoteSuccess() {
        when(mockRemoteGateway.getComments(POST_ID)).thenReturn(Single.just(REMOTE_COMMENTS));
    }

    private void arrangeLocalSuccess() {
        when(mockLocalGateway.getComments(POST_ID)).thenReturn(Maybe.just(LOCAL_COMMENTS));
    }

    private void arrangeEmptyLocal() {
        when(mockLocalGateway.getComments(POST_ID)).thenReturn(Maybe.empty());
    }

}