package com.example.mapapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;

public class Profile extends AppCompatActivity implements OnMapReadyCallback {

    private DrawerLayout drawerLayout;
    private boolean isProfileArrowUp = false;
    private boolean isBellArrowUp = false;
    private GoogleMap mMap;
    private TextView navUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navUsername = navigationView.getHeaderView(0).findViewById(R.id.nav_header_username);
        String username = getIntent().getStringExtra("username");
        if(username != null && !username.isEmpty()) {
            navUsername.setText(username);
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.StaffDet:
                        Intent intent = new Intent(Profile.this, StaffDetailsActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_settings:
                        Intent settingsIntent = new Intent(Profile.this, NotificationActivity.class);
                        startActivity(settingsIntent);
                        break;
                    case R.id.Req:
                        Intent request = new Intent(Profile.this,StudentRequestActivity.class);
                        startActivity(request);
                        break;
                    case R.id.Attend:
                        Intent Attendance = new Intent(Profile.this,AttendanceActivity.class);
                        startActivity(Attendance);
                        break;
                    case R.id.Event:
                        Intent Event = new Intent(Profile.this,EventDetailActivity.class);
                        startActivity(Event);
                        break;
                    default:
                        drawerLayout.closeDrawers();
                        break;
                }
                return true;
            }
        });

        final ImageView profileArrowIcon = findViewById(R.id.arrow_icon);
        ImageView profileIcon = findViewById(R.id.profile_icon);
        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePopupMenu(v, isProfileArrowUp, profileArrowIcon, R.menu.profile_menu);
                isProfileArrowUp = !isProfileArrowUp;
            }
        });

        final ImageView bellArrowIcon = findViewById(R.id.newarrow_icon);
        ImageView bellIcon = findViewById(R.id.bell_icon);
        bellIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePopupMenu(v, isBellArrowUp, bellArrowIcon, R.menu.bell_menu);
                isBellArrowUp = !isBellArrowUp;
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    private void togglePopupMenu(View view, boolean isArrowUp, ImageView arrowIcon, int menuRes) {
        if (!isArrowUp) {
            arrowIcon.setImageResource(android.R.drawable.arrow_up_float);
            showPopupMenu(view, menuRes, arrowIcon);
        } else {
            arrowIcon.setImageResource(android.R.drawable.arrow_down_float);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);

        LatLng hctCollegeSharjah = new LatLng(25.285267197405954, 55.46964840806604);
        mMap.addMarker(new MarkerOptions().position(hctCollegeSharjah).title("HCT College Sharjah"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hctCollegeSharjah, 18));
    }

    private void showPopupMenu(View view, int menuRes, final ImageView arrowIcon) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(menuRes, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return true;
            }
        });

        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
                arrowIcon.setImageResource(android.R.drawable.arrow_down_float);
            }
        });

        popupMenu.show();
    }
}
