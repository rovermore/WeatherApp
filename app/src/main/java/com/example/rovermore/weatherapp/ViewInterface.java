package com.example.rovermore.weatherapp;

import com.example.rovermore.weatherapp.datamodel.Location;

import java.util.List;

public interface ViewInterface {

    void receiveResults(List<Location> locationList);
}
