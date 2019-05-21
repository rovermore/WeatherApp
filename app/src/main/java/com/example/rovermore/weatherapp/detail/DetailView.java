package com.example.rovermore.weatherapp.detail;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.rovermore.weatherapp.R;
import com.example.rovermore.weatherapp.datamodel.currentweather.Metric;
import com.example.rovermore.weatherapp.datamodel.currentweather.Temperature;
import com.example.rovermore.weatherapp.datamodel.currentweather.Weather;
import com.example.rovermore.weatherapp.main.MainView;

public class DetailView extends AppCompatActivity implements DetailViewInterface{

    private String locationName;
    private String locationKey;
    private DetailPresenterInterface detailPresenterInterface;

    private Toolbar toolbar;
    private TextView temperatureTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        temperatureTextView = (TextView) findViewById(R.id.tv_temperature);

        detailPresenterInterface = new DetailPresenter(getApplicationContext(), this);

        locationName = getIntent().getStringExtra(MainView.LOCATION_NAME);
        setTitle(locationName);

        locationKey = getIntent().getStringExtra(MainView.LOCATION_KEY);
        passLocationKeyToPresenter(locationKey);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void passLocationKeyToPresenter(String locationKey) {
        detailPresenterInterface.fetchCurrentWeather(locationKey);
    }

    @Override
    public void onReceiveWeather(Weather weather) {

        Temperature temperature = weather.getTemperature();
        Metric metricTemperature = temperature.getMetric();
        Float temperatureCelsius = metricTemperature.getValue();
        temperatureTextView.setText(String.valueOf(temperatureCelsius));
    }

    @Override
    public void onReceiveErrorFromFetch(Throwable t) {
        Snackbar.make(toolbar,"Error when receive data from server",Snackbar.LENGTH_INDEFINITE)
                .setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        passLocationKeyToPresenter(locationKey);
                    }
                }).show();
    }
}
