package com.learntodroid.androidmvvmtutorial.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "city_table")
public class City {
    private String city;
    private String name;
    private String country;
    private String countryCode;
    private String region;
    private String regionCode;
    private float latitude;
    private float longitude;

    @PrimaryKey
    @NonNull
    private String wikiDataId;

    private int population;

    public City(String city, String name, String country, String countryCode, String region, String regionCode, float latitude, float longitude, String wikiDataId) {
        this.city = city;
        this.name = name;
        this.country = country;
        this.countryCode = countryCode;
        this.region = region;
        this.regionCode = regionCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.wikiDataId = wikiDataId;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getRegion() {
        return region;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public String getWikiDataId() {
        return wikiDataId;
    }

    public int getPopulation() {
        return population;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public void setWikiDataId(@NonNull String wikiDataId) {
        this.wikiDataId = wikiDataId;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
