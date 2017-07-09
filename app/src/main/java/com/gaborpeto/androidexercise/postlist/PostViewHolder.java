package com.gaborpeto.androidexercise.postlist;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gaborpeto.androidexercise.R;
import com.gaborpeto.androidexercise.domain.model.Post;
import com.gaborpeto.androidexercise.postdetails.PostDetailsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.gaborpeto.androidexercise.util.Constants.*;

public class PostViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.title) TextView title;
    @BindView(R.id.body) TextView body;

    private int postId;

    public PostViewHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item_view, parent, false));
        ButterKnife.bind(this, itemView);
    }

    public void bind(Post post) {
        this.postId = post.getId();
        title.setText(post.getTitle());
        body.setText(post.getBody());
    }

    @OnClick() public void onClick() {
        Intent intent = new Intent(itemView.getContext(), PostDetailsActivity.class);
        intent.putExtra(INTENT_EXTRA_POST_ID, postId);
        itemView.getContext().startActivity(intent);
    }
}

