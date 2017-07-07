package com.gaborpeto.androidexercise.persistence.mapper;

import com.gaborpeto.androidexercise.domain.model.Post;
import com.gaborpeto.androidexercise.persistence.model.PersistablePost;

public class PersistablePostMapper extends PersistableMapper<Post, PersistablePost> {

    @Override
    public PersistablePost map(Post post) {
        return new PersistablePost(
                post.getId(),
                post.getUserId(),
                post.getTitle(),
                post.getBody()
        );
    }

    @Override
    protected Post inverseMap(PersistablePost persistablePost) {
        return new Post(
                persistablePost.id,
                persistablePost.userId,
                persistablePost.title,
                persistablePost.body
        );
    }
}
