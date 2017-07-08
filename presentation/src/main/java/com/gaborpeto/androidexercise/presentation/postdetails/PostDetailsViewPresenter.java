package com.gaborpeto.androidexercise.presentation.postdetails;

import com.gaborpeto.androidexercise.domain.interactor.comment.IGetCommentsInteractor;
import com.gaborpeto.androidexercise.domain.interactor.post.IGetPostInteractor;
import com.gaborpeto.androidexercise.domain.model.Comment;
import com.gaborpeto.androidexercise.domain.model.Post;
import com.gaborpeto.androidexercise.presentation.Presenter;
import com.gaborpeto.androidexercise.presentation.util.IScheduler;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subscribers.DisposableSubscriber;

public class PostDetailsViewPresenter extends Presenter<PostDetailsViewModel> {

    private IGetPostInteractor getPostInteractor;
    private IGetCommentsInteractor getCommentsInteractor;
    private IScheduler scheduler;
    private int postId;

    CompositeDisposable subscriptions;

    @Inject public PostDetailsViewPresenter(IGetPostInteractor getPostInteractor,
                             IGetCommentsInteractor getCommentsInteractor,
                             IScheduler scheduler) {
        this.getPostInteractor = getPostInteractor;
        this.getCommentsInteractor = getCommentsInteractor;
        this.scheduler = scheduler;
        this.subscriptions = new CompositeDisposable();
    }

    @Override
    public void start() {
        viewModel.setLoading(true);
        getPost();
        getComments();
    }

    @Override
    public void finish() {
        unsubscribe();
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    private void getPost() {
        subscriptions.add(getPostInteractor.getPost(postId)
                .subscribeOn(scheduler.background())
                .observeOn(scheduler.main())
                .subscribeWith(postSubscriber()));
    }

    private void getComments() {
        subscriptions.add(getCommentsInteractor.getComments(postId)
                .subscribeOn(scheduler.background())
                .observeOn(scheduler.main())
                .subscribeWith(commentsSubscriber()));
    }

    private DisposableSubscriber<Post> postSubscriber() {
        return new DisposableSubscriber<Post>() {
            @Override
            public void onNext(Post post) {
                viewModel.setPost(post);
            }

            @Override
            public void onError(Throwable t) {
                viewModel.setLoading(false);
                viewModel.showErrorMessage();
            }

            @Override
            public void onComplete() {
                viewModel.setLoading(false);
            }
        };
    }

    private DisposableSubscriber<List<Comment>> commentsSubscriber() {
        return new DisposableSubscriber<List<Comment>>() {
            @Override
            public void onNext(List<Comment> comments) {
                viewModel.setComments(comments);
            }

            @Override
            public void onError(Throwable t) {
                viewModel.setLoading(false);
                viewModel.showErrorMessage();
            }

            @Override
            public void onComplete() {
                viewModel.setLoading(false);
            }
        };
    }

    private void unsubscribe() {
        if(!subscriptions.isDisposed()) {
            subscriptions.dispose();
        }
    }
}
