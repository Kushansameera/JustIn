package com.example.kushansameera.justin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

public class TripActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);

        createTabs();

        MapFragment mapFragment = (MapFragment)getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.googleMap.getUiSettings().setZoomControlsEnabled(true);
        this.googleMap.getUiSettings().setZoomGesturesEnabled(true);
        LatLng SriLanka = new LatLng(7, 81);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SriLanka, 7));
    }

    private void createTabs(){
        TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup();

        TabHost.TabSpec spec = host.newTabSpec("Tab One");
        spec.setIndicator("",getResources().getDrawable(R.drawable.event_selector));
        spec.setContent(R.id.events_tab);
        host.addTab(spec);

        spec = host.newTabSpec("Tab Two");
        spec.setIndicator("",getResources().getDrawable(R.drawable.hotel_selector));
        spec.setContent(R.id.hotels_tab);
        host.addTab(spec);

        spec = host.newTabSpec("Tab Three");
        spec.setIndicator("",getResources().getDrawable(R.drawable.transport_selector));
        spec.setContent(R.id.transport_tab);
        host.addTab(spec);

        spec = host.newTabSpec("Tab Four");
        spec.setIndicator("",getResources().getDrawable(R.drawable.guide_selector));
        spec.setContent(R.id.guide_tab);
        host.addTab(spec);
    }
}
