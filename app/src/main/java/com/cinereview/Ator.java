package com.cinereview;

import android.graphics.Bitmap;

public class Ator {
    private String nome;
    private int idade;
    private String nacionalidade;
    private String foto;

    public Ator(String nome, int idade, String nacionalidade, String foto) {
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
