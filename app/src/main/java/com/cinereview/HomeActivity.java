package com.cinereview;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;
public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerViewMyList;
    private RecyclerView recyclerViewRecomendation;
    private RecyclerView recyclerViewCelebs;

    private FilmeAdapter myListAdapter;
    private FilmeAdapter recomendationAdapter;
    private FilmeAdapter celebsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Remove o título
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().setBackgroundDrawable(null);

        // Remover a barra de status
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener((item) -> {
            // Lógica para tratar a seleção de itens da BottomNavigationView

                // Navegar para a tela da lista
                Intent intent = new Intent(HomeActivity.this, ListaFilmesActivity.class);
                startActivity(intent);
                return true;

        });




        recyclerViewMyList = findViewById(R.id.recyclerView_myList);
        recyclerViewMyList.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewRecomendation = findViewById(R.id.recyclerView_Recomendation);
        recyclerViewRecomendation.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewCelebs = findViewById(R.id.recyclerViewcelebs);
        recyclerViewCelebs.setLayoutManager(new LinearLayoutManager(this));

    }

//    public void listarFilmes() {
//        ArrayList<Filme> listaFilmes = new ArrayList<>();
//
//        Ator[] atores = {
//                new Ator("Ator 1", 23, "Brasileiro", null),
//                new Ator("Ator 2", 23, "Brasileiro", null),
//                new Ator("Ator 3", 23, "Brasileiro", null)
//        };
//
//        Diretor diretor = new Diretor("Diretor 1", 23, "Brasileiro", null);
//
//        Filme filme1 = new Filme.Builder("Filme 1")
//                .setGenero(new String[]{"Ação", "Drama"})
//                .setAtores(atores)
//                .setDiretor(diretor)
//                .setNota(8.5f)
//                .setDescricao("Descrição do Filme 1")
//                .build();
//
//        listaFilmes.add(filme1);
//        listaFilmes.add(filme1);
//        listaFilmes.add(filme1);
//
//        myListAdapter = new FilmeAdapter(this, listaFilmes);
//        recyclerViewMyList.setAdapter(myListAdapter);
//
//        recomendationAdapter = new FilmeAdapter(this, listaFilmes);
//        recyclerViewRecomendation.setAdapter(recomendationAdapter);
//
//        celebsAdapter = new FilmeAdapter(this, listaFilmes);
//        recyclerViewCelebs.setAdapter(celebsAdapter);
//    }
}