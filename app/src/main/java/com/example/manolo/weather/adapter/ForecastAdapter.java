package com.example.manolo.weather.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.manolo.weather.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import model.Channel;
import model.channel.item.Forecast;

/**
 * Created by manolofernandez on 10/2/17.
 */

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder> {
    Activity mActivity;
    List<Forecast> mForecastList;

    public ForecastAdapter(Activity mActivity, List<Forecast> mForecastList) {
        this.mActivity = mActivity;
        this.mForecastList = mForecastList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cities_forecast_item, parent, false);
        return new ForecastAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Forecast forecast = mForecastList.get(position);

        holder.day_text.setText(forecast.getDay());
        holder.date_text.setText(forecast.getDate());
        transformCodeToIcon(holder.city_weather_image, forecast.getCode());
        holder.code_text.setText(forecast.getText());
        holder.high_text.setText(String.format(mActivity.getResources().getString(R.string.high),
                forecast.getHigh()));
        holder.low_text.setText(String.format(mActivity.getResources().getString(R.string.low),
                forecast.getLow()));
    }

    @Override
    public int getItemCount() {
        return mForecastList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        @InjectView(R.id.day_text)
        TextView day_text;
        @InjectView(R.id.date_text)
        TextView date_text;
        @InjectView(R.id.city_weather_image)
        ImageView city_weather_image;
        @InjectView(R.id.code_text)
        TextView code_text;
        @InjectView(R.id.high_text)
        TextView high_text;
        @InjectView(R.id.low_text)
        TextView low_text;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

    private void transformCodeToIcon(ImageView weatherImage, String code){
        int wCode = Integer.parseInt(code);
        switch (wCode){
            case 0:
            case 1:
            case 2:
                Picasso.with(mActivity).load(R.mipmap.weather_none_available).resize(100, 100).into(weatherImage);
                break;
            case 3:
            case 4:
                Picasso.with(mActivity).load(R.mipmap.weather_storm).resize(100, 100).into(weatherImage);
                break;
            case 5:
            case 6:
            case 7:
                Picasso.with(mActivity).load(R.mipmap.weather_snow_rain).resize(100, 100).into(weatherImage);
            case 8:
            case 9:
                Picasso.with(mActivity).load(R.mipmap.weather_drizzle_day).resize(100, 100).into(weatherImage);
                break;
            case 10:
                Picasso.with(mActivity).load(R.mipmap.weather_hail).resize(100, 100).into(weatherImage);
                break;
            case 11:
            case 12:
                Picasso.with(mActivity).load(R.mipmap.weather_showers_day).resize(100, 100).into(weatherImage);
            case 13:
            case 14:
            case 15:
            case 16:
                Picasso.with(mActivity).load(R.mipmap.weather_snow_scattered_day).resize(100, 100).into(weatherImage);
                break;
            case 17:
            case 18:
                Picasso.with(mActivity).load(R.mipmap.weather_hail).resize(100, 100).into(weatherImage);
                break;
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
                Picasso.with(mActivity).load(R.mipmap.weather_fog).resize(100, 100).into(weatherImage);
                break;
            case 25:
                Picasso.with(mActivity).load(R.mipmap.weather_none_available).resize(100, 100).into(weatherImage);
                break;
            case 26:
                Picasso.with(mActivity).load(R.mipmap.weather_clouds).resize(100, 100).into(weatherImage);
                break;
            case 27:
            case 29:
                Picasso.with(mActivity).load(R.mipmap.weather_clouds_night).resize(100, 100).into(weatherImage);
            case 28:
            case 30:
                Picasso.with(mActivity).load(R.mipmap.weather_few_clouds).resize(100, 100).into(weatherImage);
                break;
            case 31:
            case 33:
                Picasso.with(mActivity).load(R.mipmap.weather_clear_night).resize(100, 100).into(weatherImage);
                break;
            case 32:
            case 34:
            case 36:
                Picasso.with(mActivity).load(R.mipmap.weather_clear).resize(100, 100).into(weatherImage);
                break;
            case 35:
                Picasso.with(mActivity).load(R.mipmap.weather_hail).resize(100, 100).into(weatherImage);
                break;
            case 37:
            case 38:
            case 39:
            case 40:
                Picasso.with(mActivity).load(R.mipmap.weather_storm).resize(100, 100).into(weatherImage);
                break;
            case 41:
            case 42:
            case 43:
                Picasso.with(mActivity).load(R.mipmap.weather_snow).resize(100, 100).into(weatherImage);
                break;
            case 44:
                Picasso.with(mActivity).load(R.mipmap.weather_clouds).resize(100, 100).into(weatherImage);
                break;
            case 45:
            case 47:
                Picasso.with(mActivity).load(R.mipmap.weather_storm).resize(100, 100).into(weatherImage);
                break;
            case 46:
                Picasso.with(mActivity).load(R.mipmap.weather_snow).resize(100, 100).into(weatherImage);
                break;
            case 3200:
            default:
                Picasso.with(mActivity).load(R.mipmap.weather_none_available).resize(100, 100).into(weatherImage);
                break;

        }
    }
}
