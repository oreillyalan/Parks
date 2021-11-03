package com.example.parks.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parks.R;
import com.example.parks.models.Attraction;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AttractionRecyclerViewAdapter extends RecyclerView.Adapter<AttractionRecyclerViewAdapter.ViewHolder> {


    private final List<Attraction> attractionList;

    public AttractionRecyclerViewAdapter(List<Attraction> attractionList) {
        this.attractionList = attractionList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.attraction_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Attraction attraction = attractionList.get(position);
        holder.attractionName.setText(attraction.getFullName());
        holder.attractionType.setText(attraction.getType().toString());
        holder.attractionCounty.setText(attraction.getCounty());
        if(attraction.getImage()!=""){
            Picasso.get().load(attraction.getImage())
                    .placeholder(android.R.drawable.stat_sys_download)
                    .error(android.R.drawable.stat_notify_error)
                    .resize(100,100)
                    .centerCrop()
                    .into(holder.attractionImage);
        }



    }

    @Override
    public int getItemCount() {
        return attractionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView attractionImage;
        public TextView attractionName;
        public TextView attractionType;
        public TextView attractionCounty;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            attractionImage = itemView.findViewById(R.id.row_attraction_imageView);
            attractionName = itemView.findViewById(R.id.row_attraction_name_textview);
            attractionType = itemView.findViewById(R.id.row_attraction_type_textview);
            attractionCounty = itemView.findViewById(R.id.row_attraction_county_textview);
        }
    }
}
