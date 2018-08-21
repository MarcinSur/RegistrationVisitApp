package com.example.marcin.registrationvisitapp.data;

import androidx.annotation.Nullable;

public class Resources<T>{
    @Nullable
    public final String message;
    @Nullable
    public final T data;

    public Resources(String status, T data) {
        this.message = status;
        this.data = data;
    }

    public static <T> Resources<T> loading(@Nullable T data){
        return new Resources<>(null, data);
    }
    public static <T> Resources<T> success(@Nullable T data){
        return new Resources<>(null,data);
    }
}
