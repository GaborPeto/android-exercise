package com.gaborpeto.androidexercise.presentation;

import com.gaborpeto.androidexercise.domain.model.Post;
import com.gaborpeto.androidexercise.presentation.postlist.IPostListViewPresenter;
import com.gaborpeto.androidexercise.presentation.postlist.PostListViewModel;

import static java.util.Collections.*;

public class TestPostListViewPresenter implements IPostListViewPresenter {

    public static final String TITLE = "post title";
    public static final String BODY = "post body";

    private static final int POST_ID = 1;
    private static final int USER_ID = 2;

    private PostListViewModel viewModel;

    @Override
    public void setViewModel(PostListViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void start() {
        viewModel.setPosts(singletonList(buildPost()));
    }

    @Override
    public void finish() {

    }

    private Post buildPost() {
        return new Post(POST_ID, USER_ID, TITLE, BODY);
    }

}
