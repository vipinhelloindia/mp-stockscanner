package com.mp.stockscanner;

import android.app.Application;

import com.mp.stockscanner.di.module.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import io.reactivex.plugins.RxJavaPlugins;

public class MarketPulseApp extends Application implements HasAndroidInjector {

    @Inject
    DispatchingAndroidInjector<Object> objectDispatchingAndroidInjector;


    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent
                .builder()
                .provideAppContext(this)
                .build()
                .inject(this);
        setRxErrorHandler();
    }

    private void setRxErrorHandler() {
        RxJavaPlugins.setErrorHandler((e) -> {
        });
    }


    @Override
    public AndroidInjector<Object> androidInjector() {
        return objectDispatchingAndroidInjector;
    }
}
