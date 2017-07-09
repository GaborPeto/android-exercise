package com.gaborpeto.androidexercise.postdetails;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gaborpeto.androidexercise.R;
import com.gaborpeto.androidexercise.domain.model.Comment;
import com.gaborpeto.androidexercise.domain.model.Post;
import com.gaborpeto.androidexercise.presentation.postdetails.IPostDetailsViewPresenter;
import com.gaborpeto.androidexercise.presentation.postdetails.PostDetailsViewModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

import static android.view.View.*;
import static com.gaborpeto.androidexercise.util.Constants.*;

public class PostDetailsFragment extends Fragment implements PostDetailsViewModel {

    @Inject
    IPostDetailsViewPresenter presenter;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.body)
    TextView body;

    @BindView(R.id.comment_list)
    RecyclerView commentList;

    private CommentAdapter commentAdapter;

    @Override
    public void onAttach(Context context) {
        AndroidInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_details, container, false);
        ButterKnife.bind(this, view);
        commentAdapter = new CommentAdapter();
        commentList.setLayoutManager(new LinearLayoutManager(getContext()));
        commentList.setAdapter(commentAdapter);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.setViewModel(this);
        presenter.setPostId(getPostId());
        presenter.start();
    }

    @Override
    public void onStop() {
        presenter.finish();
        super.onStop();
    }

    @Override
    public void setLoading(boolean isVisible) {
        progressBar.setVisibility(isVisible ? VISIBLE : GONE);
    }

    @Override
    public void setComments(List<Comment> comments) {
        commentAdapter.setComments(comments);
    }

    @Override
    public void setPost(Post post) {
        title.setText(post.getTitle());
        body.setText(post.getBody());
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
    }

    private int getPostId() {
        return getActivity().getIntent().getIntExtra(INTENT_EXTRA_POST_ID, 0);
    }
}
