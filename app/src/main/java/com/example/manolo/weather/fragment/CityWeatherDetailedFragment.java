package com.example.manolo.weather.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.manolo.weather.R;
import com.example.manolo.weather.adapter.ForecastAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import model.Channel;
import model.channel.Item;
import model.channel.Location;
import model.channel.Units;
import model.channel.item.Condition;
import model.channel.item.Forecast;

/**
 * Created by manolofernandez on 9/27/17.
 */

public class CityWeatherDetailedFragment extends Fragment {
    Channel mChannel;
    Item mItem;
    Condition mCondition;
    Location mLocation;
    Units mUnits;
    Activity mActivity;
    List<Forecast> mForecastList;
    ForecastAdapter mForecastAdapter;

    @InjectView(R.id.weather_image)
    ImageView weatherImageView;
    @InjectView(R.id.city_text)
    TextView cityTextView;
    @InjectView(R.id.temperature_text)
    TextView temperatureTextView;
    @InjectView(R.id.description_text)
    TextView descriptionTextView;
    @InjectView(R.id.recyclerViewForecast)
    RecyclerView mRecyclerViewForecast;

    public CityWeatherDetailedFragment(){}

    public CityWeatherDetailedFragment(Activity activity, Channel channel){
        this.mActivity = activity;
        this.mChannel = channel;
        this.mItem = channel.getItem();
        this.mCondition = channel.getItem().getCondition();
        this.mLocation = channel.getLocation();
        this.mUnits = channel.getUnits();
        mForecastList = mItem.getForecast();

        mForecastAdapter = new ForecastAdapter(mActivity, mForecastList);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_city_weather_detailed, container, false);
        ButterKnife.inject(this, view);

        if(savedInstanceState != null){
            this.mActivity = getActivity();
            this.mChannel = savedInstanceState.getParcelable("Channel");
            this.mItem = mChannel.getItem();
            this.mCondition = mChannel.getItem().getCondition();
            this.mLocation = mChannel.getLocation();
            this.mUnits = mChannel.getUnits();
            mForecastList = mItem.getForecast();

            mForecastAdapter = new ForecastAdapter(mActivity, mForecastList);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        transformCodeToIcon(weatherImageView, mCondition.getCode());
        cityTextView.setText(mLocation.getCity() + ", " + mLocation.getRegion());
        temperatureTextView.setText(mCondition.getTemp() + " \u00B0" + mUnits.getTemperature());
        descriptionTextView.setText(mCondition.getText());

        mRecyclerViewForecast.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerViewForecast.setAdapter(mForecastAdapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("Channel", mChannel);
    }

    private void transformCodeToIcon(ImageView weatherImage, String code){
        int wCode = Integer.parseInt(code);
        switch (wCode){
            case 0:
            case 1:
            case 2:
                Picasso.with(mActivity).load(R.mipmap.weather_none_available).into(weatherImage);
                break;
            case 3:
            case 4:
                Picasso.with(mActivity).load(R.mipmap.weather_storm).into(weatherImage);
                break;
            case 5:
            case 6:
            case 7:
                Picasso.with(mActivity).load(R.mipmap.weather_snow_rain).into(weatherImage);
            case 8:
            case 9:
                Picasso.with(mActivity).load(R.mipmap.weather_drizzle_day).into(weatherImage);
                break;
            case 10:
                Picasso.with(mActivity).load(R.mipmap.weather_hail).into(weatherImage);
                break;
            case 11:
            case 12:
                Picasso.with(mActivity).load(R.mipmap.weather_showers_day).into(weatherImage);
            case 13:
            case 14:
            case 15:
            case 16:
                Picasso.with(mActivity).load(R.mipmap.weather_snow_scattered_day).into(weatherImage);
                break;
            case 17:
            case 18:
                Picasso.with(mActivity).load(R.mipmap.weather_hail).into(weatherImage);
                break;
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
                Picasso.with(mActivity).load(R.mipmap.weather_fog).into(weatherImage);
                break;
            case 25:
                Picasso.with(mActivity).load(R.mipmap.weather_none_available).into(weatherImage);
                break;
            case 26:
                Picasso.with(mActivity).load(R.mipmap.weather_clouds).into(weatherImage);
                break;
            case 27:
            case 29:
                Picasso.with(mActivity).load(R.mipmap.weather_clouds_night).into(weatherImage);
            case 28:
            case 30:
                Picasso.with(mActivity).load(R.mipmap.weather_few_clouds).into(weatherImage);
                break;
            case 31:
            case 33:
                Picasso.with(mActivity).load(R.mipmap.weather_clear_night).into(weatherImage);
                break;
            case 32:
            case 34:
            case 36:
                Picasso.with(mActivity).load(R.mipmap.weather_clear).into(weatherImage);
                break;
            case 35:
                Picasso.with(mActivity).load(R.mipmap.weather_hail).into(weatherImage);
                break;
            case 37:
            case 38:
            case 39:
            case 40:
                Picasso.with(mActivity).load(R.mipmap.weather_storm).into(weatherImage);
                break;
            case 41:
            case 42:
            case 43:
                Picasso.with(mActivity).load(R.mipmap.weather_snow).into(weatherImage);
                break;
            case 44:
                Picasso.with(mActivity).load(R.mipmap.weather_clouds).into(weatherImage);
                break;
            case 45:
            case 47:
                Picasso.with(mActivity).load(R.mipmap.weather_storm).into(weatherImage);
                break;
            case 46:
                Picasso.with(mActivity).load(R.mipmap.weather_snow).into(weatherImage);
                break;
            case 3200:
            default:
                Picasso.with(mActivity).load(R.mipmap.weather_none_available).into(weatherImage);
                break;

        }
    }
}
