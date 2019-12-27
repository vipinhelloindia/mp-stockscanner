package com.mp.stockscanner.di.component;


import com.mp.stockscanner.MainViewModelTest;
import com.mp.stockscanner.di.app.AppModule;
import com.mp.stockscanner.di.module.MpScannerRepoModule;
import com.mp.stockscanner.di.module.NetworkModule;
import com.mp.stockscanner.di.module.RetrofitModule;
import com.mp.stockscanner.di.module.RxJavaTestModule;
import com.mp.stockscanner.di.module.ViewModelModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Reusable;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, RetrofitModule.class,
        NetworkModule.class, MpScannerRepoModule.class, RxJavaTestModule.class, ViewModelModule.class})
public interface TestComponent {

    @Reusable
    void inject(MainViewModelTest test);

    @Component.Builder
    interface Builder {
        TestComponent build();
    }
}