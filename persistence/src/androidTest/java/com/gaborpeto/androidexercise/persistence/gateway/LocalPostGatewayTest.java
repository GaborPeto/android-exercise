package com.gaborpeto.androidexercise.persistence.gateway;

import com.gaborpeto.androidexercise.domain.model.Post;
import com.gaborpeto.androidexercise.persistence.mapper.PersistablePostMapper;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import io.reactivex.observers.TestObserver;

import static java.util.Arrays.*;

public class LocalPostGatewayTest extends BaseLocalGatewayTest {

    private static final int USER_ID = 10;
    private static final String TITLE = "title";
    private static final String BODY = "body";
    private static final Post POST_1 = buildPost(1);
    private static final Post POST_2 = buildPost(2);
    private static final List<Post> POSTS = asList(POST_1, POST_2);

    private LocalPostGateway gateway;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        gateway = new LocalPostGateway(new PersistablePostMapper());
    }

    @Test
    public void getPosts() throws Exception {
        gateway.updatePosts(POSTS);

        TestObserver<List<Post>> observer = gateway.getPosts().test();

        observer.assertValue(POSTS);
        observer.assertNoErrors();
        observer.assertComplete();
    }

    @Test
    public void getPost() throws Exception {
        gateway.updatePosts(POSTS);

        TestObserver<Post> observer = gateway.getPost(1).test();

        observer.assertValue(POST_1);
        observer.assertNoErrors();
        observer.assertComplete();
    }

    @Test
    public void updatePosts() throws Exception {
        gateway.updatePosts(POSTS);
        Post updatedPost = new Post(1, USER_ID, TITLE, "updated body");

        gateway.updatePosts(asList(updatedPost, POST_2));

        TestObserver<List<Post>> observer = gateway.getPosts().test();

        observer.assertValue(asList(updatedPost, POST_2));
        observer.assertNoErrors();
        observer.assertComplete();
    }

    @Test
    public void updatePost() throws Exception {
        gateway.updatePost(POST_1);
        Post updatedPost = new Post(1, USER_ID, "updated title", BODY);

        gateway.updatePost(updatedPost);

        TestObserver<Post> observer = gateway.getPost(1).test();

        observer.assertValue(updatedPost);
        observer.assertNoErrors();
        observer.assertComplete();
    }

    @Test
    public void givenEmptyPosts_whenGetPosts_thenReturnEmptyObservable() {
        TestObserver<List<Post>> observer = gateway.getPosts().test();

        observer.assertNoValues();
        observer.assertNoErrors();
        observer.assertComplete();
    }

    @Test
    public void givenPostIsNotFound_whenGetPost_thenReturnEmptyObservable() {
        TestObserver<Post> observer = gateway.getPost(1).test();

        observer.assertNoValues();
        observer.assertNoErrors();
        observer.assertComplete();
    }

    private static Post buildPost(int postId) {
        return new Post(postId, USER_ID, TITLE, BODY);
    }

}