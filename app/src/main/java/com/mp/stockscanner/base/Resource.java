package com.mp.stockscanner.base;

import androidx.annotation.Nullable;

public class Resource<T> {
    private final Status status;
    @Nullable
    private T data;
    @Nullable
    private String message;

    public Resource(Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    @Nullable
    public T getData() {
        return data;
    }

    @Nullable
    public String getMessage() {
        return message;
    }

    public void setData(@Nullable T data) {
        this.data = data;
    }

    public void setMessage(@Nullable String message) {
        this.message = message;
    }
}
