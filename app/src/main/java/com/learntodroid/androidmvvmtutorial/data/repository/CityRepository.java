package com.learntodroid.androidmvvmtutorial.data.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.learntodroid.androidmvvmtutorial.data.local.CityDatabase;
import com.learntodroid.androidmvvmtutorial.data.model.City;
import com.learntodroid.androidmvvmtutorial.data.model.CityDao;
import com.learntodroid.androidmvvmtutorial.data.remote.GeoDBCitiesResponse;
import com.learntodroid.androidmvvmtutorial.data.remote.GeoDBCitiesWebService;
import com.learntodroid.androidmvvmtutorial.data.remote.GeoDBCityDetailsResponse;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class CityRepository {
    public static final String BASE_URL = "https://wft-geo-db.p.rapidapi.com/";

    private static CityRepository instance;
    private GeoDBCitiesWebService geoDBCitiesWebService;

    private MutableLiveData<List<City>> citiesLiveData;

    private MutableLiveData<City> cityDetailsLiveData;
    private LiveData<List<City>> favouriteCitiesLiveData;

    private CityDao cityDao;

    public CityRepository(Application application) {
        CityDatabase db = CityDatabase.getDatabase(application);
        cityDao = db.cityDao();

        geoDBCitiesWebService = getGeoDBCitiesWebService();
        citiesLiveData = new MutableLiveData<>();
        cityDetailsLiveData = new MutableLiveData<>();
        favouriteCitiesLiveData = cityDao.getFavouriteCities();
//        favouriteCitiesLiveData = new MutableLiveData<>();
    }

    public static synchronized CityRepository getInstance(Application application) {
        if (instance == null) {
            instance = new CityRepository(application);
        }
        return instance;
    }

    public GeoDBCitiesWebService getGeoDBCitiesWebService() {
        if (geoDBCitiesWebService == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.level(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            geoDBCitiesWebService = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(GeoDBCitiesWebService.class);
        }
        return geoDBCitiesWebService;
    }

    public void getCities(String namePrefix) {
        geoDBCitiesWebService.getCities(namePrefix)
                .enqueue(new Callback<GeoDBCitiesResponse>() {
                    @Override
                    public void onResponse(Call<GeoDBCitiesResponse> call, Response<GeoDBCitiesResponse> response) {
                        citiesLiveData.postValue(response.body().getData());
                    }

                    @Override
                    public void onFailure(Call<GeoDBCitiesResponse> call, Throwable t) {

                    }
                });
    }

    public void getCityDetails(String selectedCityWikiId) {
        geoDBCitiesWebService.getCityDetails(selectedCityWikiId)
                .enqueue(new Callback<GeoDBCityDetailsResponse>() {
                    @Override
                    public void onResponse(Call<GeoDBCityDetailsResponse> call, Response<GeoDBCityDetailsResponse> response) {
                        cityDetailsLiveData.postValue(response.body().getData());
                    }

                    @Override
                    public void onFailure(Call<GeoDBCityDetailsResponse> call, Throwable t) {

                    }
                });
    }

    public void favourite(City city) {
        Log.i(CityRepository.class.getSimpleName(), "favourite");
        CityDatabase.databaseWriteExecutor.execute(() -> {
            cityDao.insert(city);
        });
    }

    public void unfavourite(City city) {
        Log.i(CityRepository.class.getSimpleName(), "unfavourite");
        CityDatabase.databaseWriteExecutor.execute(() -> {
            cityDao.delete(city);
        });
    }

    public MutableLiveData<List<City>> getCitiesLiveData() {
        return citiesLiveData;
    }

    public MutableLiveData<City> getCityDetailsLiveData() {
        return cityDetailsLiveData;
    }

    public LiveData<List<City>> getFavouriteCitiesLiveData() {
        return favouriteCitiesLiveData;
    }
}
