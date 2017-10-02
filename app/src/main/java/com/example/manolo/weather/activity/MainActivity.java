package com.example.manolo.weather.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.manolo.weather.App;
import com.example.manolo.weather.R;
import com.example.manolo.weather.fragment.AddCityFragment;
import com.example.manolo.weather.fragment.CityWeatherDetailedFragment;
import com.example.manolo.weather.fragment.ShowCitiesWeatherFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import model.Channel;
import model.Query;
import model.QueryList;
import model.Response;
import model.ResponseList;
import model.Results;
import model.ResultsList;
import model.channel.Location;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import service.interfaces.WeatherService;
import timber.log.Timber;
import util.Settings;

public class MainActivity extends AppCompatActivity implements ShowCitiesWeatherFragment.OnShowCitiesFragmentAddListener, AddCityFragment.OnSearchCityListener {
    ShowCitiesWeatherFragment showCitiesWeatherFragment;
    AddCityFragment addCityFragment;
    CityWeatherDetailedFragment cityWeatherDetailedFragment;

    List<Channel> mCitiesList = new ArrayList<Channel>();
    Settings settings;

    @Inject
    Retrofit retrofit;

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

        retrieveWeatherFromCitiesSaved();
    }

    @Override
    public void onAddCityButtonClick() {
        addCityFragment = new AddCityFragment(this);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, addCityFragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    @Override
    public void onCityClick(Channel channel) {
        cityWeatherDetailedFragment = new CityWeatherDetailedFragment(this, channel);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, cityWeatherDetailedFragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    @Override
    public void onSearchCityListener(Query response, String city) {
        processCityWeatherResponse(response, city);
        getFragmentManager().popBackStack();
    }

    @Override
    public void onSearchCityFailListener() {
        Toast.makeText(this, "An error occurred fetching the Weather", Toast.LENGTH_SHORT).show();
    }

    private void processCityWeatherResponse(Query response, String city) {
        if(city != null && (response.getCount() == 0 || response.getResults() == null)){
            Toast.makeText(this, "No weather found for '" + city + "'", Toast.LENGTH_SHORT).show();
        }else{
            Results results = response.getResults();
            Channel channel = results.getChannel();
            Location location = channel.getLocation();

            if(city != null) {
                if (settings.loadCity() != null && !settings.loadCity().isEmpty()) {
                    String citiesSaved = settings.loadCity();
                    settings.saveCity(citiesSaved + ";" + location.getCity() + "," + location.getRegion());
                } else {
                    settings.saveCity(location.getCity() + "," + location.getRegion());
                }
            }

            mCitiesList.add(channel);
            showCitiesWeatherFragment.updateRecyclerView(mCitiesList);
        }
    }

    private void processCitiesWeatherResponse(QueryList query) {
        if(query.getCount() == 0 || query.getResults() == null){
        }else{
            ResultsList results = query.getResults();
            List<Channel> channelList = results.getChannel();

            mCitiesList.addAll(channelList);
            showCitiesWeatherFragment.updateRecyclerView(mCitiesList);
        }
    }

    private void retrieveWeatherFromCitiesSaved() {
        String cities = settings.loadCity();
        if(cities == null || cities.isEmpty()){
            return;
        }
        String[] citiesArray = cities.split(";");
        String queryCities;

        if(citiesArray.length == 1){
            queryCities = String.format(WeatherService.SUB_URL, citiesArray[0]);

            Call<Response> channel = retrofit.create(WeatherService.class).getWeather(queryCities, WeatherService.FORMAT);
            channel.enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<model.Response> call, retrofit2.Response<Response> response) {
                    if(response != null && response.body() != null && response.body().getQuery() != null) {
                        processCityWeatherResponse(response.body().getQuery(), null);
                    }
                }

                @Override
                public void onFailure(Call<model.Response> call, Throwable t) {
                    Timber.e(t, "Call FAILED");
                    onSearchCityFailListener();
                }
            });
        } else if (citiesArray.length > 1){
            StringBuilder citiesYQL = new StringBuilder();
            for (int i = 0; i < citiesArray.length; i++){
                if(i == citiesArray.length - 1){
                    citiesYQL.append(citiesArray[i]);
                }else {
                    citiesYQL.append(citiesArray[i]);
                    citiesYQL.append("\', \'");
                }
            }
            queryCities = String.format(WeatherService.SUB_URL, citiesYQL);

            Call<ResponseList> channel = retrofit.create(WeatherService.class).getWeatherList(queryCities, WeatherService.FORMAT);
            channel.enqueue(new Callback<ResponseList>() {
                @Override
                public void onResponse(Call<model.ResponseList> call, retrofit2.Response<ResponseList> response) {
                    if(response != null && response.body() != null && response.body().getQuery() != null) {
                        processCitiesWeatherResponse(response.body().getQuery());
                    }
                }

                @Override
                public void onFailure(Call<model.ResponseList> call, Throwable t) {
                    Timber.e(t, "Call FAILED");
                    onSearchCityFailListener();
                }
            });
        }
    }
}
