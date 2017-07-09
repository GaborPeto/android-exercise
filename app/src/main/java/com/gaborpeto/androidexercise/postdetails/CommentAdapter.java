package com.gaborpeto.androidexercise.postdetails;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.gaborpeto.androidexercise.domain.model.Comment;

import java.util.List;

class CommentAdapter extends RecyclerView.Adapter<CommentViewHolder> {

    private List<Comment> comments;

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        holder.bind(comments.get(position));
    }

    @Override
    public int getItemCount() {
        return comments == null ? 0 : comments.size();
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
        notifyDataSetChanged();
    }
}
