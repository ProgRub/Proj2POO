import java.util.ArrayList;
        
public class Edicao {
    private int numEdicao;
    private int ano;
    ArrayList <Filme> filmes;
    ArrayList <String> premios;   
    private int posicao = 0;


    public Edicao(int numEdicao, int ano,ArrayList <Premio> premios ){

    this.numEdicao = numEdicao;
    this.ano = ano;
    this.premios = premios;
    }
    
    public String toString(){
        String Edicao;
        Edicao = "Edição: " + numEdicao + " Ano: " + ano + "\n";
        
        return Edicao; 
    }
    
    public void inserePremios(){
        
        premios.add("Melhor filme");
        premios.add("Melhor ator principal");
        premios.add("Melhor atriz principal");
        premios.add("Melhor ator secundário");
        premios.add("Melhor atriz secundária");
        premios.add("Melhor realizador");
        premios.add("Melhor argumento");
        premios.add("Melhor cinematografia");
    }
}
    
