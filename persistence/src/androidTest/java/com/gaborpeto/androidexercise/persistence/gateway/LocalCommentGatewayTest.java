package com.gaborpeto.androidexercise.persistence.gateway;

import com.gaborpeto.androidexercise.domain.model.Comment;
import com.gaborpeto.androidexercise.persistence.mapper.PersistableCommentMapper;

import org.junit.Before;
import org.junit.Test;
import java.util.List;

import io.reactivex.observers.TestObserver;

import static java.util.Arrays.*;

public class LocalCommentGatewayTest extends BaseLocalGatewayTest {

    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String BODY = "body";
    private static final int POST_ID = 1;
    private static final int OTHER_POST_ID = 2;
    private static final Comment COMMENT_1 = buildComment(1, POST_ID);
    private static final Comment COMMENT_2 = buildComment(2, POST_ID);
    private static final Comment OTHER_COMMENT = buildComment(3, OTHER_POST_ID);

    private LocalCommentGateway gateway;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        gateway = new LocalCommentGateway(new PersistableCommentMapper());
    }

    @Test
    public void getComments() throws Exception {
        gateway.updateComments(asList(COMMENT_1, COMMENT_2, OTHER_COMMENT));

        TestObserver<List<Comment>> observer = gateway.getComments(POST_ID).test();

        observer.assertValue(asList(COMMENT_1, COMMENT_2));
        observer.assertNoErrors();
        observer.assertComplete();
    }

    @Test
    public void updateComments() throws Exception {
        gateway.updateComments(asList(COMMENT_1, COMMENT_2));
        Comment updatedComment = new Comment(1, POST_ID, NAME, EMAIL, "updated body");

        gateway.updateComments(asList(updatedComment, COMMENT_2));

        TestObserver<List<Comment>> observer = gateway.getComments(POST_ID).test();
        observer.assertValue(asList(updatedComment, COMMENT_2));
        observer.assertNoErrors();
        observer.assertComplete();
    }

    private static Comment buildComment(int commentId, int postId) {
        return new Comment(commentId, postId, NAME, EMAIL, BODY);
    }
}