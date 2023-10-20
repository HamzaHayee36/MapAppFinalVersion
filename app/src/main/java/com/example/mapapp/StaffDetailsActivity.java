package com.example.mapapp;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

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

        // Initialize the RecyclerView with custom column width.
        setupRecyclerView();

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
    }

//    private void setupRecyclerView() {
//        RecyclerView recyclerView = findViewById(R.id.recyclerView);
//
//        List<String> dataList = new ArrayList<>();
//        dataList.add("Name");
//        dataList.add("Mobile");
//        dataList.add("Department");
//        dataList.add("Education");
//        // ... add more data if needed ...
//
//        GridLayoutManager layoutManager = new GridLayoutManager(this, 6); // 6 spans
//
//        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                switch (position % 4) {
//                    case 0: // First column
//                    case 1: // Second column
//                        return 1;
//                    case 3: // Fourth column
//                    case 2: // Third column (Department)
//                        return 2; // Takes up 2 spans
//                    default:
//                        return 1; // Default
//                }
//            }
//        });
//
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(new CustomAdapter(dataList));
//    }
        private void setupRecyclerView() {
            RecyclerView recyclerView = findViewById(R.id.recyclerView);

            List<String> dataList = new ArrayList<>();
            // Header row
            dataList.add("Name");
            dataList.add("Mobile");
            dataList.add("Department");
            dataList.add("Education");

            // Ali Raza
            dataList.add("Ali Raza");
            dataList.add("555 474 28374");
            dataList.add("CS");
            dataList.add("Mphil");

            // Hassan
            dataList.add("Hassan");
            dataList.add("555 474 46782");
            dataList.add("SE");
            dataList.add("MSCS");

            // Amna
            dataList.add("Amna");
            dataList.add("555 474 94782");
            dataList.add("CS");
            dataList.add("Mphil");

            // Sadia
            dataList.add("Sadia");
            dataList.add("555 474 94782");
            dataList.add("CS");
            dataList.add("MSCS");

            // Hussain
            dataList.add("Hussain");
            dataList.add("555 474 94782");
            dataList.add("CS");
            dataList.add("Mphil");

            GridLayoutManager layoutManager = new GridLayoutManager(this, 6); // 4 spans since you have 4 columns

            layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    switch (position % 4) {
                        case 2: // Third column (Department)
                        case 3: // Fourth column (Education)
                            return 2;
                        case 0: // Name
                        case 1: // Mobile
                            return 1; // Takes up 1 span
                        default:
                            return 1; // Default
                    }
                }
            });

            recyclerView.setLayoutManager(layoutManager);
            int[] spacings = {-30, 0, 10, 10};  // Example spacings
            recyclerView.addItemDecoration(new CustomItemDecoration(spacings));
            recyclerView.setAdapter(new CustomAdapter(dataList));
    }
    public class CustomItemDecoration extends RecyclerView.ItemDecoration {
        private int[] spacings;

        public CustomItemDecoration(int[] spacings) {
            this.spacings = spacings;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            int column = position % 4;  // Assuming you have 4 columns

            outRect.right = spacings[column];
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
