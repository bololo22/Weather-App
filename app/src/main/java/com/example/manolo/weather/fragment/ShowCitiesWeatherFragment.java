package com.example.manolo.weather.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
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

public class ShowCitiesWeatherFragment extends Fragment implements CitiesAdapter.OnCityClickListener{
    OnShowCitiesFragmentAddListener mOnShowCitiesFragmentAddListenerCallback;
    Activity mActivity;
    List<Channel> mChannelList;
    CitiesAdapter citiesAdapter;

    @InjectView(R.id.newFloatingActionButton)
    FloatingActionButton fab;
    @InjectView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    public ShowCitiesWeatherFragment(){
        Timber.d("");
    }

    public ShowCitiesWeatherFragment(Activity activity){
        this.mActivity = activity;
        mOnShowCitiesFragmentAddListenerCallback = (OnShowCitiesFragmentAddListener) mActivity;
        this.mChannelList = new ArrayList<>();
        citiesAdapter = new CitiesAdapter(mActivity, mChannelList, this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{

        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null){
            mChannelList = savedInstanceState.getParcelableArrayList("ChannelList");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view =  inflater.inflate(R.layout.fragment_show_cities_weather, container, false);
        ButterKnife.inject(this, view);

        if(savedInstanceState != null){
            this.mActivity = getActivity();
            mOnShowCitiesFragmentAddListenerCallback = (OnShowCitiesFragmentAddListener) mActivity;
            if(mChannelList == null) {
                this.mChannelList = savedInstanceState.getParcelableArrayList("ChannelList");
            }
            citiesAdapter = new CitiesAdapter(mActivity, mChannelList, this);
        }

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

    @Override
    public void onCityClickListener(View v, int position) {
        mOnShowCitiesFragmentAddListenerCallback.onCityClick(mChannelList.get(position));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("ChannelList", (ArrayList<? extends Parcelable>) mChannelList);
    }

    public interface OnShowCitiesFragmentAddListener {
        void onAddCityButtonClick();
        void onCityClick(Channel channel);
    }
}
