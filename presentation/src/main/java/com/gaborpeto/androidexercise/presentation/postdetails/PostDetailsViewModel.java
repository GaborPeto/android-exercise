package com.gaborpeto.androidexercise.presentation.postdetails;

import com.gaborpeto.androidexercise.domain.model.Comment;
import com.gaborpeto.androidexercise.domain.model.Post;

import java.util.List;

public interface PostDetailsViewModel {

    void setLoading(boolean isVisible);

    void setComments(List<Comment> comments);

    void setPost(Post post);

    void showErrorMessage();
}
