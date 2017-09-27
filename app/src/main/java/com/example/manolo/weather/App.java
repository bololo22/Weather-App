package com.example.manolo.weather;

import android.app.Application;

import inject.AppComponent;
import inject.AppModule;
import inject.DaggerAppComponent;
import service.ServiceModule;

/**
 * Created by manolofernandez on 9/26/17.
 */

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .serviceModule(new ServiceModule("https://query.yahooapis.com/"))
                .build();
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }
}
