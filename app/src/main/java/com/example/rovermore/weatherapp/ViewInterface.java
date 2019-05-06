package com.example.rovermore.weatherapp;

import android.view.View;

import com.example.rovermore.weatherapp.datamodel.Location;

import java.util.List;

public interface ViewInterface {

    void receiveResults(List<Location> locationList);

    void receiveErrorFromSearch(View view);
}
