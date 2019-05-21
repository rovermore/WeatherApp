package com.example.rovermore.weatherapp;

import com.example.rovermore.weatherapp.datamodel.currentweather.Weather;
import com.example.rovermore.weatherapp.datamodel.location.Location;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AccuWeatherAPI {

    String API_KEY = "mlk2W5uoq01rSXsjGJDPVkGCSAQNroKK";

    @GET("locations/v1/cities/search?apikey=mlk2W5uoq01rSXsjGJDPVkGCSAQNroKK")
    Call<List<Location>> getLocationResults(@Query("q") String location);

    @GET("currentconditions/v1/{key}")
    Call<List<Weather>> getCurrentWeather(@Path("key") String locationKey,
                                    @Query("apikey") String apiKey);
}
