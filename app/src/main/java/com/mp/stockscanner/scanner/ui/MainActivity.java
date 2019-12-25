package com.mp.stockscanner.scanner.ui;

import android.os.Bundle;

import com.mp.stockscanner.R;
import com.mp.stockscanner.base.BaseAppCompactActivity;

public class MainActivity extends BaseAppCompactActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
    }
}
