package com.example.rovermore.weatherapp;

import android.util.Log;
import android.view.View;

import com.example.rovermore.weatherapp.datamodel.Location;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchPresenter  {

    private static final String TAG = SearchPresenter.class.getSimpleName();

    private ViewInterface viewInterface;

    public SearchPresenter(ViewInterface viewInterface){
       this.viewInterface = viewInterface;

    }


    public void fetchLocation(final View view, final String location) {

        Retrofit retrofit = new Retrofit.Builder()
                .client(NetworkUtils.GetClient())
                .baseUrl(NetworkUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AccuWeatherAPI accuWeatherAPI = retrofit.create(AccuWeatherAPI.class);
        Call<List<Location>> call = accuWeatherAPI.getLocationResults(location);
        call.enqueue(new Callback<List<Location>>() {
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
                if(!response.isSuccessful()) {
                    Log.e(TAG,"Response code is: " + response.code());
                } else {
                    List<Location> locationList = response.body();
                    viewInterface.receiveResults(locationList);
                }
            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable t) {
                Log.d(TAG,"ERROR: " + t.toString());
                viewInterface.receiveErrorFromSearch(view);
            }
        });
    }

}
