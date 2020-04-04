import java.util.ArrayList;
        
public class Edicao {
    
    private int numEdicao;
    private int ano;
    ArrayList <Filme> filmes;
    ArrayList <Premio> premios;   
    private int posicao = 0;


    public Edicao(int numEdicao, int ano,ArrayList <Premio> premios ){

    this.numEdicao = numEdicao;
    this.ano = ano;
    this.premios = premios;
    }
    
    public String toString(){
        String Edicao;
        Edicao = "Edição: " + numEdicao + " Ano: " + ano;
 
        return Edicao; 
    }
}
    
