package com.bitcoin;

import android.app.Application;
import com.bitcoin.presentation.di.components.ApplicationComponent;
import com.bitcoin.presentation.di.components.DaggerApplicationComponent;
import com.bitcoin.presentation.di.modules.ApplicationModule;

public class BitcoinApplication extends Application {
  private static BitcoinApplication application;
  private ApplicationComponent applicationComponent;

  public static BitcoinApplication getInstance() {
    return application;
  }

  @Override public void onCreate() {
    super.onCreate();
    application = this;
    initializeInjector();
  }

  private void initializeInjector() {
    this.applicationComponent = DaggerApplicationComponent.builder()
        .applicationModule(new ApplicationModule(this))
        .build();
  }

  public ApplicationComponent getApplicationComponent() {
    return this.applicationComponent;
  }
}
