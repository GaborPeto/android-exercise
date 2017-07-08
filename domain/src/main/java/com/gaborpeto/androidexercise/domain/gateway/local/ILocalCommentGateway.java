package com.gaborpeto.androidexercise.domain.gateway.local;

import com.gaborpeto.androidexercise.domain.model.Comment;

import java.util.List;

import io.reactivex.Maybe;

public interface ILocalCommentGateway {

    Maybe<List<Comment>> getComments(int postId);

    void updateComments(List<Comment> comments);

}
