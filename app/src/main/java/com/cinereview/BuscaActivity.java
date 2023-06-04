package com.cinereview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class BuscaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca);
        // Remove o título
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().setBackgroundDrawable(null);

        // Remover a barra de status
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            switch (itemId) {
                case R.id.menu_item1:
                    // Navegar para a tela de busca (esta tela)
                    // Não é necessário fazer nada, já estamos na tela de home
                    return true;
                case R.id.menu_item2:
                    // Navegar para a tela de recomendações
                    Intent intentRecomendacoes = new Intent(BuscaActivity.this, RecomendacoesActivity.class);
                    startActivity(intentRecomendacoes);
                    return true;
                case R.id.menu_item3:
                    // Navegar para a tela de home
                    Intent intentHome = new Intent(BuscaActivity.this, HomeActivity.class);
                    startActivity(intentHome);
                    return true;
                case R.id.menu_item4:
                    // Navegar para a tela de minhas celebridades
                    Intent intentCelebridades = new Intent(BuscaActivity.this, MinhasCelebridadesActivity.class);
                    startActivity(intentCelebridades);
                    return true;
                case R.id.menu_item5:
                    // Navegar para a tela de minha lista
                    Intent intentMinhaLista = new Intent(BuscaActivity.this, ListaFilmesActivity.class);
                    startActivity(intentMinhaLista);
                    return true;
                default:
                    return false;
            }
        });
    }
}