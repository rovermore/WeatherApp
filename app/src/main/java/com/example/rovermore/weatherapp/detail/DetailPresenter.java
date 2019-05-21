package com.example.rovermore.weatherapp.detail;

import android.content.Context;
import android.util.Log;

import com.example.rovermore.weatherapp.AccuWeatherAPI;
import com.example.rovermore.weatherapp.NetworkUtils;
import com.example.rovermore.weatherapp.datamodel.currentweather.Weather;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailPresenter implements DetailPresenterInterface {

    private static final String TAG = DetailPresenter.class.getSimpleName();

    private Context context;
    private DetailViewInterface detailViewInterface;

    public DetailPresenter(Context context, DetailViewInterface detailViewInterface){
        this.context = context;
        this.detailViewInterface = detailViewInterface;
    }

    @Override
    public void fetchCurrentWeather(String locationKey) {

        Retrofit retrofit = new Retrofit.Builder()
                .client(NetworkUtils.GetClient())
                .baseUrl(NetworkUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AccuWeatherAPI accuWeatherAPI = retrofit.create(AccuWeatherAPI.class);
        Call<List<Weather>> call = accuWeatherAPI.getCurrentWeather(locationKey,AccuWeatherAPI.API_KEY);
        call.enqueue(new Callback<List<Weather>>() {
            @Override
            public void onResponse(Call<List<Weather>> call, Response<List<Weather>> response) {
                if(!response.isSuccessful()) {
                    Log.e(TAG,"Response code is: " + response.code());
                } else {
                    List<Weather> weatherList = response.body();
                    Weather currentWeather = weatherList.get(0);
                    detailViewInterface.onReceiveWeather(currentWeather);
                }
            }

            @Override
            public void onFailure(Call<List<Weather>> call, Throwable t) {
                detailViewInterface.onReceiveErrorFromFetch(t);
            }
        });
    }
}




