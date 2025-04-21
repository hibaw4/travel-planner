// Permet à l’utilisateur de planifier un nouveau voyage en ajoutant des informations
// telles que le nom du voyage, la destination, les dates, et les participants.
// Une fois terminé, le voyage est sauvegardé dans la base de données pour future consultation.

package com.example.travelplanner.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travelplanner.R;
import com.example.travelplanner.models.Trip;

public class TripCreationActivity extends AppCompatActivity {

    private Dialog planningDialog;
    private EditText destinationInput;
    private DatePicker startDatePicker, endDatePicker;
    private EditText travelersInput;
    private Spinner budgetSpinner;
    private RadioGroup planningTypeGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_creation);

        // Initialize views
        Button planTripButton = findViewById(R.id.planTripButton);
        planTripButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPlanningDialog();
            }
        });
    }

    private void showPlanningDialog() {
        planningDialog = new Dialog(this);
        planningDialog.setContentView(R.layout.dialog_trip_planning);
        planningDialog.getWindow().setLayout(
                android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT
        );

        // Initialize dialog views
        destinationInput = planningDialog.findViewById(R.id.destinationInput);
        startDatePicker = planningDialog.findViewById(R.id.startDatePicker);
        endDatePicker = planningDialog.findViewById(R.id.endDatePicker);
        travelersInput = planningDialog.findViewById(R.id.travelersInput);
        budgetSpinner = planningDialog.findViewById(R.id.budgetSpinner);
        planningTypeGroup = planningDialog.findViewById(R.id.planningTypeGroup);

        Button continueButton = planningDialog.findViewById(R.id.continueButton);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processTripDetails();
            }
        });

        ImageView closeButton = planningDialog.findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                planningDialog.dismiss();
            }
        });

        planningDialog.show();
    }

    private void processTripDetails() {
        String destination = destinationInput.getText().toString();
        String travelers = travelersInput.getText().toString();
        String budget = budgetSpinner.getSelectedItem().toString();

        if (destination.isEmpty() || travelers.isEmpty()) {
            Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create Trip object (implement Trip model class)
        Trip trip = new Trip();
        trip.setDestination(destination);
        trip.setTravelers(Integer.parseInt(travelers));
        trip.setBudget(budget);

        // Navigate to flight booking
        Intent intent = new Intent(this, ReservationActivity.class);
        intent.putExtra("trip", trip);
        startActivity(intent);
        planningDialog.dismiss();
    }
}