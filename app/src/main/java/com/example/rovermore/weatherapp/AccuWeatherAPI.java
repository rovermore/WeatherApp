package com.example.rovermore.weatherapp;

import com.example.rovermore.weatherapp.datamodel.Location;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AccuWeatherAPI {

    @GET("locations/v1/cities/search?apikey=mlk2W5uoq01rSXsjGJDPVkGCSAQNroKK")
    Call<List<Location>> getLocationResults(@Query("q") String location);
}
