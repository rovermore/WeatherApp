package com.example.rovermore.weatherapp.search;

import com.example.rovermore.weatherapp.datamodel.location.Location;

import java.util.List;

public interface SearchViewInterface {

    void receiveResults(List<Location> locationList);

    void receiveErrorFromSearch(Throwable t);

    void onLocationSavedInDatabase(String string);
}
