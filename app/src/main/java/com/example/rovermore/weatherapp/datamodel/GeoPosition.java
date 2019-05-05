
package com.example.rovermore.weatherapp.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GeoPosition implements Serializable
{

    @SerializedName("Latitude")
    @Expose
    private Float latitude;
    @SerializedName("Longitude")
    @Expose
    private Float longitude;
    @SerializedName("Elevation")
    @Expose
    private Elevation elevation;
    private final static long serialVersionUID = -104390369618765099L;

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Elevation getElevation() {
        return elevation;
    }

    public void setElevation(Elevation elevation) {
        this.elevation = elevation;
    }

}
