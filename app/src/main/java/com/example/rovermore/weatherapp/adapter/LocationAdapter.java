package com.example.rovermore.weatherapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rovermore.weatherapp.R;
import com.example.rovermore.weatherapp.datamodel.Location;

import java.util.ArrayList;
import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.MyLocationViewHolder> {

    private Context context;
    private List<Location> locationList;

    public LocationAdapter(Context context, List<Location> locationList){
        this.context = context;
        this.locationList = locationList;
    }

    @NonNull
    @Override
    public LocationAdapter.MyLocationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_location,viewGroup,false);
        return new MyLocationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationAdapter.MyLocationViewHolder myLocationViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        if(locationList==null) return 0;
        return locationList.size();
    }

    public class MyLocationViewHolder extends RecyclerView.ViewHolder {

        TextView city;
        TextView country;
        TextView region;

        public MyLocationViewHolder(@NonNull View itemView) {
            super(itemView);

            city = itemView.findViewById(R.id.tv_city_name);
            country = itemView.findViewById(R.id.tv_country_name);
            region = itemView.findViewById(R.id.tv_region_name);

        }
    }

    public void clearLocationListAdapter(){
        if(locationList!=null) {
            locationList.clear();
            locationList = null;
            notifyDataSetChanged();
        }
    }

    public void setLocationList(List<Location> locationList){
        //Creating a new ArrayList to be independent from main activity list
        if(locationList!=null) {
            this.locationList = new ArrayList<>(locationList);
            notifyDataSetChanged();
        }
    }

    public List<Location> getLocationList(){
        return locationList;
    }
}