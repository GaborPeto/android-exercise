package com.gaborpeto.androidexercise.postlist;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.gaborpeto.androidexercise.domain.model.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {

    private List<Post> posts;

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PostViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        holder.bind(posts.get(position));
    }

    @Override
    public int getItemCount() {
        return posts == null ? 0 : posts.size();
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }
}
