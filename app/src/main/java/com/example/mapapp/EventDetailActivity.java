package com.example.mapapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class EventDetailActivity extends AppCompatActivity {

    private boolean isProfileArrowUp = false;
    private boolean isBellArrowUp = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Profile icon toggle functionality
        final ImageView profileArrowIcon = findViewById(R.id.arrow_icon);
        ImageView profileIcon = findViewById(R.id.profile_icon);
        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePopupMenu(v, isProfileArrowUp, profileArrowIcon, R.menu.profile_menu);
                isProfileArrowUp = !isProfileArrowUp;
            }
        });

        // Bell icon toggle functionality
        final ImageView bellArrowIcon = findViewById(R.id.newarrow_icon);
        ImageView bellIcon = findViewById(R.id.bell_icon);
        bellIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePopupMenu(v, isBellArrowUp, bellArrowIcon, R.menu.bell_menu);
                isBellArrowUp = !isBellArrowUp;
            }
        });
    }

    private void togglePopupMenu(View view, boolean isArrowUp, ImageView arrowIcon, int menuRes) {
        if (!isArrowUp) {
            arrowIcon.setImageResource(android.R.drawable.arrow_up_float);
            showPopupMenu(view, menuRes, arrowIcon);
        } else {
            arrowIcon.setImageResource(android.R.drawable.arrow_down_float);
        }
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
