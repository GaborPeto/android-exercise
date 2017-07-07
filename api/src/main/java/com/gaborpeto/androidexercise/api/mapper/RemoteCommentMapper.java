package com.gaborpeto.androidexercise.api.mapper;

import com.gaborpeto.androidexercise.api.model.RemoteComment;
import com.gaborpeto.androidexercise.domain.mapper.Mapper;
import com.gaborpeto.androidexercise.domain.model.Comment;

public class RemoteCommentMapper extends Mapper<RemoteComment, Comment> {

    @Override
    protected Comment map(RemoteComment comment) {
        return new Comment(
                comment.id,
                comment.postId,
                comment.name,
                comment.email,
                comment.body);
    }

}
