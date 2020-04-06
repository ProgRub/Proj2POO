
import java.util.ArrayList;

public class Filme {

    private final String nome;
    private final String genero;
    private final int edição;
    private final Realizador realizador;
    private Pessoa AtorPrincipal;
    private Pessoa AtrizPrincipal;
    private ArrayList<Pessoa> atoresSecundarios;

    public Filme(String nome, String genero, int edição, Realizador realizador) {
        this.nome = nome;
        this.genero = genero;
        this.edição = edição;
        this.realizador = realizador;
        this.AtorPrincipal = null;
        this.AtrizPrincipal = null;
        this.atoresSecundarios = new ArrayList<Pessoa>(0);
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
    public void insereAtor(Ator ator, int posição) {
        if (posição < 2) {
            if (posição == 0 && this.AtorPrincipal == null) {
                this.AtorPrincipal = ator;
            } else if (posição == 1 && this.AtrizPrincipal == null) {
                this.AtrizPrincipal = ator;
            } else {
                System.out.println("Já existe!");
            }
        } else {
            this.atoresSecundarios.add(ator);
        }
    }

    public String getNome() {
        return nome;
    }

    public Pessoa getAtorPrincipal() {
        return AtorPrincipal;
    }

    public Pessoa getAtrizPrincipal() {
        return AtrizPrincipal;
    }

    public ArrayList<Pessoa> getAtoresSecundarios() {
        return atoresSecundarios;
    }

    public Realizador getRealizador() {
        return realizador;
    }

    public String toString() {
        String filme;
        filme = "Filme: " + nome + "\n";
        filme += "Género: " + genero + "\n";
        filme += "Realizador: " + realizador.getNome() + "\n";
        filme += "Ator Principal: " + (AtorPrincipal != null ? AtorPrincipal.getNome() : "") + "\n";
        filme += "Atriz Principal: " + (AtrizPrincipal != null ? AtrizPrincipal.getNome() : "") + "\n";
        filme += "Atores Secundários:\n";
        for (int indice = 0; indice < atoresSecundarios.size(); indice++) {
            filme += atoresSecundarios.get(indice).getNome() + "\n";
        }
        return filme;
    }

}
