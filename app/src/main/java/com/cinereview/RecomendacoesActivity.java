package com.cinereview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

public class RecomendacoesActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseUser user;

    private DatabaseReference filmesRef;
    private DatabaseReference userFavoritesRef;

    private FilmeAdapterListView adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomendacoes);
        // Remove o título
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().setBackgroundDrawable(null);

        // Remover a barra de status
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            switch (itemId) {
                case R.id.menu_item1:
                    // Navegar para a tela de busca
                    Intent intentBusca = new Intent(RecomendacoesActivity.this, BuscaActivity.class);
                    startActivity(intentBusca);
                    return true;
                case R.id.menu_item2:
                    // Navegar para a tela de recomendações (esta tela)
                    // Não é necessário fazer nada, já estamos na tela de recomendações
                    return true;
                case R.id.menu_item3:
                    // Navegar para a tela de home
                    Intent intentHome = new Intent(RecomendacoesActivity.this, HomeActivity.class);
                    startActivity(intentHome);
                    return true;
                case R.id.menu_item4:
                    // Navegar para a tela de minhas celebridades
                    Intent intentCelebridades = new Intent(RecomendacoesActivity.this, MinhasCelebridadesActivity.class);
                    startActivity(intentCelebridades);
                    return true;
                case R.id.menu_item5:
                    // Navegar para a tela de minha lista
                    Intent intentMinhaLista = new Intent(RecomendacoesActivity.this, ListaFilmesActivity.class);
                    startActivity(intentMinhaLista);
                    return true;
                default:
                    return false;
            }
        });

        ListView recommendedListView = findViewById(R.id.recommendedListView);
        adapter = new FilmeAdapterListView(this, new ArrayList<>());
        recommendedListView.setAdapter(adapter);

        setupUserFavoritesRef();
        getRecommendedMovies();
    }

    private void setupUserFavoritesRef() {
        String userId = user.getUid();
        userFavoritesRef = FirebaseDatabase.getInstance()
                .getReference()
                .child("users")
                .child(userId)
                .child("favoritos");
    }

    private void getRecommendedMovies() {
        Gson gson = new Gson();

        userFavoritesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> genres = new ArrayList<>();
                for (DataSnapshot filmeSnapshot : dataSnapshot.getChildren()) {
                    String filmeJson = filmeSnapshot.getValue(String.class);
                    Filme filme = gson.fromJson(filmeJson, Filme.class);
                    String[] filmeGeneros = filme.getGenero();
                    if (filmeGeneros != null) {
                        genres.addAll(Arrays.asList(filmeGeneros));
                    }
                }

                filmesRef = FirebaseDatabase.getInstance()
                        .getReference()
                        .child("filmes");

                filmesRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<Filme> recommendedMovies = new ArrayList<>();
                        for (DataSnapshot filmeSnapshot : dataSnapshot.getChildren()) {
                            String filmeJson = filmeSnapshot.getValue(String.class);
                            Filme filme = gson.fromJson(filmeJson, Filme.class);
                            String[] filmeGeneros = filme.getGenero();
                            if (filmeGeneros != null) {
                                for (String genre : filmeGeneros) {
                                    if (genres.contains(genre) && !recommendedMovies.contains(filme)) {
                                        recommendedMovies.add(filme);
                                        break;
                                    }
                                }
                            }
                        }

                        adapter.clear();
                        adapter.addAll(recommendedMovies);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

}
