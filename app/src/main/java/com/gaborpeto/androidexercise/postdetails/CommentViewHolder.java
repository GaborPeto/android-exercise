package com.gaborpeto.androidexercise.postdetails;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gaborpeto.androidexercise.R;
import com.gaborpeto.androidexercise.domain.model.Comment;

import butterknife.BindView;
import butterknife.ButterKnife;

class CommentViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.body)
    TextView body;

    public CommentViewHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item_view, parent, false));
        ButterKnife.bind(this, itemView);
    }

    public void bind(Comment comment) {
        name.setText(comment.getName());
        email.setText(comment.getEmail());
        body.setText(comment.getBody());
    }
}