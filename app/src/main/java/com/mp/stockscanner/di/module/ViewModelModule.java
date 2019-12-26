package com.mp.stockscanner.di.module;


import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.mp.stockscanner.di.factory.ViewModelFactory;

import java.util.Map;

import javax.inject.Provider;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class ViewModelModule {

    @Provides
    static ViewModelProvider.Factory provideViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap) {
        return new ViewModelFactory(providerMap);
    }
}