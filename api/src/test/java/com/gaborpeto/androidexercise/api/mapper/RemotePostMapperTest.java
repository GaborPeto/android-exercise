package com.gaborpeto.androidexercise.api.mapper;

import com.gaborpeto.androidexercise.api.model.RemotePost;
import com.gaborpeto.androidexercise.domain.model.Post;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RemotePostMapperTest {

    private static final int POST_ID = 1;
    private static final int USER_ID = 2;
    private static final String TITLE = "title";
    private static final String BODY = "body";
    private static final RemotePost REMOTE_POST = getRemotePost();
    private static final Post EXPECTED_POST = getExpectedPost();

    private RemotePostMapper mapper;

    @Before
    public void setup() {
        mapper = new RemotePostMapper();
    }

    @Test
    public void map_shouldReturnPost() {
        Post post = mapper.map(REMOTE_POST);

        assertEquals(EXPECTED_POST, post);
    }

    private static RemotePost getRemotePost() {
        RemotePost remotePost = new RemotePost();
        remotePost.id = POST_ID;
        remotePost.userId = USER_ID;
        remotePost.title = TITLE;
        remotePost.body = BODY;
        return remotePost;
    }

    private static Post getExpectedPost() {
        return new Post(POST_ID, USER_ID, TITLE, BODY);
    }

}