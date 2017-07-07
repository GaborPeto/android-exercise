package com.gaborpeto.androidexercise.domain.gateway.local;

import com.gaborpeto.androidexercise.domain.model.Comment;

import java.util.List;

import io.reactivex.Single;

public interface ILocalCommentGateway {

    Single<List<Comment>> getComments(int postId);

    void updateComments(List<Comment> comments);

}
