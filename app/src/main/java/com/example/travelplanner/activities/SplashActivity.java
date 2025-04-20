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

import com.example.travelplanner.activities.LoginActivity;
import com.example.travelplanner.R;

public class SplashActivity extends Activity {

    private static final int SPLASH_DISPLAY_LENGTH = 3000; // Durée d'affichage : 3 secondes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash); // Lie au fichier activity_splash.xml

        // Liaison des vues (facultatif ici, mais utile si tu veux animer par exemple)
        ImageView logoImageView = findViewById(R.id.logoImageView);
        TextView sloganText = findViewById(R.id.sloganText);
        ProgressBar progressBar = findViewById(R.id.progressBar);

        // Démarre un délai, puis lance LoginActivity ou autre
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Empêche de revenir au Splash en appuyant sur "Retour"
        }, SPLASH_DISPLAY_LENGTH);
    }
}


