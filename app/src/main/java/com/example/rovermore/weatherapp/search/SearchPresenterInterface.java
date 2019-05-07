package com.example.rovermore.weatherapp.search;

import com.example.rovermore.weatherapp.datamodel.location.Location;

public interface SearchPresenterInterface {

    void fetchLocation(String string);

    void saveLocationOnDatabase(Location location);
}
