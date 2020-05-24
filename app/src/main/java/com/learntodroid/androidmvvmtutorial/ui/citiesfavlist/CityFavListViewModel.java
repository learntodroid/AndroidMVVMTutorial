package com.learntodroid.androidmvvmtutorial.ui.citiesfavlist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.learntodroid.androidmvvmtutorial.data.model.City;
import com.learntodroid.androidmvvmtutorial.data.repository.CityRepository;

import java.util.List;

public class CityFavListViewModel extends AndroidViewModel {
    private LiveData<List<City>> favouriteCitiesLiveData;

    public CityFavListViewModel(@NonNull Application application) {
        super(application);
        favouriteCitiesLiveData = CityRepository.getInstance(getApplication()).getFavouriteCitiesLiveData();
    }

    public LiveData<List<City>> getFavouriteCitiesLiveData() {
        return favouriteCitiesLiveData;
    }
}
