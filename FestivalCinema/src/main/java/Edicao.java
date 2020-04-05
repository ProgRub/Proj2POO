import java.util.ArrayList;
        
public class Edicao {
    private final int numEdicao;
    private final int ano;
    private final ArrayList <Filme> filmes;
    private final ArrayList <String> premios;   
    private int posicao;
    private int posicao1;


    
    public Edicao(int numEdicao, int ano){

    this.numEdicao = numEdicao;
    this.ano = ano;
    premios = new ArrayList <String>();
    filmes = new ArrayList <Filme>();
    posicao = 0; 
    posicao1 = 0;
    inserePremios();
    }
    
    public String toString(){
        String Edicao;
        Edicao = "Edição: " + numEdicao + " | Ano: " + ano;
        
        return Edicao; 
    }
    
    public final void inserePremios(){
        premios.add("Melhor filme");
        premios.add("Melhor ator principal");
        premios.add("Melhor atriz principal");
        premios.add("Melhor ator secundário");
        premios.add("Melhor atriz secundária");
        premios.add("Melhor realizador");
        premios.add("Melhor argumento");
        premios.add("Melhor cinematografia");
    }
    
    public void imprimeFilmes(){
        System.out.println("FILMES:");
        if (filmes.isEmpty()){
            System.out.println("! NÃO HÁ FILMES REGISTADOS !");
        }
        else{
            while (posicao < filmes.size()){
            System.out.println(filmes.get(posicao));
            posicao++;  
            }
        } 
    }
    
    public void imprimePremios(){
        System.out.println("PRÉMIOS:");
        while (posicao1 < premios.size()){
            System.out.println(premios.get(posicao1));
            posicao1++;
        }
    }
    
    public void insereFilmes(Filme filme){
        filmes.add(filme);   
    }
    
    public int getNumEdicao(){
        return numEdicao;
    }
    
}
    
