// Permet aux utilisateurs de se connecter avec leur adresse email et mot de passe.
// Elle propose aussi une option "mot de passe oublié" et un bouton pour accéder
// à la page d’inscription s’ils n’ont pas encore de compte.

package com.example.travelplanner.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travelplanner.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Button for navigating to the registration screen
        Button goToRegister = findViewById(R.id.buttonGoToRegister);
        goToRegister.setOnClickListener(v -> {
            // Intent to go to the RegisterActivity
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish(); // Finish LoginActivity to prevent the user from returning to it when pressing the back button
        });

        // Button for logging in
        Button loginButton = findViewById(R.id.buttonLogin);
        loginButton.setOnClickListener(v -> {
            // Handle login logic here...
        });
    }
}

