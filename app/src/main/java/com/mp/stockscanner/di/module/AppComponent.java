package com.mp.stockscanner.di.module;

import android.app.Application;

import com.mp.stockscanner.MarketPulseApp;
import com.mp.stockscanner.di.app.ActivityModule;
import com.mp.stockscanner.di.app.AppModule;
import com.mp.stockscanner.di.app.FragmentModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;


@Component(modules = {AndroidInjectionModule.class,
        AppModule.class,
        RxJavaModule.class,
        RetrofitModule.class,
        NetworkModule.class,
        ActivityModule.class,
        FragmentModule.class,
        ViewModelModule.class,
        MpScannerRepoModule.class

})
@Singleton
public interface AppComponent {

    void inject(MarketPulseApp app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder provideAppContext(Application application);

        AppComponent build();
    }
}