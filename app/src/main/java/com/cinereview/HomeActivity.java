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

    private ListView listViewMyList;
    private ListView listViewRecomendation;
    private ListView listViewCelebs;

    private FilmeAdapter myListAdapter;
    private FilmeAdapter recomendationAdapter;
    private FilmeAdapter celebsAdapter;

    FirebaseAuth auth;
    Button logout;
    FirebaseUser user;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference filmesRef = database.getReference().child("filmes");

    //String userId = user.getUid();

    ArrayList<Filme> todosFilmes = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
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

        auth = FirebaseAuth.getInstance();
        logout = findViewById(R.id.btnLogout);
        user = auth.getCurrentUser();

        String userId = user.getUid();
        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        } else {

        }
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            switch (itemId) {
                case R.id.menu_item1:
                    // Navegar para a tela de busca
                    Intent intentBusca = new Intent(HomeActivity.this, BuscaActivity.class);
                    startActivity(intentBusca);
                    return true;
                case R.id.menu_item2:
                    // Navegar para a tela de recomendações
                    Intent intentRecomendacoes = new Intent(HomeActivity.this,
                            RecomendacoesActivity.class);
                    startActivity(intentRecomendacoes);
                    return true;
                case R.id.menu_item3:
                    // Navegar para a tela de home (esta tela)
                    // Não é necessário fazer nada, já estamos na tela de home
                    return true;
                case R.id.menu_item4:
                    // Navegar para a tela de minhas celebridades
                    Intent intentCelebridades = new Intent(HomeActivity.this,
                            MinhasCelebridadesActivity.class);
                    startActivity(intentCelebridades);
                    return true;
                case R.id.menu_item5:
                    // Navegar para a tela de minha lista
                    Intent intentMinhaLista = new Intent(HomeActivity.this,
                            ListaFilmesActivity.class);
                    startActivity(intentMinhaLista);
                    return true;
                default:
                    return false;
            }
        });


//        filmesRef.push().setValue(filmeJson);


        // Obter o ID do usuário logado


//        filmesRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                //todosFilmes.clear();
//
//                for (DataSnapshot filmeSnapshot : dataSnapshot.getChildren()) {
//                    String filmeJson = filmeSnapshot.getValue(String.class);
//
//                    Gson gson = new Gson();
//                    Filme filme = gson.fromJson(filmeJson, Filme.class);
//
//                    todosFilmes.add(filme);
//                    System.out.println(todosFilmes);
//                }
//                LinearLayout horizontalLayout = findViewById(R.id.layoutFilmes);
//                FilmeAdapterListView filmeAdapter = new FilmeAdapterListView(HomeActivity.this,
//                        todosFilmes);
//                for (int i = 0; i < filmeAdapter.getCount(); i++) {
//                    View itemView = filmeAdapter.getView(i, null, horizontalLayout);
//                    horizontalLayout.addView(itemView);
//                }
//                System.out.println(todosFilmes);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                // Tratar erro, catch
//            }
//        });
        filmesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot filmeSnapshot : dataSnapshot.getChildren()) {
                    String filmeJson = filmeSnapshot.getValue(String.class);
                    Gson gson = new Gson();

                    Filme filme = gson.fromJson(filmeJson, Filme.class);
                    // System.out.println(filmeJson);
                    //System.out.println(filme.toString());
                    todosFilmes.add(filme);
                }
                LinearLayout horizontalLayout = findViewById(R.id.layoutFilmes);
                FilmeAdapterListView filmeAdapter = new FilmeAdapterListView(HomeActivity.this,
                        todosFilmes);
                for (int i = 0; i < filmeAdapter.getCount(); i++) {
                    View itemView = filmeAdapter.getView(i, null, horizontalLayout);
                    Filme filme = todosFilmes.get(i);
                    String filmeId = filme.getId();
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                            // Verifica se o filme está nos favoritos do usuário
//                                DatabaseReference userFavoritesRef = FirebaseDatabase.getInstance()
//                                        .getReference()
//                                        .child("favoritos")
//                                        .child(userId); // Substitua 'userId' pela ID do usuário atual
//
//                                userFavoritesRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                                    @Override
//                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                        if (dataSnapshot.hasChild(filmeId)) {
//                                            // O filme já está nos favoritos, então exclua-o
//                                            userFavoritesRef.child(filmeId).removeValue();
//                                            Toast.makeText(HomeActivity.this, "Filme retirado dos favoritos", Toast.LENGTH_SHORT).show();
//                                        } else {
//                                            // O filme não está nos favoritos, então adicione-o
//                                            String filmeJson = gson.toJson(filme);
//                                            userFavoritesRef.child(filmeId).setValue(filmeJson);
//                                            Toast.makeText(HomeActivity.this, "Filme adicionado aos favoritos", Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//
//                                    @Override
//                                    public void onCancelled(@NonNull DatabaseError databaseError) {
//                                        // Tratar erro, se necessário
//                                    }
//                                });

                        }
                    });
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