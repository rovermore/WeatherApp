package com.example.rovermore.weatherapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rovermore.weatherapp.R;
import com.example.rovermore.weatherapp.datamodel.location.Location;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private Context context;
    private List<Location> locationList;
    private OnViewClicked onViewClicked;

    public interface OnViewClicked {
        void viewClickedFromAdapter(String locationName, String locationKey);
    }

    public MainAdapter(Context context, List<Location> locationList, OnViewClicked onViewClicked){
        this.context = context;
        this.locationList = locationList;
        this.onViewClicked = onViewClicked;
    }

    @NonNull
    @Override
    public MainAdapter.MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
            .inflate(R.layout.list_item_main_location,viewGroup,false);
        return new MainViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.MainViewHolder mainViewHolder, int i) {
        Location location = locationList.get(i);

        mainViewHolder.city.setText(location.getEnglishName());
    }

    @Override
    public int getItemCount() {
        if(locationList == null) {
            return 0;
        } else {
            return locationList.size();
        }
    }


     class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView city;

         public MainViewHolder(@NonNull View itemView) {
             super(itemView);

             city = (TextView) itemView.findViewById(R.id.tv_main_city_name);
             itemView.setOnClickListener(this);
         }

         @Override
         public void onClick(View v) {
             int position = getAdapterPosition();
             Location location = locationList.get(position);
             String locationName = location.getLocalizedName();
             String locationKey = location.getKeyLocation();
             onViewClicked.viewClickedFromAdapter(locationName, locationKey);
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
        if(locationList!=null) {
            this.locationList = locationList;
            notifyDataSetChanged();
        }
    }

    public List<Location> getLocationList(){
        return locationList;
    }
}
