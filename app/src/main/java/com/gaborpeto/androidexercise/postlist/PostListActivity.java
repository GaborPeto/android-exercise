package com.gaborpeto.androidexercise.postlist;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import com.gaborpeto.androidexercise.R;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;

public class PostListActivity extends Activity implements HasFragmentInjector {

  @Inject
  DispatchingAndroidInjector<Fragment> fragmentInjector;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_post_list);
  }

  @Override
  public AndroidInjector<Fragment> fragmentInjector() {
    return fragmentInjector;
  }
}
