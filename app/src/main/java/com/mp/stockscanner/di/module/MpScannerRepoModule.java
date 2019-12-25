package com.mp.stockscanner.di.module;


import com.mp.stockscanner.scanner.repo.MpScannerRepository;
import com.mp.stockscanner.scanner.repo.MpScannerRepositoryImpl;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class MpScannerRepoModule {
    @Binds
    abstract MpScannerRepository getCharacterRepositoryManager(MpScannerRepositoryImpl mpScannerRepository);
}
