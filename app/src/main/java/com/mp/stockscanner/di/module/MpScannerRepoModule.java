package com.mp.stockscanner.di.module;


import androidx.lifecycle.ViewModel;

import com.mp.stockscanner.di.factory.ViewModelKey;
import com.mp.stockscanner.scanner.repo.MainViewModel;
import com.mp.stockscanner.scanner.repo.MpScannerRepository;
import com.mp.stockscanner.scanner.repo.MpScannerRepositoryImpl;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

;

@Module
public abstract class MpScannerRepoModule {
    @Binds
    abstract MpScannerRepository getScannerRepositoryManager(MpScannerRepositoryImpl mpScannerRepository);

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    protected abstract ViewModel requestMainViewModel(MainViewModel mainViewModel);
}
