package com.bitcoin.presentation.di.components;

import com.bitcoin.presentation.di.modules.ApplicationModule;
import com.bitcoin.presentation.di.modules.MainViewModule;
import com.bitcoin.presentation.di.modules.NetworkModule;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {
    ApplicationModule.class,
    NetworkModule.class,
})
public interface ApplicationComponent {
  MainViewComponent plus(MainViewModule mainViewModule);
}
