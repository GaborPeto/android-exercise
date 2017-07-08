package com.gaborpeto.androidexercise.presentation.postlist;

import com.gaborpeto.androidexercise.domain.interactor.post.IGetPostsInteractor;
import com.gaborpeto.androidexercise.domain.model.Post;
import com.gaborpeto.androidexercise.presentation.Presenter;
import com.gaborpeto.androidexercise.presentation.util.IScheduler;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.subscribers.DisposableSubscriber;

public class PostListViewPresenter extends Presenter<PostListViewModel> {

    private IGetPostsInteractor interactor;
    private IScheduler scheduler;

    Disposable subscription;

    @Inject public PostListViewPresenter(IGetPostsInteractor interactor, IScheduler scheduler) {
        this.interactor = interactor;
        this.scheduler = scheduler;
    }

    @Override
    public void start() {
        getPosts();
    }

    @Override
    public void finish() {
        unsubscribe();
    }

    private void getPosts() {
        viewModel.setLoading(true);
        subscription = interactor.getPosts()
                .subscribeOn(scheduler.background())
                .observeOn(scheduler.main())
                .subscribeWith(postsSubscriber());
    }

    private DisposableSubscriber<List<Post>> postsSubscriber() {
        return new DisposableSubscriber<List<Post>>() {
            @Override
            public void onNext(List<Post> posts) {
                viewModel.setPosts(posts);
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
        if(subscription != null && !subscription.isDisposed()) {
            subscription.dispose();
        }
    }
}
