package com.example.parks;

import androidx.fragment.app.FragmentActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

import com.example.parks.data.AsyncResponse;
import com.example.parks.data.Repository;
import com.example.parks.models.Attraction;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.parks.databinding.ActivityMapsBinding;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng ireland = new LatLng(53.14337, -7.69193);
        mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(ireland, 6.0f) );

        Repository.getAttractions(attractions -> {
            for (Attraction attraction : attractions){
                LatLng attractionGeoLocation = new LatLng(attraction.getLatitude(), attraction.getLongitude());
                mMap.addMarker(new MarkerOptions().position(attractionGeoLocation).title(attraction.getFullName()));
                //Log.d("Attracty","onMapReady: "+ attraction.toString());
            }
            mMap.getUiSettings().setZoomControlsEnabled(true);

        });


    }
}