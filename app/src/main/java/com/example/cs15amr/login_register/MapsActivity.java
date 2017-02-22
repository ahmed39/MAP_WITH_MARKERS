package com.example.cs15amr.login_register;


import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.ZoomControls;
import android.widget.Button;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private GoogleMap mMap;
    private final static int MY_PERMISSION_FINE_LOCATION = 101;
    ZoomControls zoom;
    Button markBt;
    Double myLatitude = null;
    Double myLongitude = null;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    protected static final String TAG = "MapsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        locationRequest = new LocationRequest();
        locationRequest.setInterval(15 * 1000);
        locationRequest.setFastestInterval(5 * 1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        zoom = (ZoomControls) findViewById(R.id.zcZoom);
        zoom.setOnZoomOutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.animateCamera(CameraUpdateFactory.zoomOut());
            }
        });
        zoom.setOnZoomInClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.animateCamera(CameraUpdateFactory.zoomIn());

            }
        });
        markBt = (Button) findViewById(R.id.btMark);
        markBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LatLng myLocation = new LatLng(myLatitude, myLongitude);
                mMap.addMarker(new MarkerOptions().position(myLocation).title("My Location"));


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


        PolygonOptions rectOptions = new PolygonOptions()
                .add(new LatLng(51.534730, -0.471630),
                        new LatLng(51.533237, -0.471702),
                        new LatLng(51.533090, -0.469846),
                        new LatLng(51.531775, -0.469825),
                        new LatLng(51.532996, -0.467465),
                        new LatLng(51.534758, -0.468677),
                        new LatLng(51.534758, -0.468677))
                                .strokeColor(Color.BLUE);


// Get back the mutable Polygon
        Polygon polygon = mMap.addPolygon(rectOptions);
        //Markers for zone E
        Marker Eastern = mMap.addMarker(new MarkerOptions().position(new LatLng(51.533591, -0.468423)).title("Eastern Gateway").snippet("Houses The Business School"));
        Marker SportsCentre = mMap.addMarker(new MarkerOptions().position(new LatLng(51.533303, -0.469959)).title("Sports Centre").snippet("State of Th Art Facilities Including fully Equipped Gym"));
        Marker Lancaster = mMap.addMarker(new MarkerOptions().position(new LatLng(51.534345, -0.471376)).title("Lancaster Complex").snippet("Student Halls"));
        Marker MarySeacole = mMap.addMarker(new MarkerOptions().position(new LatLng(51.532825, -0.468563)).title("Mary Seacole").snippet("The Building Is Home To Students and Staff From The College Of Health and Life Sciences."));
        Marker BrunelSports = mMap.addMarker(new MarkerOptions().position(new LatLng(51.532383, -0.469628)).title("Brunel Indoor Athletic Centre").snippet("Brunel University Sport's state-of-the-art Facilities Are Open To Students, Staff and Members Of The Community."));
        Marker St_Johns = mMap.addMarker(new MarkerOptions().position(new LatLng(51.534496, -0.469319)).title("St Johns").snippet("Computer Science Centre"));

        //Markers for zone G
        Marker Russel = mMap.addMarker(new MarkerOptions().position(new LatLng(51.531908, -0.468371)).title("Russell Building").snippet(""));
        Marker Elliot = mMap.addMarker(new MarkerOptions().position(new LatLng(51.531868, -0.467499)).title("Elliot Jaques").snippet(""));
        Marker Gardiner = mMap.addMarker(new MarkerOptions().position(new LatLng(51.531470, -0.468052)).title("Gardiner").snippet(""));
        Marker Metal = mMap.addMarker(new MarkerOptions().position(new LatLng(51.531448, -0.466968)).title("Advanced Metal Casting Centre").snippet(""));



        // Add a marker in Brunel and move the camera
        LatLng brunel = new LatLng(51.5332, -0.4692);
       // mMap.addMarker(new MarkerOptions().position(brunel).title("Marker in Brunel"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(brunel, 18));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            mMap.setMyLocationEnabled(true);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_FINE_LOCATION);

            }

        }

    }

    public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSION_FINE_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        mMap.setMyLocationEnabled(true);
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "This app requires locations permissions to be granted", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
        }
    }

    public void onClick(View v){

        Intent j = new Intent(this, SettingsActivity.class);
        startActivity(j);

    }
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        requestLocationUpdates();
    }

    private void requestLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "connection suspended");

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i(TAG, "Connection Failed: ConnectionResult.getErrorCode() = " + connectionResult.getErrorCode());

    }

    @Override
    public void onLocationChanged(Location location) {
        myLatitude = location.getLatitude();
        myLongitude = location.getLongitude();

    }

    protected void onStart() {
        super.onStart();
    }
    protected void onPause(){
        super.onPause();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
    }
    protected void onResume() {
        super.onResume();
        if (googleApiClient.isConnected()) {
            requestLocationUpdates();
        }
    }
    protected void onStop() {
        super.onStop();
        googleApiClient.disconnect();
    }



}
