package com.example.parks;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.parks.adapter.AttractionRecyclerViewAdapter;
import com.example.parks.data.Repository;
import com.example.parks.models.Attraction;

import java.util.List;

public class AttractionsFragment extends Fragment {
    private RecyclerView recyclerView;
    private AttractionRecyclerViewAdapter attractionRecyclerViewAdapter;
    private List<Attraction> attractionList;

    public AttractionsFragment() {

    }

    public static AttractionsFragment newInstance() {
        AttractionsFragment fragment = new AttractionsFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_attractions, container, false);
        recyclerView = view.findViewById(R.id.attraction_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Repository.getAttractions(attractions -> {
            attractionRecyclerViewAdapter = new AttractionRecyclerViewAdapter(attractions);
            recyclerView.setAdapter(attractionRecyclerViewAdapter);
        });

    }
}