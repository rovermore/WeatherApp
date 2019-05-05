package com.example.rovermore.weatherapp;

import com.example.rovermore.weatherapp.datamodel.Location;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class NetworkUtils {

    public static final String BASE_URL =
            "http://dataservice.accuweather.com/";

    public static OkHttpClient GetClient(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        return  httpClient.addInterceptor(logging).build();
    }

    public interface AccuWeatherAPI {
        @GET("locations/v1/cities/search?apikey=mlk2W5uoq01rSXsjGJDPVkGCSAQNroKK")
        Call<List<Location>> getLocationResults(@Query("q") String location);
    }

}
