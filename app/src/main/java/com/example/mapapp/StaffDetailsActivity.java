package com.example.mapapp;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StaffDetailsActivity extends AppCompatActivity {

    private boolean isProfileArrowUp = false;
    private boolean isBellArrowUp = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        // Profile icon toggle
        final ImageView profileArrowIcon = findViewById(R.id.arrow_icon);
        ImageView profileIcon = findViewById(R.id.profile_icon);
        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePopupMenu(v, isProfileArrowUp, profileArrowIcon, R.menu.profile_menu);
                isProfileArrowUp = !isProfileArrowUp;
            }
        });

        // Bell icon toggle
        final ImageView bellArrowIcon = findViewById(R.id.newarrow_icon);
        ImageView bellIcon = findViewById(R.id.bell_icon);
        bellIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePopupMenu(v, isBellArrowUp, bellArrowIcon, R.menu.bell_menu);
                isBellArrowUp = !isBellArrowUp;
            }
        });


        GridLayout gridLayout = findViewById(R.id.gridLayout);

        String[][] data = {
                {"Name", "Mobile", "Department", "Education"},
                {"Ali Raza", "555\n474\n28374", "CS", "Mphil"},
                {"Hassan", "555\n474\n46782", "SE", "MSCS"},
                {"Amna", "555\n474\n94782", "CS", "Mphil"},
                {"Sadia", "555\n474\n94782", "CS", "MSCS"},
                {"Hussain", "555\n474\n94782", "CS", "Mphil"}
        };

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                TextView textView = new TextView(this);
                textView.setText(data[i][j]);
                textView.setPadding(8, 8, 8, 8);
                textView.setGravity(Gravity.CENTER);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.setGravity(Gravity.FILL_HORIZONTAL);
                params.bottomMargin = 16; // Added vertical margin between rows
                textView.setLayoutParams(params);
                gridLayout.addView(textView);
            }
            // Add a dark grey separator line after every row, except the last one
            if (i < data.length - 1) {
                View separator = new View(this);
                separator.setBackgroundColor(Color.DKGRAY);
                GridLayout.LayoutParams sepParams = new GridLayout.LayoutParams();
                sepParams.width = GridLayout.LayoutParams.MATCH_PARENT;
                sepParams.height = 1; // 1 pixel high
                sepParams.columnSpec = GridLayout.spec(0, 4); // Span across all 4 columns
                sepParams.bottomMargin = 16; // Added vertical margin after the separator
                separator.setLayoutParams(sepParams);
                gridLayout.addView(separator);
            }
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
