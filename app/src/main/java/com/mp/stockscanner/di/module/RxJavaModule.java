package com.mp.stockscanner.di.module;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


@Module
public class RxJavaModule {
    final public static String SUBSCRIBER_ON = "SubscribeOn";
    final public static String OBSERVER_ON = "ObserverOn";

    @Provides
    @Named(SUBSCRIBER_ON)
    Scheduler provideSubscriberOn() {
        return Schedulers.io();
    }

    @Provides
    @Named(OBSERVER_ON)
    Scheduler provideObserverOn() {
        return AndroidSchedulers.mainThread();
    }

}