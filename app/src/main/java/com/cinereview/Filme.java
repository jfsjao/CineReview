package com.cinereview;

public class Filme {
    private String nome;
    private String[] genero;
    private Ator[] atores;
    private Diretor diretor;
    private float nota;
    private String descricao;
    private String cartaz;
    private String dataLancamento;

    private Filme(Builder builder) {
        this.nome = builder.nome;
        this.genero = builder.genero;
        this.atores = builder.atores;
        this.diretor = builder.diretor;
        this.nota = builder.nota;
        this.descricao = builder.descricao;
        this.cartaz = builder.cartaz;
        this.dataLancamento = builder.dataLancamento;
    }

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

    public String getCartaz() {
        return cartaz;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public static class Builder {
        private String nome;
        private String[] genero;
        private Ator[] atores;
        private Diretor diretor;
        private float nota;
        private String descricao;
        private String cartaz;
        private String dataLancamento;

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

        public Builder setCartaz(String cartaz) {
            this.cartaz = cartaz;
            return this;
        }

        public Builder setDataLancamento(String dataLancamento) {
            this.dataLancamento = dataLancamento;
            return this;
        }

        public Filme build() {
            return new Filme(this);
        }
    }
}
