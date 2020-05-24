package com.learntodroid.androidmvvmtutorial.data.remote;

import com.learntodroid.androidmvvmtutorial.data.model.City;

public class GeoDBCityDetailsResponse {
    private City data;

    public GeoDBCityDetailsResponse(City data) {
        this.data = data;
    }

    public City getData() {
        return data;
    }
}
