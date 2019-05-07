package com.example.rovermore.weatherapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rovermore.weatherapp.R;
import com.example.rovermore.weatherapp.datamodel.location.Country;
import com.example.rovermore.weatherapp.datamodel.location.Location;
import com.example.rovermore.weatherapp.datamodel.location.Region;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.MyLocationViewHolder> {

    private Context context;
    private List<Location> locationList;
    private OnViewClicked onViewClicked;

    public LocationAdapter(Context context, List<Location> locationList){
        this.context = context;
        this.locationList = locationList;
    }

    public interface OnViewClicked {
        void passClicked (Location location);
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
        Location location = locationList.get(i);

        myLocationViewHolder.city.setText(location.getEnglishName());
        Country country = location.getCountry();
        myLocationViewHolder.country.setText((CharSequence) country.getEnglishName() );
        Region region = location.getRegion();
        myLocationViewHolder.region.setText((CharSequence) region.getEnglishName());
    }

    @Override
    public int getItemCount() {
        if(locationList==null) return 0;
        return locationList.size();
    }

    class MyLocationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView city;
        TextView country;
        TextView region;

        public MyLocationViewHolder(@NonNull View itemView) {
            super(itemView);

            city = itemView.findViewById(R.id.tv_city_name);
            country = itemView.findViewById(R.id.tv_country_name);
            region = itemView.findViewById(R.id.tv_region_name);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Location location = locationList.get(position);
            onViewClicked.passClicked(location);
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

    public void setOnViewClickedInterface(OnViewClicked onViewClicked){
        this.onViewClicked = onViewClicked;
    }
}
