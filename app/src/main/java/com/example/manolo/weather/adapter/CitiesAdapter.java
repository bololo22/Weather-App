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
import android.os.Handler;

import com.example.manolo.weather.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import model.Channel;

import com.squareup.picasso.Picasso;

/**
 * Created by manolofernandez on 9/28/17.
 */

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.ViewHolder> {
    private static final int PENDING_REMOVAL_TIMEOUT = 3000;

    List<Channel> mCitiesList;
    Activity mActivity;
    List<Channel> itemsPendingRemoval;
    boolean undoOn = true;
    Handler handler = new Handler();
    HashMap<Channel, Runnable> pendingRunnables = new HashMap<>();

    public CitiesAdapter(Activity mActivity, List<Channel> mCitiesList) {
        this.mActivity = mActivity;
        this.mCitiesList = mCitiesList;
        this.itemsPendingRemoval = new ArrayList<>();
    }

    @Override
    public CitiesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cities_adapter_container_item, parent, false);
        return new CitiesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CitiesAdapter.ViewHolder holder, int position) {
        Channel city = mCitiesList.get(position);

        holder.cityName.setText(city.getLocation().getCity() + ", " + city.getLocation().getRegion());
        transformCodeToIcon(holder.weatherIcon, city.getItem().getCondition().getCode());
        holder.temperatureText.setText(city.getItem().getCondition().getTemp() + " \u00B0" + city.getUnits().getTemperature());
    }

    @Override
    public int getItemCount() {
        return mCitiesList.size();
    }

    public void setUndoOn(boolean undoOn) {
        this.undoOn = undoOn;
    }

    public boolean isUndoOn() {
        return undoOn;
    }

    public void pendingRemoval(int position) {
        final Channel item = mCitiesList.get(position);
        if (!itemsPendingRemoval.contains(item)) {
            itemsPendingRemoval.add(item);
            // this will redraw row in "undo" state
            notifyItemChanged(position);
            // let's create, store and post a runnable to remove the item
            Runnable pendingRemovalRunnable = new Runnable() {
                @Override
                public void run() {
                    remove(mCitiesList.indexOf(item));
                }
            };
            handler.postDelayed(pendingRemovalRunnable, PENDING_REMOVAL_TIMEOUT);
            pendingRunnables.put(item, pendingRemovalRunnable);
        }
    }

    public void remove(int position) {
        Channel item = mCitiesList.get(position);
        if (itemsPendingRemoval.contains(item)) {
            itemsPendingRemoval.remove(item);
        }
        if (mCitiesList.contains(item)) {
            mCitiesList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public boolean isPendingRemoval(int position) {
        Channel item = mCitiesList.get(position);
        return itemsPendingRemoval.contains(item);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        @InjectView(R.id.city_item_layout)
        RelativeLayout cityItem;
        @InjectView(R.id.city_text_view)
        TextView cityName;
        @InjectView(R.id.city_weather_image)
        ImageView weatherIcon;
        @InjectView(R.id.temperature_text_view)
        TextView temperatureText;
        @InjectView(R.id.undo_button)
        Button undoButton;

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
                weatherImage.setImageResource(R.mipmap.weather_none_available);
                break;
            case 3:
            case 4:
                weatherImage.setImageResource(R.mipmap.weather_storm);
                break;
            case 5:
            case 6:
            case 7:
                weatherImage.setImageResource(R.mipmap.weather_snow_rain);
            case 8:
            case 9:
                weatherImage.setImageResource(R.mipmap.weather_drizzle_day);
                break;
            case 10:
                weatherImage.setImageResource(R.mipmap.weather_hail);
                break;
            case 11:
            case 12:
                weatherImage.setImageResource(R.mipmap.weather_showers_day);
            case 13:
            case 14:
            case 15:
            case 16:
                weatherImage.setImageResource(R.mipmap.weather_snow_scattered_day);
                break;
            case 17:
            case 18:
                weatherImage.setImageResource(R.mipmap.weather_hail);
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
                weatherImage.setImageResource(R.mipmap.weather_none_available);
                break;
            case 26:
                weatherImage.setImageResource(R.mipmap.weather_clouds);
                break;
            case 27:
            case 29:
                weatherImage.setImageResource(R.mipmap.weather_clouds_night);
            case 28:
            case 30:
                weatherImage.setImageResource(R.mipmap.weather_few_clouds);
                break;
            case 31:
            case 33:
                weatherImage.setImageResource(R.mipmap.weather_clear_night);
                break;
            case 32:
            case 34:
            case 36:
                weatherImage.setImageResource(R.mipmap.weather_clear);
                break;
            case 35:
                weatherImage.setImageResource(R.mipmap.weather_hail);
                break;
            case 37:
            case 38:
            case 39:
            case 40:
                weatherImage.setImageResource(R.mipmap.weather_storm);
                break;
            case 41:
            case 42:
            case 43:
                weatherImage.setImageResource(R.mipmap.weather_snow);
                break;
            case 44:
                weatherImage.setImageResource(R.mipmap.weather_clouds);
                break;
            case 45:
            case 47:
                weatherImage.setImageResource(R.mipmap.weather_storm);
                break;
            case 46:
                weatherImage.setImageResource(R.mipmap.weather_snow);
                break;
            case 3200:
            default:
                weatherImage.setImageResource(R.mipmap.weather_none_available);
                break;

        }
    }
}
