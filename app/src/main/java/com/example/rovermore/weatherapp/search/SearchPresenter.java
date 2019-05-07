package com.example.rovermore.weatherapp.search;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.example.rovermore.weatherapp.AccuWeatherAPI;
import com.example.rovermore.weatherapp.NetworkUtils;
import com.example.rovermore.weatherapp.adapter.LocationAdapter;
import com.example.rovermore.weatherapp.database.AppDatabase;
import com.example.rovermore.weatherapp.datamodel.location.Location;
import com.example.rovermore.weatherapp.threads.AppExecutors;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchPresenter implements LocationAdapter.OnViewClicked {

    private static final String TAG = SearchPresenter.class.getSimpleName();

    private SearchViewInterface searchViewInterface;
    private LocationAdapter.OnViewClicked onViewClicked;
    private AppDatabase appDatabase;

    public SearchPresenter(Context context, SearchViewInterface searchViewInterface){
       this.searchViewInterface = searchViewInterface;
       this.onViewClicked = this;
       appDatabase = AppDatabase.getInstance(context);
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
                    searchViewInterface.receiveResults(locationList);
                    searchViewInterface.onReceiveOnClickViewInterface(onViewClicked);
                }
            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable t) {
                Log.d(TAG,"ERROR: " + t.toString());
                searchViewInterface.receiveErrorFromSearch(view);
            }
        });
    }

    @Override
    public void passClicked(final Location location) {
        final String tagMessage = "Location saved in Database";
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                appDatabase.eventDao().insertLocation(location);
                Log.d(TAG,tagMessage);
            }
        });

        searchViewInterface.onLocationSavedInDatabase(tagMessage);
    }
}
