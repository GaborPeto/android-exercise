package com.gaborpeto.androidexercise.persistence.mapper;

import com.gaborpeto.androidexercise.domain.model.Comment;
import com.gaborpeto.androidexercise.persistence.model.PersistableComment;

public class PersistableCommentMapper extends PersistableMapper<Comment, PersistableComment> {

    @Override
    public PersistableComment map(Comment comment) {
        return new PersistableComment(
            comment.getId(),
            comment.getPostId(),
            comment.getName(),
            comment.getEmail(),
            comment.getBody()
        );
    }

    @Override
    protected Comment inverseMap(PersistableComment persistableComment) {
        return new Comment(
            persistableComment.id,
            persistableComment.postId,
            persistableComment.name,
            persistableComment.email,
            persistableComment.body
        );
    }
}
