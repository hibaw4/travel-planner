// C’est la page principale de l’application une fois connecté. Elle affiche
// un résumé rapide des prochains voyages, les itinéraires à venir, et propose des
// raccourcis vers les principales fonctionnalités comme les suggestions, les réservations
// ou la création de voyage.

package com.example.travelplanner.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelplanner.R;
import com.example.travelplanner.adapters.TripAdapter;
import com.example.travelplanner.models.Trip;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    private RecyclerView tripsRecyclerView;
    private TextView emptyView;
    private FloatingActionButton createTripButton;
    private TripAdapter tripAdapter;
    private List<Trip> trips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        initializeViews();
        setupRecyclerView();
        loadTrips();
    }

    private void initializeViews() {
        tripsRecyclerView = findViewById(R.id.tripsRecyclerView);
        emptyView = findViewById(R.id.emptyView);
        createTripButton = findViewById(R.id.createTripButton);

        createTripButton.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, TripCreationActivity.class);
            startActivity(intent);
        });
    }

    private void setupRecyclerView() {
        trips = new ArrayList<>();
        tripAdapter = new TripAdapter(trips, new TripAdapter.OnTripClickListener() {
            @Override
            public void onTripClick(Trip trip) {
                Intent intent = new Intent(DashboardActivity.this, ItineraryActivity.class);
                intent.putExtra("trip", trip);
                startActivity(intent);
            }

            @Override
            public void onEditClick(Trip trip) {
                Intent intent = new Intent(DashboardActivity.this, TripCreationActivity.class);
                intent.putExtra("trip", trip);
                intent.putExtra("editMode", true);
                startActivity(intent);
            }
        });

        tripsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tripsRecyclerView.setAdapter(tripAdapter);
    }

    private void loadTrips() {
        // In a real app, load trips from database or API
        // For now, show mock data if any

        // Mock completed trip
        Trip parisTrip = new Trip();
        parisTrip.setDestination("Paris, France");
        parisTrip.setStartDate("April 21, 2025");
        parisTrip.setEndDate("April 28, 2025");
        parisTrip.setBudget("Medium");
        parisTrip.setTravelers(1);
        trips.add(parisTrip);

        // Mock upcoming trip
        Trip greeceTrip = new Trip();
        greeceTrip.setDestination("Greek Island Hopping");
        greeceTrip.setStartDate("July 8, 2025");
        greeceTrip.setEndDate("July 18, 2025");
        greeceTrip.setBudget("Luxury");
        greeceTrip.setTravelers(2);
        trips.add(greeceTrip);

        updateUI();
    }

    private void updateUI() {
        if (trips.isEmpty()) {
            tripsRecyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            tripsRecyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
            tripAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTrips(); // Reload trips when returning to dashboard
    }
}