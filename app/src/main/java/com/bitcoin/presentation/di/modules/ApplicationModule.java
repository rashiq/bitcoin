package com.bitcoin.presentation.di.modules;

import android.content.Context;
import com.bitcoin.BitcoinApplication;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module public class ApplicationModule {
  private final BitcoinApplication application;

  public ApplicationModule(BitcoinApplication application) {
    this.application = application;
  }

  @Provides @Singleton Context provideApplicationContext() {
    return this.application;
  }
}
