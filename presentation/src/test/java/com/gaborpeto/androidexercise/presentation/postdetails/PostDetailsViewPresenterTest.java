package com.gaborpeto.androidexercise.presentation.postdetails;

import com.gaborpeto.androidexercise.domain.interactor.comment.IGetCommentsInteractor;
import com.gaborpeto.androidexercise.domain.interactor.post.IGetPostInteractor;
import com.gaborpeto.androidexercise.domain.model.Comment;
import com.gaborpeto.androidexercise.domain.model.Post;
import com.gaborpeto.androidexercise.presentation.util.TestScheduler;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.Flowable;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PostDetailsViewPresenterTest {

    private static final int POST_ID = 1;
    private static final Throwable ERROR = new Exception();
    private static final Post POST = new Post();
    private static final List<Comment> COMMENTS = asList(new Comment(), new Comment());

    private PostDetailsViewPresenter presenter;

    @Mock
    PostDetailsViewModel mockViewModel;

    @Mock
    IGetPostInteractor mockGetPostInteractor;

    @Mock
    IGetCommentsInteractor mockGetCommentsInteractor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new PostDetailsViewPresenter(
                mockGetPostInteractor,
                mockGetCommentsInteractor,
                new TestScheduler());
        presenter.setViewModel(mockViewModel);
        presenter.setPostId(POST_ID);
    }

    @Test
    public void onStart_getPostAndComments() {
        arrangePostInteractorError();
        arrangeCommentsInteractorError();

        presenter.start();

        verify(mockViewModel).setLoading(true);
        verify(mockGetPostInteractor).getPost(POST_ID);
        verify(mockGetCommentsInteractor).getComments(POST_ID);
    }

    @Test public void givenGetPostError_thenShowError() {
        arrangePostInteractorError();
        arrangeCommentsInteractorComments();

        presenter.start();

        verify(mockViewModel).showErrorMessage();
    }

    @Test public void givenPost_thenSetPost() {
        arrangePostInteractorPost();
        arrangeCommentsInteractorError();

        presenter.start();

        verify(mockViewModel).setPost(POST);
        verify(mockViewModel, atLeastOnce()).setLoading(false);
    }

    @Test public void givenGetCommentsError_thenShowError() {
        arrangePostInteractorPost();
        arrangeCommentsInteractorError();

        presenter.start();

        verify(mockViewModel).showErrorMessage();
    }

    @Test public void givenComments_thenSetComments() {
        arrangePostInteractorError();
        arrangeCommentsInteractorComments();

        presenter.start();

        verify(mockViewModel).setComments(COMMENTS);
        verify(mockViewModel, atLeastOnce()).setLoading(false);
    }

    @Test public void onFinish_unsubscribe() {
        arrangePostInteractorPost();
        arrangeCommentsInteractorComments();
        presenter.start();

        presenter.finish();

        assertTrue(presenter.subscriptions.isDisposed());
    }

    private void arrangePostInteractorPost() {
        when(mockGetPostInteractor.getPost(POST_ID)).thenReturn(Flowable.just(POST));

    }

    private void arrangeCommentsInteractorComments() {
        when(mockGetCommentsInteractor.getComments(POST_ID)).thenReturn(Flowable.just(COMMENTS));
    }

    private void arrangeCommentsInteractorError() {
        when(mockGetCommentsInteractor.getComments(POST_ID)).thenReturn(Flowable.error(ERROR));
    }

    private void arrangePostInteractorError() {
        when(mockGetPostInteractor.getPost(POST_ID)).thenReturn(Flowable.error(ERROR));
    }

}