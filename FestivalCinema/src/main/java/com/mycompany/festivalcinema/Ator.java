package com.mycompany.festivalcinema;

import java.util.ArrayList;

public class Ator extends Pessoa {

    private int anosCarreira;
    private int numFilmesEdiçãoAtual;
    private int numFilmesTotal;
    private final ArrayList<Filme> filmesParticipa;

    protected Ator(String nome, boolean genero, int anosCarreira) {
        super(nome, genero);
        this.anosCarreira = anosCarreira;
        this.numFilmesTotal = 0;
        this.numFilmesEdiçãoAtual = 0;
        filmesParticipa = new ArrayList<>(0);
    }

    protected void resetNumFilmesEdicaoAtual() {
        this.numFilmesEdiçãoAtual = 0;
    }

    protected void incrementaAnosCarreira() {
        this.anosCarreira++;
    }

    /**
     *
     * @return true se o ator participa em menos de 2 filmes na edição atual,
     * false caso contrario
     */
    protected boolean podeInserirFilme() {
        return numFilmesEdiçãoAtual < 2;
    }

    /**
     * Método que guarda um filme no catalogo de filmes do ator e atualiza as
     * variáveis que contam os filmes
     *
     * @param filme - filme a inserir no catalogo de filmes do ator
     */
    protected void inserirFilme(Filme filme) {
        this.filmesParticipa.add(filme);
        this.numFilmesEdiçãoAtual++;
        this.numFilmesTotal++;
    }

    protected int getAnosCarreira() {
        return anosCarreira;
    }

    protected ArrayList<Filme> getFilmes() {
        return filmesParticipa;
    }

    protected int getnumFilmesEdiçãoAtual() {
        return numFilmesEdiçãoAtual;
    }

    @Override
    public String toString() {
        String ator = super.toString();
        ator += "Anos de Carreira: " + anosCarreira + "\n";
        ator += "Filmes em que participa: \n";
        for (Filme f : this.filmesParticipa) {
            ator += f.getNome() + "\n";
        }
        return ator;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Ator)) {
            return false;
        }
        Ator comparar = (Ator) obj;
        return this.anosCarreira == comparar.anosCarreira && this.getGenero() == comparar.getGenero() && this.getNome().equalsIgnoreCase(comparar.getNome());
    }
}
