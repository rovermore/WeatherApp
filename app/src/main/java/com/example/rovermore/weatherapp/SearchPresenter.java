package com.example.rovermore.weatherapp;

import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.example.rovermore.weatherapp.datamodel.Location;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchPresenter  {

    private static final String TAG = SearchPresenter.class.getSimpleName();

    private ViewInterface viewInterface;

    public SearchPresenter(View view, String location, ViewInterface viewInterface){
       this.viewInterface = viewInterface;
       passQuery(view, location);
    }


    private void passQuery(final View view, final String location) {
        Gson gson = new GsonBuilder()
                .setDateFormat("dd-MM-yyyy HH:mm")
                .create();


        Retrofit retrofit = new Retrofit.Builder()
                .client(NetworkUtils.GetClient())
                .baseUrl(NetworkUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NetworkUtils.AccuWeatherAPI accuWeatherAPI = retrofit.create(NetworkUtils.AccuWeatherAPI.class);
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
                Snackbar.make(view,"Error when connecting the network",Snackbar.LENGTH_SHORT)
                        .setAction("Retry", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                passQuery(view,location);
                            }
                        }).show();
            }
        });
    }

}
