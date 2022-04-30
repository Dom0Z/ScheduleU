package com.example.scheduleu;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    
    public static final boolean ADMIN_MODE = true;

    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ArrayAdapter<String> navAdapter;
    private ActionBarDrawerToggle drawerToggle;
    private Button addEventButton;
    private TextView event4;

    private ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("ScheduleU");
        
        if (ADMIN_MODE) {
            addEventButton = findViewById(R.id.buttonAddEvent);
            addEventButton.setVisibility(View.VISIBLE);
            addEventButton.setOnClickListener(this);
            event4 = findViewById(R.id.event4);
        }

        drawerLayout = findViewById(R.id.drawerLayout);
        drawerList = findViewById(R.id.navList);

        navAdapter = new ArrayAdapter<>(this, R.layout.nav_drawer_item, new ArrayList<String>());
        drawerList.setAdapter(navAdapter);
        drawerList.setOnItemClickListener(
                (parent, view, position, id) -> {
                        selectDrawerItem(position);
                        setTitle(navAdapter.getItem(position));
                });

        drawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        );

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), this::handleResult);

        navAdapter.add("Events");
        navAdapter.add("Appointments");
        navAdapter.add("Public Safety");
    }

    private void selectDrawerItem(int position) {
        // change activity
        Log.d(TAG, "selectDrawerItem: Selected drawer item " + position);

        if (position == 1) {
            Intent intent = new Intent(this, AppointmentsActivity.class);
            startActivity(intent);
        }
        else if (position == 2) {
            Intent intent = new Intent(this, PublicSafetyActivity.class);
            startActivity(intent);
        }

        drawerLayout.closeDrawer(drawerList);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Check drawer first!
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onClick(View view) {
        if (view == addEventButton) {
            Log.d(TAG, "onClick: add event");

            Intent intent = new Intent(this, AddEventActivity.class);
            activityResultLauncher.launch(intent);
        }
    }

    private void handleResult(ActivityResult result) {
        if (result.getResultCode() == RESULT_OK) {
            String title = result.getData().getStringExtra("Title");
            String body = result.getData().getStringExtra("Body");
            String date = result.getData().getStringExtra("Date");
            String startTime = result.getData().getStringExtra("StartTime");
            String endTime = result.getData().getStringExtra("EndTime");
            String maxRSVP = result.getData().getStringExtra("MaxRSVP");

            event4.setText(title + "\n" + date + " " + startTime + " - " + endTime + "\nCurrent RSVP: 0\nMax RSVP: " + maxRSVP);
            event4.setVisibility(View.VISIBLE);
        }
    }
}