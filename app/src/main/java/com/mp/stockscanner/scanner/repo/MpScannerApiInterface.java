package com.mp.stockscanner.scanner.repo;

import com.mp.stockscanner.models.StockScanner;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MpScannerApiInterface {
    @GET("/data")
    Observable<ArrayList<StockScanner>> getStockScanner();
}
