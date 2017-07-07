package com.gaborpeto.androidexercise.api.mapper;

import com.gaborpeto.androidexercise.api.model.RemoteComment;
import com.gaborpeto.androidexercise.api.model.RemotePost;
import com.gaborpeto.androidexercise.domain.model.Comment;
import com.gaborpeto.androidexercise.domain.model.Post;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RemoteCommentMapperTest {

    private static final int COMMENT_ID = 1;
    private static final int POST_ID = 2;
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String BODY = "body";
    private static final RemoteComment REMOTE_COMMENT = getRemoteComment();
    private static final Comment EXPECTED_COMMENT = getExpectedComment();

    private RemoteCommentMapper mapper;

    @Before
    public void setup() {
        mapper = new RemoteCommentMapper();
    }

    @Test
    public void map_shouldReturnPost() {
        Comment comment = mapper.map(REMOTE_COMMENT);

        assertEquals(EXPECTED_COMMENT, comment);
    }

    private static RemoteComment getRemoteComment() {
        RemoteComment remoteComment = new RemoteComment();
        remoteComment.id = COMMENT_ID;
        remoteComment.postId = POST_ID;
        remoteComment.name = NAME;
        remoteComment.email = EMAIL;
        remoteComment.body = BODY;
        return remoteComment;
    }

    private static Comment getExpectedComment() {
        return new Comment(COMMENT_ID, POST_ID, NAME, EMAIL, BODY);
    }
}
