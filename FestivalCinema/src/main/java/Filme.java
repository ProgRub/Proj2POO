import java.util.ArrayList;

public class Filme {
    private String nome;
    private String genero;
    private int edição;
    private Realizador realizador;
    private ArrayList<Pessoa> atores;
    private int posição = 0;
    
    public Filme(String nome, String genero, int edição, Realizador realizador){
        this.nome = nome;
        this.genero = genero;
        this.edição = edição;
        this.realizador = realizador;
    }
    
    public void insereAtor(Ator ator){
        this.atores.add(ator);
    }
    
    public String getNome(){
        return nome;
    }
    
    public String toString(){
        String filme;
        filme = "Filme: "+nome+"\n";
        filme += "Género: "+genero+"\n";
        filme += "Edição: "+edição+"\n";
        filme += "Realizador: "+realizador.getPrimeiroNome()+" "+realizador.getUltimoNome()+"\n";
        while(posição < atores.size()){
            if(posição == 0){
                 filme += "Ator Principal: "+atores.get(0).getPrimeiroNome()+atores.get(0).getUltimoNome()+"\n";
            }
            else if(posição == 1){
                 filme += "Atriz Principal: "+atores.get(1).getPrimeiroNome()+atores.get(1).getUltimoNome()+"\n";
            }
            else if (posição == 2){
                filme += "Atores Secundários: \n";
                filme += atores.get(2).getPrimeiroNome()+atores.get(2).getUltimoNome()+"\n";
            }
            else{
                filme += atores.get(posição).getPrimeiroNome()+atores.get(posição).getUltimoNome()+"\n";
            }
            posição++;
        }
        posição = 0;
        
        return filme;
    }
    
}
