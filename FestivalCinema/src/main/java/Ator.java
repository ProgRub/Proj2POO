import java.util.ArrayList;

public class Ator extends Pessoa{
    private int anosCarreira;
    private int filmes;
    private ArrayList<Filme> filmesParticipa;
    private int posição = 0;
    
    public Ator(String primeiroNome, String ultimoNome, String genero,int anosCarreira, int filmes){
        super(primeiroNome, ultimoNome,genero);
        this.anosCarreira = anosCarreira;
        this.filmes = filmes;
    }
    
    public void insereFilme(Filme filme){
        this.filmesParticipa.add(filme);
    }
    
    public String toString(){
        String ator;
        ator = "Nome: "+getPrimeiroNome()+" "+getUltimoNome()+"\n";
        ator += "Género: "+getGenero()+"\n";
        ator += "Anos de Carreira: "+anosCarreira+"\n";
        while(posição < filmesParticipa.size()){
            if(posição == 0){
                 ator += "Filmes em que participa: \n";
            }
            ator +=  filmesParticipa.get(posição).getNome()+"\n";
            posição++;
        }
        posição = 0;
        return ator;
    }
    
}
