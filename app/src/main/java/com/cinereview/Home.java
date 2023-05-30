package com.cinereview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_principal);

        //remove o titulo
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setBackgroundDrawable(null);

        // Remover a barra de status
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener((item) -> {
            // Lógica para tratar a seleção de itens da BottomNavigationView
            return true;
        });
        ListarFilmes();
    }

    public void ListarFilmes(){
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

        RecyclerView recyclerViewFilmes = findViewById(R.id.recyclerViewFilmes);
        FilmeAdapter filmeAdapter = new FilmeAdapter(Home.this,listaFilmes);
        recyclerViewFilmes.setAdapter(filmeAdapter);
        recyclerViewFilmes.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }
}
