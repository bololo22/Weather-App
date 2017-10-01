package com.example.manolo.weather.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.manolo.weather.App;
import com.example.manolo.weather.R;
import com.example.manolo.weather.fragment.AddCityFragment;
import com.example.manolo.weather.fragment.CityWeatherDetailedFragment;
import com.example.manolo.weather.fragment.ShowCitiesWeatherFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import model.Channel;
import model.Query;
import util.Settings;

public class MainActivity extends AppCompatActivity implements ShowCitiesWeatherFragment.OnFABClickListener, AddCityFragment.OnSearchCityListener {
    private boolean mShowingAddCityFragment = false;
    private boolean mShowingCityWeatherDeailedFragment = false;
    private boolean mShowingCitiesWeatherFragment = false;

    ShowCitiesWeatherFragment showCitiesWeatherFragment;
    AddCityFragment addCityFragment;
    CityWeatherDetailedFragment cityWeatherDetailedFragment;

    List<Channel> mCitiesList = new ArrayList<Channel>();

    Settings settings;

    public MainActivity(){
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        ((App) getApplication()).getAppComponent().inject(this);
        settings = new Settings(this);
        setShowCitiesWeatherFragment();
    }

    private void setShowCitiesWeatherFragment() {
        showCitiesWeatherFragment = new ShowCitiesWeatherFragment(this);
        getFragmentManager()
                .beginTransaction()
                .add(R.id.container, showCitiesWeatherFragment)
                .commitAllowingStateLoss();

        mShowingCitiesWeatherFragment = true;

        //mCitiesList = settings.loadCity();
    }

    @Override
    public void onBackPressed() {
        if(showCitiesWeatherFragment != null && showCitiesWeatherFragment.isVisible()){
            mShowingCitiesWeatherFragment = true;
        }
        super.onBackPressed();
    }

    @Override
    public void onFABClick() {
        addCityFragment = new AddCityFragment(this);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, addCityFragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();
        mShowingAddCityFragment = true;
        mShowingCitiesWeatherFragment = false;
    }

    @Override
    public void onSearchCityListener(Query response) {
        getFragmentManager().popBackStack();
        mShowingAddCityFragment = true;
        mShowingCitiesWeatherFragment = false;
        processCityWeatherResponse(response);
    }

    @Override
    public void onSearchCityFailListener() {
        Toast.makeText(this, "An error occurred fetching the Weather", Toast.LENGTH_SHORT).show();
    }

    private void processCityWeatherResponse(Query response) {
        //Logica para guardar las ciudad en Map
        //
        //
        // END
        Channel city = response.getResults().getChannel();

        mCitiesList.add(city);

        showCitiesWeatherFragment.setRecyclerView(mCitiesList);
    }
}
