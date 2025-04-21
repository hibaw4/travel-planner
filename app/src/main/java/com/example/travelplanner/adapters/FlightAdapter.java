package com.example.travelplanner.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelplanner.R;
import com.example.travelplanner.models.Flight;

import java.util.List;

public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.FlightViewHolder> {

    private List<Flight> flights;
    private OnFlightSelectedListener listener;

    public interface OnFlightSelectedListener {
        void onFlightSelected(Flight flight);
    }

    public FlightAdapter(List<Flight> flights, OnFlightSelectedListener listener) {
        this.flights = flights;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FlightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_flight, parent, false);
        return new FlightViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightViewHolder holder, int position) {
        Flight flight = flights.get(position);
        holder.bind(flight);
    }

    @Override
    public int getItemCount() {
        return flights.size();
    }

    class FlightViewHolder extends RecyclerView.ViewHolder {
        private ImageView airlineLogo;
        private TextView airlineNameText;
        private TextView departureTimeText;
        private TextView arrivalTimeText;
        private TextView departureAirportText;
        private TextView arrivalAirportText;
        private TextView priceText;
        private TextView durationText;
        private Button selectButton;

        FlightViewHolder(@NonNull View itemView) {
            super(itemView);
            airlineLogo = itemView.findViewById(R.id.airlineLogo);
            airlineNameText = itemView.findViewById(R.id.airlineNameText);
            departureTimeText = itemView.findViewById(R.id.departureTimeText);
            arrivalTimeText = itemView.findViewById(R.id.arrivalTimeText);
            departureAirportText = itemView.findViewById(R.id.departureAirportText);
            arrivalAirportText = itemView.findViewById(R.id.arrivalAirportText);
            priceText = itemView.findViewById(R.id.priceText);
            durationText = itemView.findViewById(R.id.durationText);
            selectButton = itemView.findViewById(R.id.selectButton);
        }

        void bind(Flight flight) {
            airlineNameText.setText(flight.getAirline());
            departureTimeText.setText(flight.getDepartureTime());
            arrivalTimeText.setText(flight.getArrivalTime());
            departureAirportText.setText(flight.getDepartureCity());
            arrivalAirportText.setText(flight.getArrivalCity());
            priceText.setText(String.format("$%.0f", flight.getPrice()));
            durationText.setText(flight.getDuration());

            selectButton.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onFlightSelected(flight);
                }
            });
        }
    }
}