package com.example.marcin.registrationvisitapp.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.example.marcin.registrationvisitapp.data.Status.LOADING;
import static com.example.marcin.registrationvisitapp.data.Status.SUCCESS;

public class Resources<T> {
    @NonNull
    public final Status status;
    @Nullable
    public final T data;

    public Resources(@NonNull Status status, @Nullable T data) {
        this.status = status;
        this.data = data;
    }

    public static <T> Resources<T> loading(@Nullable T data) {
        return new Resources<>(LOADING, data);
    }

    public static <T> Resources<T> success(@Nullable T data) {
        return new Resources<>(SUCCESS, data);
    }
}

