package com.cinereview;

import android.graphics.Bitmap;
import java.util.Date;

public class Filme {
    String nome;
    String[] genero;
    Ator[] atores;
    Diretor diretor;
    float nota;
    String descricao;
    Bitmap cartaz;
    Date dataLancamento;

    public Filme(Builder builder) {
        this.setNome(builder.nome);
        this.genero = builder.genero;
        this.atores = builder.atores;
        this.diretor = builder.diretor;
        this.nota = builder.nota;
        this.descricao = builder.descricao;
        this.cartaz = builder.cartaz;
        this.dataLancamento = builder.dataLancamento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static class Builder {
        private String nome;
        private String[] genero;
        private Ator[] atores;
        private Diretor diretor;
        private float nota;
        private String descricao;
        private Bitmap cartaz;
        private Date dataLancamento;

        public Builder(String nome) {
            this.nome = nome;
        }

        public Builder setGenero(String[] genero) {
            this.genero = genero;
            return this;
        }

        public Builder setAtores(Ator[] atores) {
            this.atores = atores;
            return this;
        }

        public Builder setDiretor(Diretor diretor) {
            this.diretor = diretor;
            return this;
        }

        public Builder setNota(float nota) {
            this.nota = nota;
            return this;
        }

        public Builder setDescricao(String descricao) {
            this.descricao = descricao;
            return this;
        }

        public Builder setCartaz(Bitmap cartaz) {
            this.cartaz = cartaz;
            return this;
        }

        public Builder setDataLancamento(Date dataLancamento) {
            this.dataLancamento = dataLancamento;
            return this;
        }

        public Filme build() {
            return new Filme(this);
        }
    }

    // Getters para os atributos

    public String getNome() {
        return nome;
    }

    public String[] getGenero() {
        return genero;
    }

    public Ator[] getAtores() {
        return atores;
    }

    public Diretor getDiretor() {
        return diretor;
    }

    public float getNota() {
        return nota;
    }

    public String getDescricao() {
        return descricao;
    }

    public Bitmap getCartaz() {
        return cartaz;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }
}

