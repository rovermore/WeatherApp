package com.example.rovermore.weatherapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.rovermore.weatherapp.database.AppDatabase;
import com.example.rovermore.weatherapp.datamodel.location.Location;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private static final String TAG = MainViewModel.class.getSimpleName();

    private LiveData<List<Location>> locations;

    public MainViewModel(@NonNull Application application) {
        super(application);

        AppDatabase database = AppDatabase.getInstance(this.getApplication());
        locations = database.eventDao().loadAllLocation();
    }

    public LiveData<List<Location>> getEvents(){return locations;}

}
