package com.example.rovermore.weatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rovermore.weatherapp.adapter.LocationAdapter;
import com.example.rovermore.weatherapp.datamodel.Location;

import java.util.List;

public class SearchView extends AppCompatActivity implements ViewInterface {

    private EditText editText;
    private Button searchButton;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private LocationAdapter locationAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editText = findViewById(R.id.etQuery);
        searchButton = findViewById(R.id.buttonSearch);
        recyclerView = findViewById(R.id.recyclerViewResult);
        layoutManager = new LinearLayoutManager(this);
        locationAdapter = new LocationAdapter(this,null);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(locationAdapter);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchQuery(v);
            }
        });
    }

    private void searchQuery(View v){
        String queryString = String.valueOf(editText.getText());
        SearchPresenter searchPresenter = new SearchPresenter(v, queryString,this);

    }

    public void onReceiveList(List<Location> locationList){
        locationAdapter.clearLocationListAdapter();
        locationAdapter.setLocationList(locationList);
    }

    @Override
    public void receiveResults(List<Location> locationList) {
        onReceiveList(locationList);
    }
}