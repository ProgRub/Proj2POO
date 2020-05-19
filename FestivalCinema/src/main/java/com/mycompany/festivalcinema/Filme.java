package com.mycompany.festivalcinema;

import java.util.ArrayList;

public class Filme {

    private final String nome;
    private final String genero;
    private final int edição;
    private int numeroPremios;
    private final Realizador realizador;
    private Ator AtorPrincipal;
    private Ator AtrizPrincipal;
    private final ArrayList<Ator> atoresSecundarios;

    protected Filme(String nome, String genero, int edição, Realizador realizador) {
        this.nome = nome;
        this.genero = genero;
        this.edição = edição;
        this.realizador = realizador;
        this.AtorPrincipal = null;
        this.AtrizPrincipal = null;
        this.atoresSecundarios = new ArrayList<>(0);
        this.numeroPremios = 0;
    }

    protected String getNome() {
        return nome;
    }

    protected String getGenero() {
        return genero;
    }

    protected Ator getAtorPrincipal() {
        return AtorPrincipal;
    }

    protected Ator getAtrizPrincipal() {
        return AtrizPrincipal;
    }

    protected ArrayList<Ator> getAtoresSecundarios() {
        return atoresSecundarios;
    }

    protected Realizador getRealizador() {
        return realizador;
    }

    protected int getNumeroPremios() {
        return numeroPremios;
    }

    protected void incrementaNumeroPremios() {
        numeroPremios++;
    }

    /**
     * Método que insere um ator no filme, se é principal ou secundário depende
     * da posição
     *
     * @param ator - ator que se vai inserir no filme, se possível
     * @param principal - indica se o ator é suposto ser principal ou secundário
     * (true - principal, false - secundário)
     */
    protected void insereAtor(Ator ator, boolean principal) {
        if (principal) {
            for (Ator a : this.atoresSecundarios) {
                if (ator == a) { //verifica se o ator já está na lista de atores secundários
                    System.out.println((ator.getGenero() ? "O ator" : "A atriz") + " já está no filme!");
                    return;
                }
            }
            if (ator.getGenero() && this.AtorPrincipal == null) {
                this.AtorPrincipal = ator;
                ator.inserirFilme(this); //insere o filme na lista de filmes em que o ator participa
            } else if (!ator.getGenero() && this.AtrizPrincipal == null) {
                this.AtrizPrincipal = ator;
                ator.inserirFilme(this); //insere o filme na lista de filmes em que o atriz participa
            } else {
                System.out.println(this.nome + " já tem " + (ator.getGenero() ? " um ator principal!" : " uma atriz principal!"));
            }
        } else {
            if (ator == this.AtorPrincipal || ator == this.AtrizPrincipal) { //evita que o ator seja inserido como secundário se já é principal
                System.out.println((ator.getGenero() ? "O ator" : "A atriz") + " já está no filme!");
                return;
            }
            for (Ator a : this.atoresSecundarios) {
                if (ator == a) { //verifica se o ator já está na lista de atores secundários
                    System.out.println((ator.getGenero() ? "O ator" : "A atriz") + " já está no filme!");
                    return;
                }
            }
            this.atoresSecundarios.add(ator);
            ator.inserirFilme(this); //insere o filme na lista de filmes em que o ator/atriz participa
        }
    }

    @Override
    public String toString() {
        String filme;
        filme = "Filme: " + nome + "\n";
        filme += "Género: " + genero + "\n";
        filme += "Realizador: " + realizador.getNome() + "\n";
        filme += "Ator Principal: " + (AtorPrincipal != null ? AtorPrincipal.getNome() : "") + "\n";
        filme += "Atriz Principal: " + (AtrizPrincipal != null ? AtrizPrincipal.getNome() : "") + "\n";
        filme += "Atores Secundários:\n";
        for (Ator secundario : this.atoresSecundarios) {
            filme += secundario.getNome() + "\n";
        }
        return filme;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Filme filme = (Filme) obj;
        return this.nome.equalsIgnoreCase(filme.nome) && this.genero.equalsIgnoreCase(filme.genero) && this.realizador.equals(filme.realizador);
    }
}
