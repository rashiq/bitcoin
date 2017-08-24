package com.bitcoin.presentation.di.components;

import com.bitcoin.presentation.di.PerActivity;
import com.bitcoin.presentation.di.modules.MainViewModule;
import com.bitcoin.presentation.view.mainview.MainActivity;
import dagger.Subcomponent;

@PerActivity @Subcomponent(modules = { MainViewModule.class })
public interface MainViewComponent extends MainActivity.Injector {
}
