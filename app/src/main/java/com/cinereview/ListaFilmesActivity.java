package com.cinereview;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Objects;

public class ListaFilmesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_filmes);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setBackgroundDrawable(null);

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
                    Intent intentBusca = new Intent(ListaFilmesActivity.this, BuscaActivity.class);
                    startActivity(intentBusca);
                    return true;
                case R.id.menu_item2:
                    // Navegar para a tela de recomendações
                    Intent intentRecomendacoes = new Intent(ListaFilmesActivity.this, RecomendacoesActivity.class);
                    startActivity(intentRecomendacoes);
                    return true;
                case R.id.menu_item3:
                    // Navegar para a tela de home
                    Intent intentHome = new Intent(ListaFilmesActivity.this, HomeActivity.class);
                    startActivity(intentHome);
                    return true;
                case R.id.menu_item4:
                    // Navegar para a tela de minhas celebridades
                    Intent intentCelebridades = new Intent(ListaFilmesActivity.this, MinhasCelebridadesActivity.class);
                    startActivity(intentCelebridades);
                    return true;
                case R.id.menu_item5:
                    // Navegar para a tela de minha lista(esta tela)
                    // Não é necessário fazer nada, já estamos na tela de home
                    return true;
                default:
                    return false;
            }
        });
        ArrayList<Filme> listaFilmes = new ArrayList<>();

        Ator[] atores = {
                new Ator("Ator 1", 23, "Brasileiro", null),
                new Ator("Ator 2", 23, "Brasileiro", null),
                new Ator("Ator 3", 23, "Brasileiro", null)
        };

        Diretor diretor = new Diretor("Diretor 1", 23, "Brasileiro", null);

        Filme filme1 = new Filme.Builder("Filme 1")
                .setGenero(new String[] {"Ação", "Drama"})
                .setAtores(atores)
                .setDiretor(diretor)
                .setNota(8.5f)
                .setDescricao("Descrição do Filme 1")
                .build();
        System.out.println(filme1);
        listaFilmes.add(filme1);
        listaFilmes.add(filme1);
        listaFilmes.add(filme1);
        System.out.println(filme1);


        ListView listView1 = findViewById(R.id.listView01);
        FilmeAdapterListView filmeAdapter = new FilmeAdapterListView(ListaFilmesActivity.this, listaFilmes);
        listView1.setAdapter(filmeAdapter);
    }
}