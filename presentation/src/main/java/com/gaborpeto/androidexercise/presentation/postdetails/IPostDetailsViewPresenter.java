package com.gaborpeto.androidexercise.presentation.postdetails;

import com.gaborpeto.androidexercise.presentation.IPresenter;

public interface IPostDetailsViewPresenter extends IPresenter<PostDetailsViewModel> {

    void setPostId(int postId);

}
