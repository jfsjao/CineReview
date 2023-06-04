package com.cinereview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class MinhasCelebridadesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_celebridades);
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
                    // Navegar para a tela de busca
                    Intent intentBusca = new Intent(MinhasCelebridadesActivity.this, BuscaActivity.class);
                    startActivity(intentBusca);
                    return true;
                case R.id.menu_item2:
                    // Navegar para a tela de recomendações(
                    Intent intentRecomendacoes = new Intent(MinhasCelebridadesActivity.this, RecomendacoesActivity.class);
                    startActivity(intentRecomendacoes);
                    return true;
                case R.id.menu_item3:
                    Intent intentHome = new Intent(MinhasCelebridadesActivity.this, HomeActivity.class);
                    startActivity(intentHome);
                    return true;

                case R.id.menu_item4:
                    // Navegar para a tela de minhas celebridades(esta tela)
                    // Não é necessário fazer nada, já estamos na tela de home
                    return true;
                case R.id.menu_item5:
                    // Navegar para a tela de minha lista
                    Intent intentMinhaLista = new Intent(MinhasCelebridadesActivity.this, ListaFilmesActivity.class);
                    startActivity(intentMinhaLista);
                    return true;
                default:
                    return false;
            }
        });
    }
}