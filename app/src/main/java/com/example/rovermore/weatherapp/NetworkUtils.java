package com.example.rovermore.weatherapp;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class NetworkUtils {

    public static final String BASE_URL =
            "http://dataservice.accuweather.com/";

    public static OkHttpClient GetClient(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        return  httpClient.addInterceptor(logging).build();
    }
}
