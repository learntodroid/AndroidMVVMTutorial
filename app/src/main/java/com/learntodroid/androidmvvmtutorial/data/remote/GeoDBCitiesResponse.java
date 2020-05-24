package com.learntodroid.androidmvvmtutorial.data.remote;

import com.learntodroid.androidmvvmtutorial.data.model.City;

import java.util.List;

public class GeoDBCitiesResponse {
    private List<City> data;

    public GeoDBCitiesResponse(List<City> data) {
        this.data = data;
    }

    public List<City> getData() {
        return data;
    }

    public void setData(List<City> data) {
        this.data = data;
    }
}
