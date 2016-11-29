package com.egco428.mythailandmap;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.text.DecimalFormat;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    TextView lat;
    TextView lon;
    Button randBtn;
    private String getLat;
    private String getLon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        lat = (TextView)findViewById(R.id.lat);
        lon = (TextView)findViewById(R.id.lon);
        randBtn = (Button)findViewById(R.id.randBtn);
        getLat = lat.getText().toString();
        getLon = lon.getText().toString();

        randBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                randLatLon();
            }
        });


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

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    protected void randLatLon() {
        double minLat = -85.000000;
        double maxLat = 85.000000;
        double latitude = minLat + (double) (Math.random() * ((maxLat - minLat) + 1));
        double minLon = -179.999989;
        double maxLon = 179.999989;
        double longitude = minLon + (double) (Math.random() * ((maxLon - minLon) + 1));
        DecimalFormat df = new DecimalFormat("#.######"); //Convert 0.000000.. to 0.000000
        lat.setText(df.format(latitude));
        lon.setText(df.format(longitude));
    }

    Polyline line = mMap.addPolyline(new PolylineOptions()
            .add(new LatLng(15.55234, 100), new LatLng(15.4231,100.45612), new LatLng(15, 100.45612),
                    new LatLng(13.7934, 100.3225), new LatLng(15.1123, 100.05612), new LatLng(15.55234, 100))
            .width(5)
            .color(Color.RED));

}
