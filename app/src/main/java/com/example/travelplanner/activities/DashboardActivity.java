// C’est la page principale de l’application une fois connecté. Elle affiche
// un résumé rapide des prochains voyages, les itinéraires à venir, et propose des
// raccourcis vers les principales fonctionnalités comme les suggestions, les réservations
// ou la création de voyage.

package com.example.travelplanner.activities;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.travelplanner.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DashboardActivity extends AppCompatActivity {

    private TextView welcomeText;
    private Button planTripButton;
    private BottomNavigationView bottomNavigation;
    private ImageView menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Initialisation des vues
        welcomeText = findViewById(R.id.welcomeText);
        planTripButton = findViewById(R.id.newTripButton);
        bottomNavigation = findViewById(R.id.bottomNavigation);
        menuButton = findViewById(R.id.menuButton);

        // Message de bienvenue (ex. dynamique avec nom d'utilisateur)
        // welcomeText.setText("Bonjour, " + userName);

        // Bouton de création de voyage
        planTripButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Naviguer vers la page de création de voyage
                // Intent intent = new Intent(DashboardActivity.this, CreateTripActivity.class);
                // startActivity(intent);
            }
        });

        // Clique sur le bouton menu
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(v);
            }
        });

        // Navigation inférieure
        setupBottomNavigation();
    }

    private void setupBottomNavigation() {
        bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                // Déjà ici
                return true;
            } else if (itemId == R.id.nav_itineraries) {
                // startActivity(new Intent(DashboardActivity.this, ItinerariesActivity.class));
                return true;
            } else if (itemId == R.id.nav_discover) {
                // startActivity(new Intent(DashboardActivity.this, DiscoverActivity.class));
                return true;
            } else if (itemId == R.id.nav_profile) {
                // startActivity(new Intent(DashboardActivity.this, ProfileActivity.class));
                return true;
            }

            return false;
        });
    }

    private void showPopupMenu(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.getMenuInflater().inflate(R.menu.bottom_nav_menu, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();

                bottomNavigation.setSelectedItemId(itemId); // Synchroniser avec BottomNavigation

                switch (itemId) {
                    case R.id.nav_home:
                        // Déjà sur l'accueil
                        return true;
                    case R.id.nav_itineraries:
                        // startActivity(new Intent(DashboardActivity.this, ItinerariesActivity.class));
                        return true;
                    case R.id.nav_discover:
                        // startActivity(new Intent(DashboardActivity.this, DiscoverActivity.class));
                        return true;
                    case R.id.nav_profile:
                        // startActivity(new Intent(DashboardActivity.this, ProfileActivity.class));
                        return true;
                }
                return false;
            }
        });

        popup.show();
    }
}
