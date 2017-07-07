package com.gaborpeto.androidexercise.api.gateway;

import com.gaborpeto.androidexercise.api.client.ApiClient;
import com.gaborpeto.androidexercise.api.mapper.RemoteCommentMapper;
import com.gaborpeto.androidexercise.api.model.RemoteComment;
import com.gaborpeto.androidexercise.domain.mapper.Mapper;
import com.gaborpeto.androidexercise.domain.model.Comment;

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

public class RemoteCommentGatewayTest {

    private static final RemoteComment REMOTE_COMMENT = new RemoteComment();
    private static final Comment COMMENT = new Comment();
    private static final int POST_ID = 1;

    private RemoteCommentGateway gateway;

    @Mock
    ApiClient mockClient;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        arrangeClient();
        gateway = new RemoteCommentGateway(mockClient, new RemoteCommentMapper());

    }

    @Test
    public void getCommentsById_shouldGetCommentsByIdFromClient() {
        gateway.getCommentsForPost(POST_ID);

        verify(mockClient).getCommentsForPost(POST_ID);
    }

    @Test public void getCommentsById_shouldMapRemoteCommentsToComments() {
        TestObserver<List<Comment>> observer = gateway.getCommentsForPost(POST_ID).test();

        observer.assertValue(comments -> comments.get(0).equals(COMMENT));
        observer.assertNoErrors();
        observer.assertComplete();
    }

    private void arrangeClient() {
        when(mockClient.getCommentsForPost(POST_ID)).thenReturn(Single.just(singletonList(REMOTE_COMMENT)));
    }

}
