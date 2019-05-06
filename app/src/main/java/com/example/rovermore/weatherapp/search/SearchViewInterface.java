package com.example.rovermore.weatherapp.search;

import android.view.View;

import com.example.rovermore.weatherapp.datamodel.location.Location;

import java.util.List;

public interface SearchViewInterface {

    void receiveResults(List<Location> locationList);

    void receiveErrorFromSearch(View view);
}
