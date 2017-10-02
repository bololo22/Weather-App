package com.example.manolo.weather.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manolo.weather.R;
import com.example.manolo.weather.adapter.CitiesAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import model.Channel;
import timber.log.Timber;

/**
 * Created by manolofernandez on 9/27/17.
 */

public class ShowCitiesWeatherFragment extends Fragment {
    OnShowCitiesFragmentAddListener mOnShowCitiesFragmentAddListenerCallback;
    Activity mActivity;
    List<Channel> mChannelList;
    CitiesAdapter citiesAdapter;

    @InjectView(R.id.newFloatingActionButton)
    FloatingActionButton fab;
    @InjectView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    public ShowCitiesWeatherFragment(Activity activity){
        this.mActivity = activity;
        mChannelList = new ArrayList<>();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mOnShowCitiesFragmentAddListenerCallback = (OnShowCitiesFragmentAddListener) mActivity;
            this.mChannelList = new ArrayList<>();
            citiesAdapter = new CitiesAdapter(mActivity, mChannelList);

        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view =  inflater.inflate(R.layout.fragment_show_cities_weather, container, false);
        ButterKnife.inject(this, view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setAdapter(citiesAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @OnClick(R.id.newFloatingActionButton)
    public void onClick(){
        mOnShowCitiesFragmentAddListenerCallback.onAddCityButtonClick();
    }

    public void updateRecyclerView(List<Channel> mCitiesList) {
        mChannelList.clear();
        mChannelList.addAll(mCitiesList);
        citiesAdapter.notifyDataSetChanged();
    }

    public void setRecyclerView(List<Channel> mCitiesList){

    }

    public interface OnShowCitiesFragmentAddListener {
        void onAddCityButtonClick();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.d("");
    }
}
