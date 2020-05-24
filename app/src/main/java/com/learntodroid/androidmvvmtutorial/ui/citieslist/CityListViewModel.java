package com.learntodroid.androidmvvmtutorial.ui.citieslist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.learntodroid.androidmvvmtutorial.data.model.City;
import com.learntodroid.androidmvvmtutorial.data.repository.CityRepository;

import java.util.List;

public class CityListViewModel extends AndroidViewModel {
    private LiveData<List<City>> citiesLiveData;

    public CityListViewModel(@NonNull Application application) {
        super(application);

        citiesLiveData = CityRepository.getInstance(application).getCitiesLiveData();
    }

    public void getCities(String namePrefix) {
        CityRepository.getInstance(getApplication()).getCities(namePrefix);
    }

    public LiveData<List<City>> getCitiesLiveData() {
        return citiesLiveData;
    }
}
