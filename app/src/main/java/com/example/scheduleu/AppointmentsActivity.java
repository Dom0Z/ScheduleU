package com.example.scheduleu;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AppointmentsActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ArrayAdapter<String> navAdapter;
    private ActionBarDrawerToggle drawerToggle;
    private final ArrayList<Calendar> calendarArrayList = new ArrayList ();
    private RecyclerView recyclerView;
    private CalendarAdapter cAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointment_view);
        calendarArrayList.add(new Calendar("Thursday 4/28", true, false ,true , true, true));
        calendarArrayList.add(new Calendar("Friday 4/29", false, false ,false , true, true));
        calendarArrayList.add(new Calendar("Saturday 4/30", true, true ,true , true, true));
        calendarArrayList.add(new Calendar("Sunday 5/1", false, false ,false , false, true));
        calendarArrayList.add(new Calendar("Monday 5/2", true, true ,true , true, true));
        recyclerView = findViewById(R.id.AppointmentRecycler);
        cAdapter = new CalendarAdapter(calendarArrayList, this);
        recyclerView.setAdapter(cAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setTitle("Appointments");

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
}
