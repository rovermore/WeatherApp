package com.example.rovermore.weatherapp.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.rovermore.weatherapp.R;
import com.example.rovermore.weatherapp.adapter.MainAdapter;
import com.example.rovermore.weatherapp.datamodel.location.Location;
import com.example.rovermore.weatherapp.detail.DetailView;
import com.example.rovermore.weatherapp.search.SearchView;
import com.example.rovermore.weatherapp.viewmodel.MainViewModel;

import java.util.List;

public class MainView extends AppCompatActivity implements MainAdapter.OnViewClicked {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MainAdapter locationAdapter;

    public static final String LOCATION_NAME = "location_name";
    public static final String LOCATION_KEY= "location_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.mainRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        locationAdapter = new MainAdapter(this,null, this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(locationAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),SearchView.class);
                startActivity(intent);
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        setUpMainViewModel();
    }

    private void setUpMainViewModel(){
        MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getLocations().observe(this, new Observer<List<Location>>() {
            @Override
            public void onChanged(@Nullable List<Location> locationList) {
                if(locationList!=null && !locationList.isEmpty()){
                    locationAdapter.clearLocationListAdapter();
                    locationAdapter.setLocationList(locationList);
                } else {
                    //send a mesage to toast in mainview
                    String emptyDatabaseString = "No Locations found in Favourites";
                    Toast.makeText(getBaseContext(), emptyDatabaseString, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void viewClickedFromAdapter(String locationName, String locationKey) {
        Intent intent = new Intent(this,DetailView.class);
        intent.putExtra(LOCATION_NAME,locationName);
        intent.putExtra(LOCATION_KEY, locationKey);
        startActivity(intent);
    }
}
