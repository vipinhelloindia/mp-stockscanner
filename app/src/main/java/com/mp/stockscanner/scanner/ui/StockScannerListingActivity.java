package com.mp.stockscanner.scanner.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mp.stockscanner.R;

public class StockScannerListingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, StockListingFragment.newInstance())
                    .commitNow();
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0)
            getSupportFragmentManager().popBackStackImmediate();
        else super.onBackPressed();
    }
}
