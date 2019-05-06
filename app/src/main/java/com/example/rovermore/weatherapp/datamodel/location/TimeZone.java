
package com.example.rovermore.weatherapp.datamodel.location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TimeZone implements Serializable
{

    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("GmtOffset")
    @Expose
    private Integer gmtOffset;
    @SerializedName("IsDaylightSaving")
    @Expose
    private Boolean isDaylightSaving;
    @SerializedName("NextOffsetChange")
    @Expose
    private String nextOffsetChange;
    private final static long serialVersionUID = -8442077482622157691L;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGmtOffset() {
        return gmtOffset;
    }

    public void setGmtOffset(Integer gmtOffset) {
        this.gmtOffset = gmtOffset;
    }

    public Boolean getIsDaylightSaving() {
        return isDaylightSaving;
    }

    public void setIsDaylightSaving(Boolean isDaylightSaving) {
        this.isDaylightSaving = isDaylightSaving;
    }

    public String getNextOffsetChange() {
        return nextOffsetChange;
    }

    public void setNextOffsetChange(String nextOffsetChange) {
        this.nextOffsetChange = nextOffsetChange;
    }

}
