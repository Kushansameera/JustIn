package com.example.kushansameera.justin.activities;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TabHost;

import com.example.kushansameera.justin.R;
import com.example.kushansameera.justin.adapters.EventsAdapter;
import com.example.kushansameera.justin.models.Events;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class TripActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;

    RecyclerView rcvEvents;
    EventsAdapter adapter;
    List<Events> events = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);
        setTitle("My Trip");
        createTabs();

        MapFragment mapFragment = (MapFragment)getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        rcvEvents = (RecyclerView)findViewById(R.id.rcvEvents);
        rcvEvents.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EventsAdapter(this,events);
        rcvEvents.setAdapter(adapter);

        Events e1 = new Events();
        Events e2 = new Events();
        Events e3 = new Events();
        e1.setEventName("Paint Ball");
        e1.setEventTime("9 am - 11 am");
        events.add(e1);
        e2.setEventName("Kadugannawa");
        e2.setEventTime("12 pm - 12.30 pm");
        events.add(e2);
        e3.setEventName("Royal Botanical Gardens, Peradeniya");
        e3.setEventTime("1 pm - 3.30 pm");
        events.add(e3);
        adapter.notifyDataSetChanged();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
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
