
import java.util.ArrayList;

public class Ator extends Pessoa {

    private int anosCarreira;
    private int numFilmesEdiçãoAtual;
    private int numFilmesTotal;
    private ArrayList<Filme> filmesParticipa;
    String anosCarreiraS;

    public Ator(String nome, boolean genero, int anosCarreira) {
        super(nome, genero);
        this.anosCarreira = anosCarreira;
        this.numFilmesTotal = 0;
        this.numFilmesEdiçãoAtual = 0;
        filmesParticipa = new ArrayList<Filme>(0);
    }
    
    public Ator(String nome, boolean genero, String anosCarreiraS) {
        super(nome, genero);
        this.anosCarreiraS = anosCarreiraS;
        this.numFilmesTotal = 0;
        this.numFilmesEdiçãoAtual = 0;
        filmesParticipa = new ArrayList<Filme>(0);
    }

    public void resetNumFilmesEdicaoAtual() {
        this.numFilmesEdiçãoAtual = 0;
    }

    public void incrementaAnosCarreira() {
        this.anosCarreira++;
    }

    /**
     *
     * @return true se o ator participa em menos de 2 filmes na edição atual,
     * false caso contrario
     */
    public boolean podeInserirFilme() {
        return numFilmesEdiçãoAtual < 2;
    }

    /**
     * Método que guarda um filme no catalogo de filmes do ator
     *
     * @param filme - filme a inserir no catalogo de filmes do ator
     */
    public void inserirFilme(Filme filme) {
        this.filmesParticipa.add(filme);
        this.numFilmesEdiçãoAtual++;
        this.numFilmesTotal++;
    }

    public int getAnosCarreira() {
        return anosCarreira;
    }
    
    public ArrayList<Filme> getFilmes() {
        return filmesParticipa;
    }

    public String toString() {
        String ator = super.toString();
        ator += "Anos de Carreira: " + (anosCarreira==0 ? anosCarreiraS : anosCarreira) + "\n";
        ator += "Filmes em que participa: \n";
        for (int indice = 0; indice < filmesParticipa.size(); indice++) {
            ator += filmesParticipa.get(indice).getNome() + "\n";
            indice++;
        }
        return ator;
    }

    public boolean equals(Ator a, Ator b) {
        return (a.getNome().equals(b.getNome())) && (a.getGenero() == b.getGenero()) && (a.numFilmesEdiçãoAtual == b.numFilmesEdiçãoAtual) && (a.numFilmesTotal == b.numFilmesTotal) && (a.anosCarreira == b.anosCarreira);
    }
}
