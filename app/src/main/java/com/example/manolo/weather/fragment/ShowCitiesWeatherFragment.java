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

/**
 * Created by manolofernandez on 9/27/17.
 */

public class ShowCitiesWeatherFragment extends Fragment {
    OnFABClickListener mOnFABClickListenerCallback;
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
            mOnFABClickListenerCallback = (OnFABClickListener) mActivity;
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
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        citiesAdapter = new CitiesAdapter(mActivity, mChannelList);
        mRecyclerView.setAdapter(citiesAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setHasFixedSize(true);
    }

    @OnClick(R.id.newFloatingActionButton)
    public void onClick(){
        mOnFABClickListenerCallback.onFABClick();
    }

    public void setRecyclerView(List<Channel> mCitiesList) {
        mChannelList = mCitiesList;
        citiesAdapter.notifyDataSetChanged();
    }

    public interface OnFABClickListener{
        void onFABClick();
    }
}
