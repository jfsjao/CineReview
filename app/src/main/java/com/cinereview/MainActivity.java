package com.cinereview;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setBackgroundDrawable(null);


        // Remover a barra de status
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);



        TextView cadastrarTextView = findViewById(R.id.cadastrarTextView);
        cadastrarTextView.setOnClickListener(new View.OnClickListener() {// Remover a barra de status


            @Override
            public void onClick(View v) {
                // Iniciar a nova atividade (CadastrarActivity)
                Intent intent = new Intent(MainActivity.this, Cadastrar.class);
                startActivity(intent);
            }
        });
    }
}
