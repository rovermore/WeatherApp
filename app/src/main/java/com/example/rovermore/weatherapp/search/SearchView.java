package com.example.rovermore.weatherapp.search;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rovermore.weatherapp.R;
import com.example.rovermore.weatherapp.adapter.LocationAdapter;
import com.example.rovermore.weatherapp.datamodel.location.Location;

import java.util.List;

public class SearchView extends AppCompatActivity implements SearchViewInterface, LocationAdapter.OnViewClicked {

    private EditText editText;
    private Button searchButton;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private LocationAdapter locationAdapter;
    private LocationAdapter.OnViewClicked onViewClicked;
    private SearchPresenterInterface searchPresenterInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchPresenterInterface = new SearchPresenter(getApplicationContext(),this);

        editText = findViewById(R.id.etQuery);
        searchButton = findViewById(R.id.buttonSearch);
        recyclerView = findViewById(R.id.recyclerViewResult);
        layoutManager = new LinearLayoutManager(this);
        locationAdapter = new LocationAdapter(this,null);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(locationAdapter);
        locationAdapter.setOnViewClickedInterface(this);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchQuery();
            }
        });
    }

    private void searchQuery(){
        String queryString = String.valueOf(editText.getText());
        searchPresenterInterface.fetchLocation(queryString);

    }

    private void setListToAdapter(List<Location> locationList){
        locationAdapter.clearLocationListAdapter();
        locationAdapter.setLocationList(locationList);
    }

    @Override
    public void receiveResults(List<Location> locationList) {
        setListToAdapter(locationList);
    }

    @Override
    public void receiveErrorFromSearch(Throwable t) {
        Snackbar.make(searchButton,"Error when connecting the network",Snackbar.LENGTH_SHORT)
                .setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        searchQuery();
                    }
                }).show();
    }

    @Override
    public void onLocationSavedInDatabase(String string) {
        Toast.makeText(getApplicationContext(),string, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void viewClickedFromAdapter(Location location) {
        searchPresenterInterface.saveLocationOnDatabase(location);
    }
}
