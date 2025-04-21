// Réunit toutes les réservations liées au voyage (vols, hôtels, transports).
// Elle permet à l’utilisateur de voir, ajouter ou modifier ses réservations,
// et peut même intégrer une API externe pour réserver directement depuis l’application.

package com.example.travelplanner.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelplanner.R;
import com.example.travelplanner.adapters.FlightAdapter;
import com.example.travelplanner.adapters.HotelAdapter;
import com.example.travelplanner.adapters.TransportationAdapter;
import com.example.travelplanner.models.Flight;
import com.example.travelplanner.models.Hotel;
import com.example.travelplanner.models.Transportation;
import com.example.travelplanner.models.Trip;

import java.util.ArrayList;
import java.util.List;

public class ReservationActivity extends AppCompatActivity {

    private Trip trip;
    private int currentStep = 1; // 1: Flights, 2: Hotels, 3: Transportation

    // Flight booking views
    private LinearLayout flightSection;
    private RadioGroup tripTypeGroup;
    private EditText fromInput, toInput;
    private TextView departDateText, returnDateText;
    private EditText passengersInput;
    private RadioGroup classGroup;
    private RecyclerView flightResultsRecycler;

    // Hotel booking views
    private LinearLayout hotelSection;
    private EditText destinationInput;
    private TextView checkInDateText, checkOutDateText;
    private EditText guestsInput, roomsInput;
    private CheckBox wifiCheck, restaurantCheck, fitnessCheck, poolCheck, spaCheck, petFriendlyCheck;
    private RecyclerView hotelResultsRecycler;

    // Transportation views
    private LinearLayout transportationSection;
    private RadioGroup transportTypeGroup;
    private RecyclerView transportResultsRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        // Get trip details from intent
        trip = getIntent().getParcelableExtra("trip");

        initializeViews();
        showSection(1); // Show flight booking first
    }

    private void initializeViews() {
        // Initialize flight views
        flightSection = findViewById(R.id.flightSection);
        tripTypeGroup = findViewById(R.id.tripTypeGroup);
        fromInput = findViewById(R.id.fromInput);
        toInput = findViewById(R.id.toInput);
        departDateText = findViewById(R.id.departDateText);
        returnDateText = findViewById(R.id.returnDateText);
        passengersInput = findViewById(R.id.passengersInput);
        classGroup = findViewById(R.id.classGroup);
        flightResultsRecycler = findViewById(R.id.flightResultsRecycler);

        // Initialize hotel views
        hotelSection = findViewById(R.id.hotelSection);
        destinationInput = findViewById(R.id.destinationInput);
        checkInDateText = findViewById(R.id.checkInDateText);
        checkOutDateText = findViewById(R.id.checkOutDateText);
        guestsInput = findViewById(R.id.guestsInput);
        roomsInput = findViewById(R.id.roomsInput);
        wifiCheck = findViewById(R.id.wifiCheck);
        restaurantCheck = findViewById(R.id.restaurantCheck);
        fitnessCheck = findViewById(R.id.fitnessCheck);
        poolCheck = findViewById(R.id.poolCheck);
        spaCheck = findViewById(R.id.spaCheck);
        petFriendlyCheck = findViewById(R.id.petFriendlyCheck);
        hotelResultsRecycler = findViewById(R.id.hotelResultsRecycler);

        // Initialize transportation views
        transportationSection = findViewById(R.id.transportationSection);
        transportTypeGroup = findViewById(R.id.transportTypeGroup);
        transportResultsRecycler = findViewById(R.id.transportResultsRecycler);

        // Set up RecyclerViews
        flightResultsRecycler.setLayoutManager(new LinearLayoutManager(this));
        hotelResultsRecycler.setLayoutManager(new LinearLayoutManager(this));
        transportResultsRecycler.setLayoutManager(new LinearLayoutManager(this));

        // Pre-fill data from trip
        if (trip != null) {
            toInput.setText(trip.getDestination());
            destinationInput.setText(trip.getDestination());
            passengersInput.setText(String.valueOf(trip.getTravelers()));
            guestsInput.setText(String.valueOf(trip.getTravelers()));
        }

        // Search buttons
        Button searchFlightsButton = findViewById(R.id.searchFlightsButton);
        searchFlightsButton.setOnClickListener(v -> searchFlights());

        Button searchHotelsButton = findViewById(R.id.searchHotelsButton);
        searchHotelsButton.setOnClickListener(v -> searchHotels());

        Button continueToAccommodationsButton = findViewById(R.id.continueToAccommodationsButton);
        continueToAccommodationsButton.setOnClickListener(v -> showSection(2));

        Button continueToTransportationButton = findViewById(R.id.continueToTransportationButton);
        continueToTransportationButton.setOnClickListener(v -> showSection(3));
    }

    private void showSection(int step) {
        currentStep = step;
        flightSection.setVisibility(step == 1 ? View.VISIBLE : View.GONE);
        hotelSection.setVisibility(step == 2 ? View.VISIBLE : View.GONE);
        transportationSection.setVisibility(step == 3 ? View.VISIBLE : View.GONE);
    }

    private void searchFlights() {
        // Mock flight data (replace with API call)
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight("Air France", "08:25", "10:45", "New York", "Paris, France", 320, "2h 20m"));
        flights.add(new Flight("British Airways", "10:15", "12:50", "New York", "Paris, France", 285, "2h 35m"));
        flights.add(new Flight("Lufthansa", "14:30", "17:15", "New York", "Paris, France", 310, "2h 45m"));
        flights.add(new Flight("KLM", "16:45", "19:10", "New York", "Paris, France", 295, "2h 25m"));

        FlightAdapter adapter = new FlightAdapter(flights, flight -> {
            trip.setSelectedFlight(flight);
            showSection(2); // Move to hotel booking
        });
        flightResultsRecycler.setAdapter(adapter);
    }

    private void searchHotels() {
        // Mock hotel data (replace with API call)
        List<Hotel> hotels = new ArrayList<>();
        hotels.add(new Hotel("Hôtel Plaza Athénée", "25 Avenue Montaigne, 8th arr., Paris", 450, 4.7, 487,
                "Luxury hotel located on the prestigious Avenue Montaigne, with Eiffel Tower views and elegant rooms."));
        hotels.add(new Hotel("Hôtel Le Marais", "12 Rue des Archives, 4th arr., Paris", 220, 4.5, 312,
                "Boutique hotel in the trendy Le Marais district, offering contemporary rooms in a historic building."));
        hotels.add(new Hotel("Citadines Saint-Germain-des-Prés", "53 Ter Quai des Grands Augustins, 6th arr., Paris", 180, 4.3, 529,
                "Modern aparthotel on the Left Bank, with spacious studios and apartments featuring fully equipped kitchenettes."));

        HotelAdapter adapter = new HotelAdapter(hotels, hotel -> {
            trip.setSelectedHotel(hotel);
            showSection(3); // Move to transportation
        });
        hotelResultsRecycler.setAdapter(adapter);
    }

    private void showTransportationOptions() {
        List<Transportation> options = new ArrayList<>();
        int selectedType = transportTypeGroup.getCheckedRadioButtonId();

        if (selectedType == R.id.rentalCarButton) {
            options.add(new Transportation("Economy Car", "Hertz", 45, "Compact car suitable for city driving. Fuel-efficient and easy to park."));
            options.add(new Transportation("Midsize Car", "Avis", 65, "Comfortable midsize car with more space for passengers and luggage."));
            options.add(new Transportation("Premium Car", "Europcar", 95, "Luxury vehicle with premium features and superior comfort."));
        } else if (selectedType == R.id.trainTicketsButton) {
            options.add(new Transportation("Metro Pass", "RATP", 25, "7-day unlimited metro pass for zones 1-3"));
            options.add(new Transportation("RER + Metro Pass", "RATP", 35, "7-day combined RER and metro pass for all zones"));
        } else if (selectedType == R.id.busTransportButton) {
            options.add(new Transportation("Bus Pass", "RATP", 20, "7-day unlimited bus pass for all routes"));
            options.add(new Transportation("Paris Visite Pass", "RATP", 40, "Tourist pass with unlimited travel on all public transport"));
        }

        TransportationAdapter adapter = new TransportationAdapter(options, transport -> {
            trip.setSelectedTransportation(transport);
            completeTripBooking();
        });
        transportResultsRecycler.setAdapter(adapter);
    }

    private void completeTripBooking() {
        // Save trip details and navigate to itinerary
        Intent intent = new Intent(this, ItineraryActivity.class);
        intent.putExtra("trip", trip);
        startActivity(intent);
        finish();
    }
}