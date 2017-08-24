package com.bitcoin.presentation.di.modules;

import com.bitcoin.presentation.di.PerActivity;
import com.bitcoin.presentation.view.mainview.MainViewCallback;
import dagger.Module;
import dagger.Provides;

@Module public class MainViewModule {
  private final MainViewCallback mainViewCallback;

  public MainViewModule(MainViewCallback mainViewCallback) {
    this.mainViewCallback = mainViewCallback;
  }

  @Provides @PerActivity public MainViewCallback getMainViewCallback() {
    return mainViewCallback;
  }
}
