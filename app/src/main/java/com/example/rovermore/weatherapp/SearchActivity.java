package com.example.rovermore.weatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.rovermore.weatherapp.adapter.LocationAdapter;
import com.example.rovermore.weatherapp.datamodel.Location;

import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private EditText editText;
    private Button searchButton;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private LocationAdapter locationAdapter;
    private OnQueryPass onQueryPass;

    interface OnQueryPass{
        void passQuery(String string);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editText = findViewById(R.id.etQuery);
        searchButton = findViewById(R.id.buttonSearch);
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerViewResult);
        layoutManager = new LinearLayoutManager(this);
        locationAdapter = new LocationAdapter(this,null);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(locationAdapter);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchQuery();
            }
        });
    }

    private void searchQuery(){
        String queryString = String.valueOf(editText.getText());
        onQueryPass.passQuery(queryString);

    }

    public void onReceiveList(List<Location> locationList){
        locationAdapter.clearLocationListAdapter();
        locationAdapter.setLocationList(locationList);
    }
}
