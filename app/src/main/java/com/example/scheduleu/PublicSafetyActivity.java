package com.example.scheduleu;

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

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PublicSafetyActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "PublicSafetyActivity";

    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ArrayAdapter<String> navAdapter;
    private ActionBarDrawerToggle drawerToggle;
    private Button addAlertButton;
    private TextView alert4;

    private ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicsafety);

        setTitle("Public Safety");

        if (MainActivity.ADMIN_MODE) {
            addAlertButton = findViewById(R.id.buttonAddAlert);
            addAlertButton.setVisibility(View.VISIBLE);
            addAlertButton.setOnClickListener(this);
            alert4 = findViewById(R.id.alert4);
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
        // Change activity

        if (position == 0) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else if (position == 1) {
            Intent intent = new Intent(this, AppointmentsActivity.class);
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
        if (view == addAlertButton) {
            Log.d(TAG, "onClick: add alert");

            Intent intent = new Intent(this, AddAlertActivity.class);
            activityResultLauncher.launch(intent);
        }
    }

    private void handleResult(ActivityResult result) {
        if (result.getResultCode() == RESULT_OK) {
            String title = result.getData().getStringExtra("Title");
            String body = result.getData().getStringExtra("Body");
            String location = result.getData().getStringExtra("Location");

            Date currentTime = Calendar.getInstance().getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
            String dateStr = simpleDateFormat.format(currentTime);

            alert4.setText(title + "\nLocation: " + location + "\nReported: " + dateStr);
            alert4.setVisibility(View.VISIBLE);
        }
    }
}
