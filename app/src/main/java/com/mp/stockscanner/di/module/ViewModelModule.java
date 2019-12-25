package com.mp.stockscanner.di.module;


import androidx.lifecycle.ViewModelProvider;

import com.mp.stockscanner.di.factory.ViewModelFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

}