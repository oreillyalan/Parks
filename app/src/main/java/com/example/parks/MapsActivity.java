package com.example.parks;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

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
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        BottomNavigationView bottomNavigationView =
                findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            Fragment selectedFragment = null;

            int id  = item.getItemId();
            if (id == R.id.maps_nav_button){
                //show the map view
                //Log.d("maps_nav_button","maps_nav_button: CLICKED");
                mMap.clear();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.map, mapFragment).commit();
                mapFragment.getMapAsync(this);
                return true;




            } else if (id == R.id.attractions_nav_button){
                Log.d("attractions_nav_button","attractions_nav_button: CLICKED");
                selectedFragment = AttractionsFragment.newInstance();

            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.map,AttractionsFragment.newInstance()).commit();



            return true;
        });
    }

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