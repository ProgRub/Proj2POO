package com.mycompany.festivalcinema;

import java.util.ArrayList;

public class Edicao {

    private final int numEdicao;
    private final int ano;
    private final ArrayList<Filme> filmes;
    private final ArrayList<Premio> premios;
    private final ArrayList<Perito> peritos;

    protected Edicao(int numEdicao, int ano) {

        this.numEdicao = numEdicao;
        this.ano = ano;
        this.premios = new ArrayList<>(9);
        this.filmes = new ArrayList<>();
        this.peritos = new ArrayList<>();
        inserePremios();
    }

    @Override
    public String toString() {
        String Edicao;
        Edicao = "Edição: " + numEdicao + " | Ano: " + ano;

        return Edicao;
    }

    protected void inserePerito(Perito perito) {
        this.peritos.add(perito);
    }

    protected ArrayList<Perito> getPeritos() {
        return this.peritos;
    }

    protected final void inserePremios() {
        this.premios.add(new Premio("Melhor Ator Principal"));
        this.premios.add(new Premio("Melhor Atriz Principal"));
        this.premios.add(new Premio("Melhor Ator Secundário"));
        this.premios.add(new Premio("Melhor Atriz Secundária"));
        this.premios.add(new Premio("Melhor Filme"));
        this.premios.add(new Premio("Melhor Realizador"));
        this.premios.add(new Premio("Melhor Argumento"));
        this.premios.add(new Premio("Melhor Cinematografia"));
        this.premios.add(new Premio("Prémio Carreira"));
    }

    protected void imprimeFilmes() {
        System.out.println("FILMES:");
        if (filmes.isEmpty()) {
            System.out.println("! NÃO HÁ FILMES REGISTADOS !");
        } else {
            for (Filme filme : this.filmes) {
                System.out.println(filme);
            }
        }
    }

    protected void imprimePremios() {
        int indice = 0;
        while (indice < premios.size()) {
            System.out.println(premios.get(indice));
            indice++;
        }
    }

    protected void insereFilmes(Filme filme) {
        this.filmes.add(filme);
    }

    protected Filme indexOfByFilmName(String nome) {
        for (Filme f : this.filmes) {
            if (nome.equals(f.getNome())) {
                return f;
            }
        }
        return null;
    }

    protected int getNumEdicao() {
        return numEdicao;
    }

    protected ArrayList<Filme> getFilmes() {
        return this.filmes;
    }

    protected ArrayList<Premio> getPremios() {
        return this.premios;
    }
}
