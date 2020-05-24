package com.learntodroid.androidmvvmtutorial.ui.citiesdetail;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.learntodroid.androidmvvmtutorial.data.model.City;
import com.learntodroid.androidmvvmtutorial.data.repository.CityRepository;

import java.util.List;

public class CityDetailViewModel extends AndroidViewModel {
    private MutableLiveData<City> cityDetailsLiveData;
    private LiveData<List<City>> favouriteCitiesLiveData;
    private String cityWikiId;

    public CityDetailViewModel(@NonNull Application application) {
        super(application);

        cityDetailsLiveData = CityRepository.getInstance(getApplication()).getCityDetailsLiveData();
        favouriteCitiesLiveData = CityRepository.getInstance(getApplication()).getFavouriteCitiesLiveData();
    }

    public void getCityDetails() {
        CityRepository.getInstance(getApplication()).getCityDetails(cityWikiId);
    }

    public void toggleCityFavourite() {
        List<City> cities = favouriteCitiesLiveData.getValue();
        if (cities != null) {
            for (City city: cities) {
                if (city.getWikiDataId().equals(cityDetailsLiveData.getValue().getWikiDataId())) {
                    Log.i(CityDetailViewModel.class.getSimpleName(), "Unfavourite");
                    CityRepository.getInstance(getApplication()).unfavourite(cityDetailsLiveData.getValue());
                    return;
                }
            }
        }
        Log.i(CityDetailViewModel.class.getSimpleName(), "Favourite");
        CityRepository.getInstance(getApplication()).favourite(cityDetailsLiveData.getValue());
    }

    public boolean isSelectedCityFavourite() {
        boolean isFavourite = false;
        for (City favCity: favouriteCitiesLiveData.getValue()) {
            if (favCity.getWikiDataId().equals(cityWikiId)) {
                isFavourite = true;
            }
        }
        return isFavourite;
    }

    public MutableLiveData<City> getCityDetailsLiveData() {
        return cityDetailsLiveData;
    }

    public LiveData<List<City>> getFavouriteCitiesLiveData() {
        return favouriteCitiesLiveData;
    }

    public String getCityWikiId() {
        return cityWikiId;
    }

    public void setCityWikiId(String cityWikiId) {
        this.cityWikiId = cityWikiId;
    }
}
