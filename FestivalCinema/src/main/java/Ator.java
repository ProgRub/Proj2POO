
import java.util.ArrayList;
import java.util.Objects;

public class Ator extends Pessoa {

    private int anosCarreira;
    private int numFilmesEdiçãoAtual;
    private int numFilmesTotal;
    private ArrayList<Filme> filmesParticipa;

    public Ator(String nome, boolean genero, int anosCarreira) {
        super(nome, genero);
        this.anosCarreira = anosCarreira;
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
        ator += "Anos de Carreira: " + anosCarreira + "\n";
        ator += "Filmes em que participa: \n";
        for (Filme f : this.filmesParticipa) {
            ator += f.getNome() + "\n";
        }
        return ator;
    }

//    @Override
//    public boolean equals(Object obj) {
//        System.out.println("CHAMADO");
//        if (obj == null) {
//            return false;
//        }
//        if (obj == this) {
//            return true;
//        }
//        if (!(obj instanceof Ator)) {
//            return false;
//        }
//        Ator comparar = (Ator)obj;
//        System.out.println(this.anosCarreira == comparar.anosCarreira && this.getGenero() == comparar.getGenero() && this.getNome().equalsIgnoreCase(comparar.getNome()));
//        return this.anosCarreira == comparar.anosCarreira && this.getGenero() == comparar.getGenero() && this.getNome().equalsIgnoreCase(comparar.getNome());
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 3;
//        hash = 13 * hash + this.anosCarreira;
//        hash = 13 * hash + this.numFilmesEdiçãoAtual;
//        hash = 13 * hash + this.numFilmesTotal;
//        hash = 13 * hash + Objects.hashCode(this.filmesParticipa);
//        return hash;
//    }
}
