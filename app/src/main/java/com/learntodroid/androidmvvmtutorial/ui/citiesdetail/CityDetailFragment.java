package com.learntodroid.androidmvvmtutorial.ui.citiesdetail;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.button.MaterialButton;
import com.learntodroid.androidmvvmtutorial.R;
import com.learntodroid.androidmvvmtutorial.data.model.City;

import java.util.List;

import static com.learntodroid.androidmvvmtutorial.ui.citieslist.CityListFragment.CITY_WIKI_ID;

public class CityDetailFragment extends Fragment {
    private CityDetailViewModel cityDetailViewModel;

    private TextView cityName;
    private TextView cityWikiId;
    private TextView country;
    private TextView population;

    private Button favouriteButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cityDetailViewModel = ViewModelProviders.of(this).get(CityDetailViewModel.class);
        cityDetailViewModel.setCityWikiId(getArguments().getString(CITY_WIKI_ID));

        cityDetailViewModel.getCityDetailsLiveData().observe(this, new Observer<City>() {
            @Override
            public void onChanged(City city) {
                cityName.setText(city.getCity());
                cityWikiId.setText(city.getWikiDataId());
                country.setText(city.getCountry());
                population.setText(String.valueOf(city.getPopulation()));
            }
        });

        cityDetailViewModel.getFavouriteCitiesLiveData().observe(this, new Observer<List<City>>() {
            @Override
            public void onChanged(List<City> cities) {
                if (cityDetailViewModel.isSelectedCityFavourite()) {
                    favouriteButton.setText("Unfavourite");
                } else {
                    favouriteButton.setText("Favourite");
                }
            }
        });

        cityDetailViewModel.getCityDetails();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_citydetails, container, false);

        cityName = view.findViewById(R.id.fragment_citydetails_city);
        cityWikiId = view.findViewById(R.id.fragment_citydetails_cityWikiId);
        country = view.findViewById(R.id.fragment_citydetails_country);
        population = view.findViewById(R.id.fragment_citydetails_population);

        favouriteButton = view.findViewById(R.id.fragment_citydetails_favourite);
        favouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityDetailViewModel.toggleCityFavourite();
            }
        });

        return view;
    }
}
