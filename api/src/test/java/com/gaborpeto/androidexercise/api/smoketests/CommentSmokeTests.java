package com.gaborpeto.androidexercise.api.smoketests;

import com.gaborpeto.androidexercise.api.client.ApiClientFactory;
import com.gaborpeto.androidexercise.api.gateway.RemoteCommentGateway;
import com.gaborpeto.androidexercise.api.mapper.RemoteCommentMapper;
import com.gaborpeto.androidexercise.domain.model.Comment;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import io.reactivex.observers.TestObserver;

public class CommentSmokeTests {

    private static final int POST_ID = 1;

    private RemoteCommentGateway gateway;

    @Before
    public void setup() {
        gateway = new RemoteCommentGateway(new ApiClientFactory().create(), new RemoteCommentMapper());
    }

    @Test
    public void testGetCommentsForPost() {
        TestObserver<List<Comment>> observer = gateway.getComments(POST_ID).test();

        observer.assertValue(comments -> !comments.isEmpty());
        observer.assertNoErrors();
        observer.assertComplete();
    }


}
