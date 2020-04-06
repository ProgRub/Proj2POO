
import java.util.ArrayList;

public class Ator extends Pessoa {

    private int anosCarreira;
    private int filmes;
    private ArrayList<Filme> filmesParticipa;

    public Ator(String nome, boolean genero, int anosCarreira) {
        super(nome, genero);
        this.anosCarreira = anosCarreira;
        filmesParticipa = new ArrayList<Filme>(0);
    }

    /**
     *
     * @return true se o ator participa em menos de 2 filmes na edição atual,
     * false caso contrario
     */
    public boolean podeInserirFilme() {
        return filmes < 2;
    }

    /**
     * Método que guarda um filme no catalogo de filmes do ator
     *
     * @param filme - filme a inserir no catalogo de filmes do ator
     */
    public void inserirFilme(Filme filme) {
        this.filmesParticipa.add(filme);
        this.filmes++;
    }

    public String toString() {
        String ator = super.toString();
        ator += "Anos de Carreira: " + anosCarreira + "\n";
        ator += "Filmes em que participa: \n";
        for (int indice = 0; indice < filmesParticipa.size(); indice++) {
            ator += filmesParticipa.get(indice).getNome() + "\n";
            indice++;
        }
        return ator;
    }

}
