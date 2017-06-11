package com.example.kushansameera.justin.activities;

import android.graphics.Color;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.Toast;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.TransportMode;
import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.util.DirectionConverter;
import com.example.kushansameera.justin.R;
import com.example.kushansameera.justin.adapters.EventsAdapter;
import com.example.kushansameera.justin.models.Events;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class TripActivity extends AppCompatActivity implements OnMapReadyCallback,DirectionCallback {

    private GoogleMap googleMap;

    RecyclerView rcvEvents;
    EventsAdapter adapter;
    List<Events> events = new ArrayList<>();

    private LatLng origin = new LatLng(6.919157, 79.862135);
    private LatLng destination = new LatLng(7.290415, 80.632940);

    private LatLng paintBall = new LatLng(7.251389, 80.338983);
    private LatLng peradeniya = new LatLng(7.252048, 80.509336);
    private LatLng kadugannawa = new LatLng(7.272108, 80.595307);
    private LatLng hotel = new LatLng(7.293222, 80.638409);

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
        requestDirection();
        googleMap.addMarker(new MarkerOptions().position(paintBall).title("Paint Ball"));
        googleMap.addMarker(new MarkerOptions().position(kadugannawa).title("Kadugannawa"));
        googleMap.addMarker(new MarkerOptions().position(peradeniya).title("Peradeniya Garden"));
        googleMap.addMarker(new MarkerOptions().position(hotel).title("Queens Hotel"));
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

    @Override
    public void onDirectionSuccess(Direction direction, String rawBody) {
        if (direction.isOK()) {
            //googleMap.addMarker(new MarkerOptions().position(origin));
            //googleMap.addMarker(new MarkerOptions().position(destination));

            ArrayList<LatLng> directionPositionList = direction.getRouteList().get(0).getLegList().get(0).getDirectionPoint();
            googleMap.addPolyline(DirectionConverter.createPolyline(this, directionPositionList, 5, Color.RED));

        }
    }

    @Override
    public void onDirectionFailure(Throwable t) {
        Toast.makeText(TripActivity.this, "error", Toast.LENGTH_SHORT).show();
    }

    public void requestDirection() {
        GoogleDirection.withServerKey("AIzaSyCyz2tLYMXyM1HQXC_-4u4bT6316F1KmsQ")
                .from(origin)
                .to(destination)
                .transportMode(TransportMode.DRIVING)
                .execute(this);
    }
}
