package com.example.travelplanner.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelplanner.R;
import com.example.travelplanner.models.Hotel;

import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder> {

    private List<Hotel> hotels;
    private OnHotelSelectedListener listener;

    public interface OnHotelSelectedListener {
        void onHotelSelected(Hotel hotel);
    }

    public HotelAdapter(List<Hotel> hotels, OnHotelSelectedListener listener) {
        this.hotels = hotels;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hotel, parent, false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {
        Hotel hotel = hotels.get(position);
        holder.bind(hotel);
    }

    @Override
    public int getItemCount() {
        return hotels.size();
    }

    class HotelViewHolder extends RecyclerView.ViewHolder {
        private ImageView hotelImage;
        private TextView hotelNameText;
        private TextView addressText;
        private TextView priceText;
        private RatingBar ratingBar;
        private TextView reviewCountText;
        private TextView descriptionText;
        private TextView amenitiesText;
        private Button selectButton;

        HotelViewHolder(@NonNull View itemView) {
            super(itemView);
            hotelImage = itemView.findViewById(R.id.hotelImage);
            hotelNameText = itemView.findViewById(R.id.hotelNameText);
            addressText = itemView.findViewById(R.id.addressText);
            priceText = itemView.findViewById(R.id.priceText);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            reviewCountText = itemView.findViewById(R.id.reviewCountText);
            descriptionText = itemView.findViewById(R.id.descriptionText);
            amenitiesText = itemView.findViewById(R.id.amenitiesText);
            selectButton = itemView.findViewById(R.id.selectButton);
        }

        void bind(Hotel hotel) {
            hotelNameText.setText(hotel.getName());
            addressText.setText(hotel.getAddress());
            priceText.setText(String.format("$%.0f per night", hotel.getPricePerNight()));
            ratingBar.setRating((float) hotel.getRating());
            reviewCountText.setText(hotel.getReviewCount() + " reviews");
            descriptionText.setText(hotel.getDescription());

            selectButton.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onHotelSelected(hotel);
                }
            });
        }
    }
}