package com.example.kushansameera.justin.activities;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kushansameera.justin.R;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap googleMap;
    private Button btnSelectLocation;
    private double locLati, locLongi;
    public static String select, locName;
    List<Address> address = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        setTitle("Select Location");
        btnSelectLocation = (Button) findViewById(R.id.btnSelectLocation);
        PlaceAutocompleteFragment placeAutocompleteFragment = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.location_autocomplete_fragment);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        placeAutocompleteFragment.setHint("Search");

        placeAutocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                googleMap.clear();
                LatLng placeLatLng = place.getLatLng();
                locName = place.getName().toString();
                locLati = placeLatLng.latitude;
                locLongi = placeLatLng.longitude;
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(placeLatLng);
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(placeLatLng, 15));
                markerOptions.draggable(true);
                googleMap.addMarker(markerOptions);
            }

            @Override
            public void onError(Status status) {

            }
        });

        btnSelectLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(select=="Start"){
                    PlanActivity.startLatitude = locLati;
                    PlanActivity.startLongitude = locLongi;
                    PlanActivity.startName = locName;
                    select = "";
                    Intent intent = new Intent(MapActivity.this,PlanActivity.class);
                    startActivity(intent);
                }else if(select=="End"){
                    PlanActivity.endLatitude = locLati;
                    PlanActivity.endLongitude = locLongi;
                    PlanActivity.endName = locName;
                    select = "";
                    Intent intent = new Intent(MapActivity.this,PlanActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.googleMap.getUiSettings().setZoomControlsEnabled(true);
        this.googleMap.getUiSettings().setZoomGesturesEnabled(true);
        final Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        LatLng SriLanka = new LatLng(7, 81);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SriLanka, 7));

        this.googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                googleMap.clear();
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                markerOptions.draggable(true);
                googleMap.addMarker(markerOptions);
                locLati = latLng.latitude;
                locLongi = latLng.longitude;
                try {
                    address = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                    locName = address.get(0).getAddressLine(0);
                } catch (IOException e) {

                }

            }
        });

        this.googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.setVisible(false);
                locLati=0;
                locLongi=0;
                locName="";
                return true;
            }
        });
    }
}
