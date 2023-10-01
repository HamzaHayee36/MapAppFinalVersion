package com.example.mapapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class NotificationActivity extends AppCompatActivity {

    // Flags to track the state of the arrows for profile and bell icons
    private boolean isProfileArrowUp = false;
    private boolean isBellArrowUp = false;

    // TextViews for the extracted data
    private TextView studentDetailsTitle;
    private TextView studentNameLabel;
    private TextView studentNameTextView;
    private TextView studentRollLabel;
    private TextView studentRollTextView;
    private TextView studentEmailLabel;
    private TextView studentEmailTextView;
    private TextView studentMessage;
    private TextView studentMessagetextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        // Set up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Handle click for profile icon
        final ImageView profileArrowIcon = findViewById(R.id.arrow_icon);
        ImageView profileIcon = findViewById(R.id.profile_icon);
        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePopupMenu(v, isProfileArrowUp, profileArrowIcon, R.menu.profile_menu);
                isProfileArrowUp = !isProfileArrowUp;
            }
        });

        // Handle click for bell icon
        final ImageView bellArrowIcon = findViewById(R.id.newarrow_icon);
        ImageView bellIcon = findViewById(R.id.bell_icon);
        bellIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePopupMenu(v, isBellArrowUp, bellArrowIcon, R.menu.bell_menu);
                isBellArrowUp = !isBellArrowUp;
            }
        });

        // Initialize the TextViews for extracted data
        studentDetailsTitle = findViewById(R.id.studentDetailsTitle);
        studentNameLabel = findViewById(R.id.studentNameLabel);
        studentNameTextView = findViewById(R.id.studentNameTextView);
        studentRollLabel = findViewById(R.id.studentRollLabel);
        studentRollTextView = findViewById(R.id.studentRollTextView);
        studentEmailLabel = findViewById(R.id.studentEmailLabel);
        studentEmailTextView = findViewById(R.id.studentEmailTextView);
        studentMessage = findViewById(R.id.studentMessage);
        studentMessagetextview = findViewById(R.id.studentMessagetextview);

        // Set the extracted text to the TextViews
        studentDetailsTitle.setText("Notification");
        studentNameLabel.setText("Number");
        studentNameTextView.setText("ION 67");
        studentRollLabel.setText("Date");
        studentRollTextView.setText("4/15/2023 12:00:00 AM");
        studentEmailLabel.setText("Subject");
        studentEmailTextView.setText("Eid Holidays");
        studentMessage.setText("Message");
        studentMessagetextview.setText("Ministry of Interior issued a notification.");
    }

    // Method to toggle popup menu and handle arrow behavior
    private void togglePopupMenu(View view, boolean isArrowUp, ImageView arrowIcon, int menuRes) {
        if (!isArrowUp) {
            arrowIcon.setImageResource(android.R.drawable.arrow_up_float);
            showPopupMenu(view, menuRes, arrowIcon);
        } else {
            arrowIcon.setImageResource(android.R.drawable.arrow_down_float);
        }
    }

    // Function to show popup menu
    private void showPopupMenu(View view, int menuRes, final ImageView arrowIcon) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(menuRes, popupMenu.getMenu());

        // Handle menu item clicks
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Handle menu item selections
                return true;
            }
        });

        // Handle dismissal of the popup menu
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
                arrowIcon.setImageResource(android.R.drawable.arrow_down_float);
            }
        });

        popupMenu.show();
    }

    // This method ensures that the back arrow in the toolbar works correctly
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
