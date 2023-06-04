package com.cinereview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;
import java.util.Objects;

public class BuscaActivity extends AppCompatActivity {

    private AutoCompleteTextView autoCompleteTextView_busca;
    private DBHelper dbHelper;
    private ArrayAdapter<String> adapter;

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
                    // Não é necessário fazer nada, já estamos na tela de busca
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

        dbHelper = new DBHelper(this);

        autoCompleteTextView_busca = findViewById(R.id.autoCompleteTextView_busca);

        List<String> searchHistory = dbHelper.getAllSearchQueries();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, searchHistory);
        autoCompleteTextView_busca.setAdapter(adapter);

        Button btn_busca = findViewById(R.id.btn_busca);
        btn_busca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarBusca();
            }
        });
    }

    private void enviarBusca() {
        String searchQuery = autoCompleteTextView_busca.getText().toString().trim();
        if (!searchQuery.isEmpty()) {
            dbHelper.insertSearchQuery(searchQuery);
        }

        List<String> searchHistory = dbHelper.getAllSearchQueries();
        adapter.clear();
        adapter.addAll(searchHistory);
        adapter.notifyDataSetChanged();

        // Lógica para lidar com a ação de enviar a busca aqui
    }
}
