package com.example.manolo.weather.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manolo.weather.R;

/**
 * Created by manolofernandez on 9/27/17.
 */

public class CityWeatherDetailedFragment extends Fragment {
    public CityWeatherDetailedFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_city_weather_detailed, container, false);
    }
}
