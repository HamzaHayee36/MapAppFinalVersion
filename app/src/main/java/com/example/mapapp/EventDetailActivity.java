package com.example.mapapp;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class EventDetailActivity extends AppCompatActivity {

    private boolean isProfileArrowUp = false;
    private boolean isBellArrowUp = false;

    private String[][] data = {
            {"Department", "EventTitle", "Date", "Description"},
            {"Computer\nScience", "Gaming Event", "15-\nApril-\n2023", "Gaming conventions are events where people interested in video games, \n" +
                    "board games, and other types of gaming" +
                    "\n can come together to play and meet other like-minded people"},
            {"Software\nEngineering", "Electronic\nEntertainment", "10-\nApril-\n2023", "Entertainment that someone is able to see or hear using the internet: \n" +
                    "We hope that the expansion of broadband will make \ne-entertainment and e-commerce available to all."},
            {"Computer\nScience", "TTRPG Event", "06-\nApril-\n2023", "Events are the mechanisms for carrying out various processes within a game. \n" +
                    "For example, you can create an Event that assigns an image and text\n" +
                    " to a character which will then appear on the screen when a player talks to that character."},
            {"Software\nEngineering", "Programming\nCompetition", "25-\nMarch-\n2023", "Many platforms conduct coding contests, but Microsoft Imagine cup,\n" +
                    " Google coding competitions,\n" +
                    " Codeforces and TopCoder are few competitions among the best."}
    };

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


        GridLayout gridLayout = findViewById(R.id.gridLayout);

        // Loop through the data and add it to the GridLayout
        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[row].length; col++) {
                TextView textView = new TextView(this);
                textView.setText(data[row][col]);

                // Style the headers
                if (row == 0) {
                    textView.setTextColor(Color.BLACK);
                    textView.setTypeface(null, Typeface.BOLD);
                }

                textView.setPadding(16, 16, 16, 16);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                gridLayout.addView(textView, params);
            }

            // Add a dark grey separator line after every row, except the last one
            if (row < data.length - 1) {
                View separator = new View(this);
                separator.setBackgroundColor(Color.BLACK);
                GridLayout.LayoutParams sepParams = new GridLayout.LayoutParams();
                sepParams.width = GridLayout.LayoutParams.MATCH_PARENT;
                sepParams.height = 2    ; // 2 pixels high separator
                sepParams.columnSpec = GridLayout.spec(0, 4); // Span across all 4 columns
                sepParams.setMargins(0, 18, 0, 8); // Add top and bottom margin to separate the lines from the text
                gridLayout.addView(separator, sepParams);
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
