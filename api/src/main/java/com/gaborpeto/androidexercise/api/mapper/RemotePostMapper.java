package com.gaborpeto.androidexercise.api.mapper;

import com.gaborpeto.androidexercise.api.model.RemotePost;
import com.gaborpeto.androidexercise.domain.mapper.Mapper;
import com.gaborpeto.androidexercise.domain.model.Post;

public class RemotePostMapper extends Mapper<RemotePost, Post> {

    @Override public Post map(RemotePost remotePost) {
        return new Post(
                remotePost.id,
                remotePost.userId,
                remotePost.title,
                remotePost.body);
    }
}