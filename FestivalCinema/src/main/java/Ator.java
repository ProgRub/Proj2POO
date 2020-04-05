
import java.util.ArrayList;

public class Ator extends Pessoa {

    private int anosCarreira;
    private int filmes;
    private ArrayList<Filme> filmesParticipa;

    public Ator(String primeiroNome, String ultimoNome, boolean genero, int anosCarreira) {
        super(primeiroNome, ultimoNome, genero);
        this.anosCarreira = anosCarreira;
        filmesParticipa = new ArrayList<Filme>(0);
    }

    public void insereFilme(Filme filme){
        if(filmes < 2){
            this.filmesParticipa.add(filme);
            this.filmes++;
        }
        else{
           System.out.println("Não pode participar em mais nenhum filme!");
        }
    }

    public String toString() {
        String ator;
        int posição = 0;
        ator = "Nome: " + getPrimeiroNome() + " " + getUltimoNome() + "\n";
        ator += "Género: " + (getGenero() ? "Masculino" : "Feminino") + "\n";
        ator += "Anos de Carreira: " + anosCarreira + "\n";
        ator += "Filmes em que participa: \n";
        while (posição < filmesParticipa.size()) {
            ator += filmesParticipa.get(posição).getNome() + "\n";
            posição++;
        }
        posição = 0;
        return ator;
    }

}
