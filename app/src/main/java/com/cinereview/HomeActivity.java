package com.cinereview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Objects;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private Button logout;
    private FirebaseUser user;

    private DatabaseReference filmesRef;
    private DatabaseReference userFavoritesRef;

    private ArrayList<Filme> todosFilmes;
    private ArrayList<Filme> filmesFavoritos;

    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Remove o título
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().setBackgroundDrawable(null);

        // Remover a barra de status
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Inicializa os componentes da UI
        initializeUI();

        // Verifica se o usuário está logado
        if (user == null) {
            navigateToMainActivity();
        } else {
            setupLogoutButton();
            setupBottomNavigationView();
            setupFilmesRef();
            setupUserFavoritesRef();
            loadFilmes();
        }
    }

    private void initializeUI() {
        auth = FirebaseAuth.getInstance();
        logout = findViewById(R.id.btnLogout);
        user = auth.getCurrentUser();
        todosFilmes = new ArrayList<>();
        filmesFavoritos = new ArrayList<>();
        gson = new GsonBuilder().create();
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void setupLogoutButton() {
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                navigateToMainActivity();
            }
        });
    }

    private void setupBottomNavigationView() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            switch (itemId) {
                case R.id.menu_item1:
                    navigateToActivity(BuscaActivity.class);
                    return true;
                case R.id.menu_item2:
                    navigateToActivity(RecomendacoesActivity.class);
                    return true;
                case R.id.menu_item3:
                    // Não é necessário fazer nada, já estamos na tela de home
                    return true;
                case R.id.menu_item4:
                    navigateToActivity(MinhasCelebridadesActivity.class);
                    return true;
                case R.id.menu_item5:
                    navigateToActivity(ListaFilmesActivity.class);
                    return true;
                default:
                    return false;
            }
        });
    }

    private void navigateToActivity(Class<?> activityClass) {
        Intent intent = new Intent(HomeActivity.this, activityClass);
        startActivity(intent);
    }

    private void setupFilmesRef() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        filmesRef = database.getReference().child("filmes");
    }

    private void setupUserFavoritesRef() {
        String userId = user.getUid();
        userFavoritesRef = FirebaseDatabase.getInstance()
                .getReference()
                .child("users")
                .child(userId)
                .child("favoritos");
    }

    private void loadFilmes() {
        filmesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot filmeSnapshot : dataSnapshot.getChildren()) {
                    String filmeJson = filmeSnapshot.getValue(String.class);
                    Filme filme = gson.fromJson(filmeJson, Filme.class);
                    todosFilmes.add(filme);
                }
                LinearLayout horizontalLayout = findViewById(R.id.layoutFilmes);
                FilmeAdapterListView filmeAdapter = new FilmeAdapterListView(HomeActivity.this,
                        todosFilmes);
                for (int i = 0; i < filmeAdapter.getCount(); i++) {
                    View itemView = filmeAdapter.getView(i, null, horizontalLayout);
                    Filme filme = todosFilmes.get(i);
                    setupFilmeItemClickListener(itemView, filme);
                    horizontalLayout.addView(itemView);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Tratar erro, se necessário
            }
        });

        // Dentro do método setupFilmeItemClickListener(), após adicionar ou remover o filme dos favoritos
        userFavoritesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot filmeSnapshot : dataSnapshot.getChildren()) {
                    String filmeJson = filmeSnapshot.getValue(String.class);
                    Filme filme = gson.fromJson(filmeJson, Filme.class);
                    filmesFavoritos.add(filme);
                }

                LinearLayout horizontalLayout = findViewById(R.id.layoutFavoritos);
                FilmeAdapterListView filmeAdapter = new FilmeAdapterListView(HomeActivity.this, filmesFavoritos);
                for (int i = 0; i < filmeAdapter.getCount(); i++) {
                    View itemView = filmeAdapter.getView(i, null, horizontalLayout);
                    Filme filme = filmesFavoritos.get(i);
                    setupFilmeItemClickListener(itemView, filme);
                    horizontalLayout.addView(itemView);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Tratar erro, se necessário
            }
        });

    }

    private void setupFilmeItemClickListener(View itemView, Filme filme) {
        String filmeId = filme.getId();
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userFavoritesRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild(filmeId)) {
                            // O filme já está nos favoritos, então exclua-o
                            userFavoritesRef.child(filmeId).removeValue();
                            Toast.makeText(HomeActivity.this, "Filme retirado dos favoritos",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            String filmeJson = gson.toJson(filme);
                            userFavoritesRef.child(filmeId).setValue(filmeJson);
                            Toast.makeText(HomeActivity.this, "Filme adicionado aos favoritos",
                                    Toast.LENGTH_SHORT).show();
                        }
                        updateFavoritosView();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Tratar erro, se necessário
                    }
                });
            }
        });

    }

        private void updateFavoritosView() {
        userFavoritesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                filmesFavoritos.clear();
                for (DataSnapshot filmeSnapshot : dataSnapshot.getChildren()) {
                    String filmeJson = filmeSnapshot.getValue(String.class);
                    Filme filme = gson.fromJson(filmeJson, Filme.class);
                    filmesFavoritos.add(filme);
                }

                LinearLayout horizontalLayout = findViewById(R.id.layoutFavoritos);
                horizontalLayout.removeAllViews(); // Limpa as visualizações existentes

                FilmeAdapterListView filmeAdapter = new FilmeAdapterListView(HomeActivity.this, filmesFavoritos);
                for (int i = 0; i < filmeAdapter.getCount(); i++) {
                    View itemView = filmeAdapter.getView(i, null, horizontalLayout);
                    Filme filme = filmesFavoritos.get(i);
                    setupFilmeItemClickListener(itemView, filme);
                    horizontalLayout.addView(itemView);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Tratar erro, se necessário
            }
        });
    }

}