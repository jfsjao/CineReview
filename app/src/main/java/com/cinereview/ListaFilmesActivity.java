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

        Ator[] atores1 = {
                new Ator("Tom Hanks", 65, "Americano", "https://upload.wikimedia.org/wikipedia/commons/5/5c/Ridley_Scott_by_Gage_Skidmore.jpg"),
                new Ator("Emma Watson", 31, "Britânica", "https://upload.wikimedia.org/wikipedia/commons/5/5c/Ridley_Scott_by_Gage_Skidmore.jpg"),
                new Ator("Robert Downey Jr.", 56, "Americano", "https://upload.wikimedia.org/wikipedia/commons/5/5c/Ridley_Scott_by_Gage_Skidmore.jpg")
        };

        Diretor diretor1 = new Diretor("Christopher Nolan", 51, "Britânico", "");

        Filme filme1 = new Filme.Builder("Inception")
                .setId("001") // ID do filme
                .setGenero(new String[]{"Ação", "Ficção Científica"})
                .setAtores(atores1)
                .setDiretor(diretor1)
                .setNota(8.8f)
                .setDescricao("Dom Cobb é um habilidoso ladrão, o melhor na perigosa arte da extração: roubar segredos valiosos do inconsciente durante o sono das pessoas.")
                .setCartaz("https://upload.wikimedia.org/wikipedia/pt/e/e7/Martian_poster_2015.jpg")
                .setDataLancamento("16/07/2010")
                .build();

        todosFilmes.add(filme1);

        Gson gson = new GsonBuilder().create();
        String filmeJson = gson.toJson(filme1);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference filmesRef = database.getReference().child("filmes");
        filmesRef.child(filme1.getId()).setValue(filmeJson);

        Ator[] atores2 = {
                new Ator("Leonardo DiCaprio", 47, "Americano", ""),
                new Ator("Joseph Gordon-Levitt", 40, "Americano", ""),
                new Ator("Elliot Page", 35, "Canadense", "")
        };

        Diretor diretor2 = new Diretor("Quentin Tarantino", 58, "Americano", "");

        Filme filme2 = new Filme.Builder("Pulp Fiction")
                .setId("002")
                .setGenero(new String[]{"Crime", "Drama"})
                .setAtores(atores2)
                .setDiretor(diretor2)
                .setNota(8.9f)
                .setDescricao("Vincent Vega e Jules Winnfield são dois assassinos profissionais que trabalham fazendo cobranças para Marsellus Wallace, um poderosos gângster.")
                .setCartaz("https://www.example.com/pulpfiction.jpg")
                .setDataLancamento("21/10/1994")
                .build();

        todosFilmes.add(filme2);

        String filmeJson2 = gson.toJson(filme2);
        filmesRef.child(filme2.getId()).setValue(filmeJson2);

        Ator[] atores3 = {
                new Ator("Brad Pitt", 58, "Americano", ""),
                new Ator("Edward Norton", 52, "Americano", ""),
                new Ator("Helena Bonham Carter", 55, "Britânica", "")
        };

        Diretor diretor3 = new Diretor("David Fincher", 59, "Americano", "");

        Filme filme3 = new Filme.Builder("Clube da Luta")
                .setId("003")
                .setGenero(new String[]{"Drama"})
                .setAtores(atores3)
                .setDiretor(diretor3)
                .setNota(8.8f)
                .setDescricao("Jack é um executivo jovem, trabalha como investigador de seguros, mora confortavelmente, mas ele está ficando cada vez mais insatisfeito com sua vida medíocre.")
                .setCartaz("https://www.example.com/clube-da-luta.jpg")
                .setDataLancamento("15/10/1999")
                .build();

        todosFilmes.add(filme3);

        String filmeJson3 = gson.toJson(filme3);
        filmesRef.child(filme3.getId()).setValue(filmeJson3);

        Ator[] atores4 = {
                new Ator("Meryl Streep", 72, "Americana", ""),
                new Ator("Anne Hathaway", 39, "Americana", ""),
                new Ator("Emily Blunt", 39, "Britânica", "")
        };

        Diretor diretor4 = new Diretor("David Frankel", 63, "Americano", "");

        Filme filme4 = new Filme.Builder("O Diabo Veste Prada")
                .setId("004")
                .setGenero(new String[]{"Comédia", "Drama"})
                .setAtores(atores4)
                .setDiretor(diretor4)
                .setNota(7.5f)
                .setDescricao("Uma jovem jornalista recém-formada consegue um emprego como assistente de uma influente editora de moda, mas logo descobre que o trabalho não é tão glamouroso quanto imaginava.")
                .setCartaz("https://www.example.com/diabo-veste-prada.jpg")
                .setDataLancamento("29/09/2006")
                .build();

        todosFilmes.add(filme4);

        String filmeJson4 = gson.toJson(filme4);
        filmesRef.child(filme4.getId()).setValue(filmeJson4);

        Ator[] atores5 = {
                new Ator("Denzel Washington", 67, "Americano", ""),
                new Ator("Ethan Hawke", 52, "Americano", ""),
                new Ator("Scott Glenn", 82, "Americano", "")
        };

        Diretor diretor5 = new Diretor("Antoine Fuqua", 56, "Americano", "");

        Filme filme5 = new Filme.Builder("Dia de Treinamento")
                .setId("005")
                .setGenero(new String[]{"Crime", "Drama"})
                .setAtores(atores5)
                .setDiretor(diretor5)
                .setNota(7.7f)
                .setDescricao("Um jovem policial novato é designado para trabalhar junto com um detetive experiente e corrupto em seu primeiro dia na unidade de combate ao narcotráfico.")
                .setCartaz("https://www.example.com/dia-de-treinamento.jpg")
                .setDataLancamento("26/10/2001")
                .build();

        todosFilmes.add(filme5);

        String filmeJson5 = gson.toJson(filme5);
        filmesRef.child(filme5.getId()).setValue(filmeJson5);




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
                FilmeAdapterListView filmeAdapter =
                        new FilmeAdapterListView(ListaFilmesActivity.this, todosFilmes);
                listView1.setAdapter(filmeAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Tratar erro, catch
            }
        });

    }
}