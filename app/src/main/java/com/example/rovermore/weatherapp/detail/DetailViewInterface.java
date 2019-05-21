package com.example.rovermore.weatherapp.detail;

import com.example.rovermore.weatherapp.datamodel.currentweather.Weather;

public interface DetailViewInterface {

    void onReceiveWeather(Weather weather);

    void onReceiveErrorFromFetch(Throwable t);

}
