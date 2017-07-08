package com.gaborpeto.androidexercise.presentation.postlist;

import com.gaborpeto.androidexercise.domain.interactor.post.IGetPostsInteractor;
import com.gaborpeto.androidexercise.domain.model.Post;
import com.gaborpeto.androidexercise.presentation.util.TestScheduler;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.Flowable;

import static java.util.Arrays.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PostListViewPresenterTest {

    private static final Throwable ERROR = new Exception();
    private static final List<Post> POSTS = asList(new Post(), new Post());

    private PostListViewPresenter presenter;

   @Mock PostListViewModel mockViewModel;

   @Mock
   IGetPostsInteractor mockInteractor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new PostListViewPresenter(mockInteractor, new TestScheduler());
        presenter.setViewModel(mockViewModel);
    }

    @Test public void onStart_getPosts() {
        arrangeInteractorError();

        presenter.start();

        verify(mockViewModel).setLoading(true);
        verify(mockInteractor).getPosts();
    }

    @Test public void givenGetPostsError_thenShowError() {
        arrangeInteractorError();

        presenter.start();

        verify(mockViewModel).showErrorMessage();
    }

    @Test public void givenPosts_thenSetPosts() {
        arrangeInteractorWithPosts();

        presenter.start();

        verify(mockViewModel).setPosts(POSTS);
        verify(mockViewModel).setLoading(false);
    }

    @Test public void onFinish_unsubscribe() {
        arrangeInteractorWithPosts();
        presenter.start();

        presenter.finish();

        assertTrue(presenter.subscription.isDisposed());
    }

    private void arrangeInteractorWithPosts() {
        when(mockInteractor.getPosts()).thenReturn(Flowable.just(POSTS));
    }

    private void arrangeInteractorError() {
        when(mockInteractor.getPosts()).thenReturn(Flowable.error(ERROR));
    }
}