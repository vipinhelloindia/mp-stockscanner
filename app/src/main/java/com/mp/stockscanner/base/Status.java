package com.mp.stockscanner.base;

public enum Status {
    SUCCESS(1), ERROR(-1), LOADING(0);
    public int value;

    Status(int val) {
        value = val;
    }
}
