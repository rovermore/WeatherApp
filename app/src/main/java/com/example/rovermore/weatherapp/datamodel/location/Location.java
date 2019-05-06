
package com.example.rovermore.weatherapp.datamodel.location;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "locations")
public class Location implements Serializable
{

    @PrimaryKey(autoGenerate = true)
    private int idDatabase;
    @SerializedName("Version")
    @Expose
    private Integer version;
    @SerializedName("Key")
    @Expose
    private String keyLocation;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("Rank")
    @Expose
    private Integer rank;
    @SerializedName("LocalizedName")
    @Expose
    private String localizedName;
    @SerializedName("EnglishName")
    @Expose
    private String englishName;
    @SerializedName("PrimaryPostalCode")
    @Expose
    private String primaryPostalCode;
    @SerializedName("Region")
    @Expose
    private Region region;
    @SerializedName("Country")
    @Expose
    private Country country;
    @SerializedName("AdministrativeArea")
    @Expose
    private AdministrativeArea administrativeArea;
    @SerializedName("TimeZone")
    @Expose
    private TimeZone timeZone;
    @SerializedName("GeoPosition")
    @Expose
    private GeoPosition geoPosition;
    @SerializedName("IsAlias")
    @Expose
    private Boolean isAlias;
    @SerializedName("SupplementalAdminAreas")
    @Expose
    private List<SupplementalAdminArea> supplementalAdminAreas = null;
    @SerializedName("DataSets")
    @Expose
    private List<String> dataSets = null;
    private final static long serialVersionUID = -4367835460878030774L;

    public Location(int idDatabase, Integer version, String keyLocation, String type, Integer rank, String localizedName,
                    String englishName, String primaryPostalCode, Region region, Country country, AdministrativeArea administrativeArea,
                    TimeZone timeZone, GeoPosition geoPosition, Boolean isAlias,
                    List<SupplementalAdminArea> supplementalAdminAreas, List<String> dataSets) {
        this.idDatabase = idDatabase;
        this.version = version;
        this.keyLocation = keyLocation;
        this.type = type;
        this.rank = rank;
        this.localizedName = localizedName;
        this.englishName = englishName;
        this.primaryPostalCode = primaryPostalCode;
        this.region = region;
        this.country = country;
        this.administrativeArea = administrativeArea;
        this.timeZone = timeZone;
        this.geoPosition = geoPosition;
        this.isAlias = isAlias;
        this.supplementalAdminAreas = supplementalAdminAreas;
        this.dataSets = dataSets;
    }

    public int getIdDatabase() { return idDatabase; }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getKeyLocation() {
        return keyLocation;
    }

    public void setKeyLocation(String keyLocation) {
        this.keyLocation = keyLocation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    public void setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getPrimaryPostalCode() {
        return primaryPostalCode;
    }

    public void setPrimaryPostalCode(String primaryPostalCode) {
        this.primaryPostalCode = primaryPostalCode;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public AdministrativeArea getAdministrativeArea() {
        return administrativeArea;
    }

    public void setAdministrativeArea(AdministrativeArea administrativeArea) {
        this.administrativeArea = administrativeArea;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public GeoPosition getGeoPosition() {
        return geoPosition;
    }

    public void setGeoPosition(GeoPosition geoPosition) {
        this.geoPosition = geoPosition;
    }

    public Boolean getIsAlias() {
        return isAlias;
    }

    public void setIsAlias(Boolean isAlias) {
        this.isAlias = isAlias;
    }

    public List<SupplementalAdminArea> getSupplementalAdminAreas() {
        return supplementalAdminAreas;
    }

    public void setSupplementalAdminAreas(List<SupplementalAdminArea> supplementalAdminAreas) {
        this.supplementalAdminAreas = supplementalAdminAreas;
    }

    public List<String> getDataSets() {
        return dataSets;
    }

    public void setDataSets(List<String> dataSets) {
        this.dataSets = dataSets;
    }

}
