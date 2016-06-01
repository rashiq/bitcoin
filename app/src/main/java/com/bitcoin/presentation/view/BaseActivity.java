package com.bitcoin.presentation.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.bitcoin.BitcoinApplication;
import com.bitcoin.presentation.di.components.ApplicationComponent;

public abstract class BaseActivity extends AppCompatActivity {

  private Presenter presenter = new Presenter();

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setupDagger(BitcoinApplication.getInstance().getApplicationComponent());
    attachPresenter();
  }

  @Override protected void onStart() {
    super.onStart();
    presenter.onStart();
  }

  @Override protected void onResume() {
    super.onResume();
    presenter.onResume();
  }

  @Override protected void onPause() {
    super.onPause();
    presenter.onPause();
  }

  @Override protected void onStop() {
    super.onStop();
    presenter.onStop();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    presenter.onDestroy();
  }

  protected void attachPresenter(Presenter presenter) {
    this.presenter = presenter;
  }

  protected abstract void setupDagger(ApplicationComponent appComponent);

  public abstract void attachPresenter();
}
