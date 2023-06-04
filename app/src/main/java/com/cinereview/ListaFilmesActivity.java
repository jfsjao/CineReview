package com.cinereview;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ListView;
import java.util.ArrayList;

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

        ArrayList<Filme> todosFilmes = new ArrayList<>();

//        Ator[] atores1 = {
//                new Ator("Tom Hanks", 65, "Americano", null),
//                new Ator("Emma Watson", 31, "Britânica", null),
//                new Ator("Robert Downey Jr.", 56, "Americano", null)
//        };
//
//        Diretor diretor1 = new Diretor("Christopher Nolan", 51, "Britânico", null);
//
//        Filme filme1 = new Filme.Builder("Inception")
//                .setGenero(new String[]{"Ação", "Ficção Científica"})
//                .setAtores(atores1)
//                .setDiretor(diretor1)
//                .setNota(8.8f)
//                .setDescricao("Dom Cobb é um habilidoso ladrão, o melhor na perigosa arte da extração: roubar segredos valiosos do inconsciente durante o sono das pessoas.")
//                .setCartaz("https://www.example.com/inception.jpg")
//                .setDataLancamento("16/07/2010")
//                .build();
//
//
//        todosFilmes.add(filme1);
//
//        Gson gson = new GsonBuilder().create();
//        String filmeJson = gson.toJson(filme1);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference filmesRef = database.getReference().child("filmes");
//        filmesRef.push().setValue(filmeJson);

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
                FilmeAdapterListView filmeAdapter = new FilmeAdapterListView(ListaFilmesActivity.this, todosFilmes);
                listView1.setAdapter(filmeAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Tratar erro, catch
            }
        });




    }
}