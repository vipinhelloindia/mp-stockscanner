package com.mp.stockscanner.scanner.repo;

import androidx.lifecycle.MutableLiveData;

import com.mp.stockscanner.base.BaseViewModel;
import com.mp.stockscanner.base.Resource;
import com.mp.stockscanner.base.Status;
import com.mp.stockscanner.models.StockScanner;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainViewModel extends BaseViewModel {
    private MpScannerRepository mpScannerRepository;


    protected MutableLiveData<Resource<ArrayList<StockScanner>>> mutableLiveData = new MutableLiveData<>();

    public MainViewModel() {
    }

    @Inject
    public MainViewModel(MpScannerRepository mpScannerRepository) {
        this.mpScannerRepository = mpScannerRepository;
    }

    public void getStockScan() {
        mpScannerRepository.fetchMpScanStockData()
                .doOnSubscribe(this::addToDisposable)
                .subscribe(new Observer<Resource>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Resource resource) {
                        mutableLiveData.postValue(resource);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mutableLiveData.postValue(new Resource<>(Status.ERROR, new ArrayList<>(), "Error message"));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public MutableLiveData<Resource<ArrayList<StockScanner>>> getMutableLiveData() {
        return mutableLiveData;
    }

    @Override
    protected void onCleared() {
        super.onStop();
        super.onCleared();
    }

    public void set(MpScannerRepository mpScannerRepository) {
        this.mpScannerRepository = mpScannerRepository;
    }
}
