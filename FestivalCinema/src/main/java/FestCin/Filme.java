package FestCin;

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

    /**
     * Método que insere um ator no filme, se é principal ou secundário depende
     * da posição
     *
     * @param ator - ator a inserir no filme
     * @param posição - se for menor que 2 indica que pretende-se inserir o
     * ator/atriz como principal, caso contrário será secundário Se for
     * principal, a posição distingue se é ator ou atriz principal (0 - ator
     * principal, 1 - atriz principal) e só insere se não houver ator/atriz
     * principal
     */
    protected void insereAtor(Ator ator, int posição) {
        if (posição < 2) {
            if (posição == 0 && this.AtorPrincipal == null) {
                this.AtorPrincipal = ator;
                ator.inserirFilme(this); //insere o filme na lista de filmes em que o ator participa
            } else if (posição == 1 && this.AtrizPrincipal == null) {
                this.AtrizPrincipal = ator;
                ator.inserirFilme(this); //insere o filme na lista de filmes em que o atriz participa
            } else {
                System.out.println(this.nome + " já tem " + (ator.getGenero() ? " um ator principal!" : " uma atriz principal!"));
            }
        } else {
            if (ator == this.AtorPrincipal || ator == this.AtrizPrincipal) {
                System.out.println((ator.getGenero() ? "O ator" : "A atriz") + " já está no filme!");
                return;
            }
            for (Ator a : this.atoresSecundarios) {
                if (ator == a) {
                    System.out.println((ator.getGenero() ? "O ator" : "A atriz") + " já está no filme!");
                    return;
                }
            }
            this.atoresSecundarios.add(ator);
            ator.inserirFilme(this); //insere o filme na lista de filmes em que o ator/atriz participa
        }
    }

    protected String getNome() {
        return nome;
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

}
