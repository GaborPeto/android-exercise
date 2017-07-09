package com.gaborpeto.androidexercise.postlist;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gaborpeto.androidexercise.R;
import com.gaborpeto.androidexercise.domain.model.Post;
import com.gaborpeto.androidexercise.presentation.postlist.PostListViewModel;
import com.gaborpeto.androidexercise.presentation.postlist.PostListViewPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

import static android.view.View.*;

public class PostListFragment extends Fragment implements PostListViewModel {

    @Inject
    PostListViewPresenter presenter;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.post_list)
    RecyclerView postList;

    private PostAdapter postAdapter;

    @Override
    public void onAttach(Context context) {
        AndroidInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_list, container, false);
        ButterKnife.bind(this, view);
        postAdapter = new PostAdapter();
        postList.setLayoutManager(new LinearLayoutManager(getContext()));
        postList.setAdapter(postAdapter);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.setViewModel(this);
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
    public void setPosts(List<Post> posts) {
        postAdapter.setPosts(posts);
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
    }
}
