
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

    public boolean podeInserirFilme() {
        return filmes < 2;
    }

    public void inserirFilme(Filme filme) {
        this.filmesParticipa.add(filme);
        this.filmes++;
    }

    public String toString() {
        String ator;
        int posição = 0;
        ator = "Nome: " + getNome() + "\n";
        ator += "Género: " + (getGenero() ? "Masculino" : "Feminino") + "\n";
        ator += "Anos de Carreira: " + anosCarreira + "\n";
        ator += "Filmes em que participa: \n";
        while (posição < filmesParticipa.size()) {
            ator += filmesParticipa.get(posição).getNome() + "\n";
            posição++;
        }
        return ator;
    }

}
