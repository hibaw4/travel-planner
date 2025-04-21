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
import com.example.travelplanner.models.Trip;

import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.TripViewHolder> {

    private List<Trip> trips;
    private OnTripClickListener listener;

    public interface OnTripClickListener {
        void onTripClick(Trip trip);
        void onEditClick(Trip trip);
    }

    public TripAdapter(List<Trip> trips, OnTripClickListener listener) {
        this.trips = trips;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TripViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_trip, parent, false);
        return new TripViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TripViewHolder holder, int position) {
        Trip trip = trips.get(position);
        holder.bind(trip);
    }

    @Override
    public int getItemCount() {
        return trips.size();
    }

    class TripViewHolder extends RecyclerView.ViewHolder {
        private ImageView tripImage;
        private TextView destinationText;
        private TextView locationText;
        private TextView dateRangeText;
        private TextView activityCountText;
        private TextView statusText;
        private Button viewButton;
        private Button editButton;

        TripViewHolder(@NonNull View itemView) {
            super(itemView);
            tripImage = itemView.findViewById(R.id.tripImage);
            destinationText = itemView.findViewById(R.id.destinationText);
            locationText = itemView.findViewById(R.id.locationText);
            dateRangeText = itemView.findViewById(R.id.dateRangeText);
            activityCountText = itemView.findViewById(R.id.activityCountText);
            statusText = itemView.findViewById(R.id.statusText);
            viewButton = itemView.findViewById(R.id.viewButton);
            editButton = itemView.findViewById(R.id.editButton);
        }

        void bind(Trip trip) {
            destinationText.setText(trip.getDestination());

            // Extract location from destination
            String location = trip.getDestination();
            if (location.contains(",")) {
                location = location.substring(location.indexOf(",") + 1).trim();
            }
            locationText.setText(location);

            dateRangeText.setText(trip.getStartDate() + " - " + trip.getEndDate());

            // For demo purposes, show mock activity count
            activityCountText.setText("18 activities");

            // For demo purposes, show status based on date
            statusText.setText("Upcoming");
            statusText.setTextColor(itemView.getContext().getColor(R.color.accent));

            viewButton.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onTripClick(trip);
                }
            });

            editButton.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onEditClick(trip);
                }
            });
        }
    }
}