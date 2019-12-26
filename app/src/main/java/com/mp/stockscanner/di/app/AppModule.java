package com.mp.stockscanner.di.app;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    @Provides
    @ApplicationContext
    Context provideContext(Application context) {
        return context;
    }
}
