package com.cinereview;

import android.graphics.Bitmap;

public class Diretor {
    private String nome;
    private int idade;
    private String nacionalidade;
    private Bitmap foto;

    public Diretor(String nome, int idade, String nacionalidade, Bitmap foto) {
        this.nome = nome;
        this.idade = idade;
        this.nacionalidade = nacionalidade;
        this.foto = foto;
    }

    // Getters e setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }
}

