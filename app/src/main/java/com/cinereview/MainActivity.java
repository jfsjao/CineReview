package com.cinereview;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setBackgroundDrawable(null);

        // Remover a barra de status
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button botaoLogin = findViewById(R.id.botao_login);
        botaoLogin.setOnClickListener(v -> {
            // Iniciar a nova atividade (HomeActivity)
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        });

        TextView cadastrarTextView = findViewById(R.id.cadastrarTextView);
        cadastrarTextView.setOnClickListener(v -> {
            // Iniciar a nova atividade (CadastrarActivity)
            Intent intent = new Intent(MainActivity.this, Cadastrar.class);
            startActivity(intent);
        });

        //FirebaseApp.initializeApp(this);

    }
}
