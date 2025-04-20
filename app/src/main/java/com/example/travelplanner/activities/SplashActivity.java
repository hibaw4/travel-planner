// Affiche un écran d’accueil temporaire avec le logo de l’application.
// Elle sert à créer une première impression, tout en vérifiant si l’utilisateur
// est déjà connecté pour ensuite rediriger automatiquement vers la page de connexion
// ou le tableau de bord.

package com.example.travelplanner.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.travelplanner.R;
import com.example.travelplanner.activities.LoginActivity; // Import LoginActivity

import android.util.Log;

public class SplashActivity extends Activity {

    private static final int SPLASH_DISPLAY_LENGTH = 3000; // Duration of splash screen (3 seconds)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Log pour vérifier si l'écran splash se charge
        Log.d("SplashActivity", "Splash screen displayed");

        // Optionally bind the views here (for animations or visual updates)
        ImageView logoImageView = findViewById(R.id.logoImageView);
        TextView sloganText = findViewById(R.id.sloganText);
        ProgressBar progressBar = findViewById(R.id.progressBar);

        // Use Handler to delay the transition to LoginActivity
        new Handler().postDelayed(() -> {
            Log.d("SplashActivity", "Redirecting to LoginActivity");
            // Start LoginActivity after the splash screen
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Prevent going back to splash screen when pressing back
        }, SPLASH_DISPLAY_LENGTH);
    }
}




