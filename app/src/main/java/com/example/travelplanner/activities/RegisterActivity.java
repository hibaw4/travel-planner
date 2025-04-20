//Page dédiée à la création d’un nouveau compte utilisateur.
// L’utilisateur y remplit un formulaire (nom, email, mot de passe)
// et s’enregistre dans la base de données. Elle vérifie aussi la validité des informations saisies.

package com.example.travelplanner.activities;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travelplanner.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button goToLogin = findViewById(R.id.buttonGoToLogin);
        goToLogin.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        Button registerButton = findViewById(R.id.buttonRegister);
        registerButton.setOnClickListener(v -> {
            // Traiter l’inscription ici...
        });
    }
}

