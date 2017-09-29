package com.example.manolo.weather.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.manolo.weather.App;
import com.example.manolo.weather.R;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import model.Query;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import service.interfaces.WeatherService;
import timber.log.Timber;

/**
 * Created by manolofernandez on 9/25/17.
 */

public class AddCityFragment extends Fragment {
    OnSearchCityListener onSearchCityListenerCallBack;
    Activity mActivity;

    @InjectView(R.id.searchCityEditText)
    TextView searchCityEditText;
    @InjectView(R.id.newFloatingActionButtonSearch)
    FloatingActionButton fab;

    @Inject
    Retrofit retrofit;
    String cities = "\"nome, ak\""; //, "
    String queryCities = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text in (\'%s\'))", cities);

    public AddCityFragment(Activity activity){
        this.mActivity = activity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            onSearchCityListenerCallBack = (OnSearchCityListener) mActivity;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_add_city, container, false);
        ButterKnife.inject(this, view);
        ((App) mActivity.getApplication()).getAppComponent().inject(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick(R.id.newFloatingActionButtonSearch)
    public void onSearchCityWeatherClick(){
        Call<model.Response> channel = retrofit.create(WeatherService.class).getWeather(queryCities, "json");

        channel.enqueue(new Callback<model.Response>() {
            @Override
            public void onResponse(Call<model.Response> call, Response<model.Response> response) {
                if(response != null && response.body() != null && response.body().getQuery() != null) {
                    onSearchCityListenerCallBack.onSearchCityListener(response.body().getQuery());
                }
            }

            @Override
            public void onFailure(Call<model.Response> call, Throwable t) {
                Timber.e(t, "Call FAILED");
                onSearchCityListenerCallBack.onSearchCityFailListener();
            }
        });
    }

    public interface OnSearchCityListener {
        void onSearchCityListener(Query response);
        void onSearchCityFailListener();
    }
}
