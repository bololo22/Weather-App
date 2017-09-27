package com.example.manolo.weather.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manolo.weather.App;
import com.example.manolo.weather.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.Channel;
import model.Query;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import service.interfaces.WeatherService;
import ui.MainActivityContract;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {
    @BindView(R.id.main_activity_text_view)
    private TextView textView;
    @BindView(R.id.getWeatherButton)
    private Button weatherButton;

    @Inject
    Retrofit retrofit;
    String cities = "\'nome, ak\', \'los angeles, ca\'";
    String queryCities = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text in (\'%s\'))", cities);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ((App) getApplication()).getAppComponent().inject(this);
    }

    //@OnClick(R.id.getWeatherButton)
    public void onGetWeatherButtonClick(View v){
        Call<model.Response> channel = retrofit.create(WeatherService.class).getWeather(queryCities, "json");

        //Enque the call
        channel.enqueue(new Callback<model.Response>() {
            @Override
            public void onResponse(Call<model.Response> call, Response<model.Response> response) {
                textView.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<model.Response> call, Throwable t) {
                textView.setText(t.toString());
            }
        });
    }

    @Override
    public void close() {

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}
