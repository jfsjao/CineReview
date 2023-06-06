package com.cinereview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Objects;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


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

        ArrayList<Filme> todosFilmes = new ArrayList<>();

//        Ator[] atores1 = {
//                new Ator("Tom Hanks", 65, "Americano", "https://upload.wikimedia.org/wikipedia/commons/5/5c/Ridley_Scott_by_Gage_Skidmore.jpg"),
//                new Ator("Emma Watson", 31, "Britânica", "https://upload.wikimedia.org/wikipedia/commons/5/5c/Ridley_Scott_by_Gage_Skidmore.jpg"),
//                new Ator("Robert Downey Jr.", 56, "Americano", "https://upload.wikimedia.org/wikipedia/commons/5/5c/Ridley_Scott_by_Gage_Skidmore.jpg")
//        };
//
//        Diretor diretor1 = new Diretor("Christopher Nolan", 51, "Britânico", "");
//
//        Filme filme1 = new Filme.Builder("Inception")
//                .setId("001") // ID do filme
//                .setGenero(new String[]{"Ação", "Ficção Científica"})
//                .setAtores(atores1)
//                .setDiretor(diretor1)
//                .setNota(8.8f)
//                .setDescricao("Dom Cobb é um habilidoso ladrão, o melhor na perigosa arte da extração: roubar segredos valiosos do inconsciente durante o sono das pessoas.")
//                .setCartaz("https://upload.wikimedia.org/wikipedia/pt/e/e7/Martian_poster_2015.jpg")
//                .setDataLancamento("16/07/2010")
//                .build();
//
//        todosFilmes.add(filme1);
//
//        Gson gson = new GsonBuilder().create();
//        String filmeJson = gson.toJson(filme1);

       // FirebaseDatabase database = FirebaseDatabase.getInstance();
      //  DatabaseReference filmesRef = database.getReference().child("filmes");
       //filmesRef.child(filme1.getId()).setValue(filmeJson);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference filmesRef = database.getReference().child("filmes");



        filmesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //todosFilmes.clear();

                for (DataSnapshot filmeSnapshot : dataSnapshot.getChildren()) {
                    String filmeJson = filmeSnapshot.getValue(String.class);

                    Gson gson = new Gson();
                    Filme filme = gson.fromJson(filmeJson, Filme.class);

                    todosFilmes.add(filme);
                }
                ListView listView1 = findViewById(R.id.listView01);
                FilmeAdapterListViewCompleto filmeAdapter =
                        new FilmeAdapterListViewCompleto(ListaFilmesActivity.this, todosFilmes);
                listView1.setAdapter(filmeAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Tratar erro, catch
            }
        });

    }
}