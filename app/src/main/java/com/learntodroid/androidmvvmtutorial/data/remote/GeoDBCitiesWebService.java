package com.learntodroid.androidmvvmtutorial.data.remote;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GeoDBCitiesWebService {
    @Headers({
            "x-rapidapi-host: wft-geo-db.p.rapidapi.com",
            "x-rapidapi-key: 2ef1ca9772msh66402d2d33d3bd8p1446d8jsn76398f17d9df"
    })
    @GET("/v1/geo/cities")
    Call<GeoDBCitiesResponse> getCities(@Query("namePrefix") String namePrefix);

    @Headers({
            "x-rapidapi-host: wft-geo-db.p.rapidapi.com",
            "x-rapidapi-key: 2ef1ca9772msh66402d2d33d3bd8p1446d8jsn76398f17d9df"
    })
    @GET("/v1/geo/cities/{wikiDataId}")
    Call<GeoDBCityDetailsResponse> getCityDetails(@Path("wikiDataId") String wikiDataId);
}
