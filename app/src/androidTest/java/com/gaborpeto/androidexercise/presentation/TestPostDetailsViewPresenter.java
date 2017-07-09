package com.gaborpeto.androidexercise.presentation;

import com.gaborpeto.androidexercise.domain.model.Comment;
import com.gaborpeto.androidexercise.domain.model.Post;
import com.gaborpeto.androidexercise.presentation.postdetails.IPostDetailsViewPresenter;
import com.gaborpeto.androidexercise.presentation.postdetails.PostDetailsViewModel;

import static java.util.Collections.*;


public class TestPostDetailsViewPresenter implements IPostDetailsViewPresenter {

    public static final String POST_TITLE = "post title";
    public static final String POST_BODY = "post body";
    public static final String COMMENT_NAME = "comment name";
    public static final String COMMENT_EMAIL = "comment email";
    public static final String COMMENT_BODY = "comment body";

    private static final int POST_ID = 1;
    private static final int USER_ID = 2;
    private static final int COMMENT_ID = 10;

    private PostDetailsViewModel viewModel;

    @Override
    public void setViewModel(PostDetailsViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void start() {
        viewModel.setPost(buildPost());
        viewModel.setComments(singletonList(buildComment()));
    }

    @Override
    public void finish() {

    }

    @Override
    public void setPostId(int postId) {

    }

    private Post buildPost() {
        return new Post(POST_ID, USER_ID, POST_TITLE, POST_BODY);
    }

    private Comment buildComment() {
        return new Comment(COMMENT_ID, POST_ID, COMMENT_NAME, COMMENT_EMAIL, COMMENT_BODY);
    }
}
