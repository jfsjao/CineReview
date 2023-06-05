package com.cinereview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.WindowManager;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MinhasCelebridadesActivity extends AppCompatActivity {


    private ArrayList<Ator> atoresList;
    private ArrayList<Diretor> diretoresList;


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


        atoresList = new ArrayList<>();
        diretoresList = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference filmesRef = database.getReference().child("filmes");

        filmesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot filmeSnapshot : dataSnapshot.getChildren()) {
                    String filmeJson = filmeSnapshot.getValue(String.class);

                    Gson gson = new Gson();
                    Filme filme = gson.fromJson(filmeJson, Filme.class);

                    // Extrair atores e adicionar à lista de atores
                    List<Ator> atores = Arrays.asList(filme.getAtores());
                    atoresList.addAll(atores);

                    // Extrair diretor e adicionar à lista de diretores
                    Diretor diretor = filme.getDiretor();
                    diretoresList.add(diretor);
                }


                AtorAdapter atorAdapter = new AtorAdapter(MinhasCelebridadesActivity.this, atoresList);
                DiretorAdapter diretorAdapter = new DiretorAdapter(MinhasCelebridadesActivity.this, diretoresList);


                ListView listViewAtores = findViewById(R.id.listViewAtores);
                ListView listViewDiretores = findViewById(R.id.listViewDiretores);


                listViewAtores.setAdapter(atorAdapter);
                listViewDiretores.setAdapter(diretorAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Tratar erro, catch
            }
        });
    }
}