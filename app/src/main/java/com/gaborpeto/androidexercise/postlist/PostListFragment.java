package com.gaborpeto.androidexercise.postlist;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaborpeto.androidexercise.R;
import com.gaborpeto.androidexercise.domain.model.Post;
import com.gaborpeto.androidexercise.presentation.postlist.PostListViewModel;
import com.gaborpeto.androidexercise.presentation.postlist.PostListViewPresenter;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class PostListFragment extends Fragment implements PostListViewModel {

    @Inject
    PostListViewPresenter presenter;

    @Override
    public void onAttach(Context context) {
        AndroidInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post_list, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.setViewModel(this);
        presenter.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.finish();
    }

    @Override
    public void setLoading(boolean isVisible) {

    }

    @Override
    public void showErrorMessage() {

    }

    @Override
    public void setPosts(List<Post> posts) {

    }
}
