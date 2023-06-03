package com.cinereview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ListaFilmesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_filmes);


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