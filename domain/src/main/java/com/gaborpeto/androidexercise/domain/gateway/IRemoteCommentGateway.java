package com.gaborpeto.androidexercise.domain.gateway;

import com.gaborpeto.androidexercise.domain.model.Comment;

import java.util.List;

import io.reactivex.Single;

public interface IRemoteCommentGateway {

    Single<List<Comment>> getCommentsForPost(int postId);

}
