package com.gaborpeto.androidexercise.presentation.postlist;

import com.gaborpeto.androidexercise.domain.model.Post;

import java.util.List;

public interface PostListViewModel {

    void setLoading(boolean isVisible);

    void showErrorMessage();

    void setPosts(List<Post> posts);
}
