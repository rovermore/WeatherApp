package com.example.rovermore.weatherapp.search;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rovermore.weatherapp.R;
import com.example.rovermore.weatherapp.adapter.LocationAdapter;
import com.example.rovermore.weatherapp.datamodel.location.Location;

import java.util.List;

public class SearchView extends AppCompatActivity implements SearchViewInterface {

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
        SearchPresenter searchPresenter = new SearchPresenter(this);
        searchPresenter.fetchLocation(v,queryString);
    }

    public void setListToAdapter(List<Location> locationList){
        locationAdapter.clearLocationListAdapter();
        locationAdapter.setLocationList(locationList);
    }

    @Override
    public void receiveResults(List<Location> locationList) {
        setListToAdapter(locationList);
    }

    @Override
    public void receiveErrorFromSearch(final View view) {
        Snackbar.make(view,"Error when connecting the network",Snackbar.LENGTH_SHORT)
                .setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        searchQuery(view);
                    }
                }).show();
    }
}
