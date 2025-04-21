// Affiche l’itinéraire détaillé du voyage sous forme de calendrier ou de liste.
// L’utilisateur peut y ajouter, modifier ou supprimer des étapes, consulter des cartes
// ou des lieux à visiter, même en mode hors ligne.

package com.example.travelplanner.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelplanner.R;
import com.example.travelplanner.adapters.ItineraryAdapter;
import com.example.travelplanner.models.ItineraryItem;
import com.example.travelplanner.models.Trip;

import java.util.ArrayList;
import java.util.List;

public class ItineraryActivity extends AppCompatActivity {

    private Trip trip;
    private RecyclerView itineraryRecyclerView;
    private TextView destinationText, dateRangeText, weatherForecastText;
    private LinearLayout weatherLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary);

        trip = getIntent().getParcelableExtra("trip");

        initializeViews();
        setupItinerary();

        // Show success dialog for new trips
        boolean isNewTrip = getIntent().getBooleanExtra("isNewTrip", false);
        if (isNewTrip) {
            showSuccessDialog();
        }
    }

    private void initializeViews() {
        destinationText = findViewById(R.id.destinationText);
        dateRangeText = findViewById(R.id.dateRangeText);
        weatherForecastText = findViewById(R.id.weatherForecastText);
        weatherLayout = findViewById(R.id.weatherLayout);
        itineraryRecyclerView = findViewById(R.id.itineraryRecyclerView);

        Button editButton = findViewById(R.id.editButton);
        editButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, TripCreationActivity.class);
            intent.putExtra("trip", trip);
            intent.putExtra("editMode", true);
            startActivity(intent);
        });

        Button shareButton = findViewById(R.id.shareButton);
        shareButton.setOnClickListener(v -> shareItinerary());

        Button downloadButton = findViewById(R.id.downloadButton);
        downloadButton.setOnClickListener(v -> downloadItinerary());

        Button printButton = findViewById(R.id.printButton);
        printButton.setOnClickListener(v -> printItinerary());

        itineraryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupItinerary() {
        if (trip != null) {
            destinationText.setText(trip.getDestination());
            dateRangeText.setText(trip.getStartDate() + " - " + trip.getEndDate());

            // Mock weather data
            setupWeatherForecast();

            // Mock itinerary items
            List<ItineraryItem> items = generateSampleItinerary();
            ItineraryAdapter adapter = new ItineraryAdapter(items);
            itineraryRecyclerView.setAdapter(adapter);
        }
    }

    private void setupWeatherForecast() {
        // Mock weather data
        weatherLayout.removeAllViews();

        addWeatherDay("May 15", "22°C", "Partly Cloudy");
        addWeatherDay("May 16", "24°C", "Sunny");
        addWeatherDay("May 17", "20°C", "Light Rain");
    }

    private void addWeatherDay(String date, String temp, String condition) {
        LinearLayout dayLayout = new LinearLayout(this);
        dayLayout.setOrientation(LinearLayout.HORIZONTAL);
        dayLayout.setPadding(8, 8, 8, 8);

        TextView dateText = new TextView(this);
        dateText.setText(date);
        dateText.setTextSize(16);
        dateText.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));

        TextView tempText = new TextView(this);
        tempText.setText(temp);
        tempText.setTextSize(16);
        tempText.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));

        TextView conditionText = new TextView(this);
        conditionText.setText(condition);
        conditionText.setTextSize(16);
        conditionText.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));

        dayLayout.addView(dateText);
        dayLayout.addView(tempText);
        dayLayout.addView(conditionText);

        weatherLayout.addView(dayLayout);
    }

    private List<ItineraryItem> generateSampleItinerary() {
        List<ItineraryItem> items = new ArrayList<>();

        // Day 1
        items.add(new ItineraryItem("Day 1: Arrival & Eiffel Tower", true));
        items.add(new ItineraryItem("10:00", "Arrive at Charles de Gaulle Airport",
                "Take the RER B train to the city center", false));
        items.add(new ItineraryItem("13:00", "Check-in at Hotel",
                "Drop off luggage and freshen up", false));
        items.add(new ItineraryItem("15:00", "Visit Eiffel Tower",
                "Enjoy panoramic views of Paris from the iconic landmark", false));
        items.add(new ItineraryItem("19:00", "Dinner at Bistro",
                "Authentic French cuisine in a charming setting", false));

        // Day 2
        items.add(new ItineraryItem("Day 2: Louvre & Notre-Dame", true));
        items.add(new ItineraryItem("09:00", "Breakfast at Local Café",
                "Enjoy croissants and coffee", false));
        items.add(new ItineraryItem("10:00", "Visit the Louvre Museum",
                "See the Mona Lisa and other masterpieces", false));
        items.add(new ItineraryItem("14:00", "Lunch at Latin Quarter",
                "Explore the historic district", false));
        items.add(new ItineraryItem("16:00", "Notre-Dame Cathedral",
                "Admire Gothic architecture", false));
        items.add(new ItineraryItem("18:00", "Seine River Cruise",
                "Evening boat tour with city views", false));

        // Day 3
        items.add(new ItineraryItem("Day 3: Versailles & Shopping", true));
        items.add(new ItineraryItem("08:30", "Travel to Versailles",
                "Take RER C to Palace of Versailles", false));
        items.add(new ItineraryItem("10:00", "Palace of Versailles Tour",
                "Explore the royal residence and gardens", false));
        items.add(new ItineraryItem("15:00", "Return to Paris",
                "Head back to the city center", false));
        items.add(new ItineraryItem("17:00", "Shopping at Champs-Élysées",
                "Luxury shopping and window browsing", false));
        items.add(new ItineraryItem("20:00", "Farewell Dinner",
                "Final evening in Paris", false));

        return items;
    }

    private void showSuccessDialog() {
        Dialog successDialog = new Dialog(this);
        successDialog.setContentView(R.layout.dialog_success);
        successDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        Button viewItineraryButton = successDialog.findViewById(R.id.viewItineraryButton);
        viewItineraryButton.setOnClickListener(v -> successDialog.dismiss());

        Button closeButton = successDialog.findViewById(R.id.closeButton);
        closeButton.setOnClickListener(v -> successDialog.dismiss());

        successDialog.show();
    }

    private void shareItinerary() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My Trip to " + trip.getDestination());
        shareIntent.putExtra(Intent.EXTRA_TEXT, generateShareText());
        startActivity(Intent.createChooser(shareIntent, "Share Itinerary"));
    }

    private String generateShareText() {
        StringBuilder shareText = new StringBuilder();
        shareText.append("My Trip to ").append(trip.getDestination()).append("\n");
        shareText.append("Dates: ").append(trip.getStartDate()).append(" - ").append(trip.getEndDate()).append("\n\n");
        // Add more details as needed
        return shareText.toString();
    }

    private void downloadItinerary() {
        // Implement PDF generation or other download functionality
        // For now, just show a toast message
        androidx.widget.Toast.makeText(this, "Downloading itinerary...", androidx.widget.Toast.LENGTH_SHORT).show();
    }

    private void printItinerary() {
        // Implement print functionality
        // For now, just show a toast message
        androidx.widget.Toast.makeText(this, "Preparing to print...", androidx.widget.Toast.LENGTH_SHORT).show();
    }
}