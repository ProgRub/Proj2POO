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
        Edicao = "Edição: " + numEdicao + " Ano: " + ano + "\n";
        
        while(posicao < premios.size()){
            
            if (posicao==0){
                Edicao += "Prémio: Melhor filme \n";
            }
            
            if (posicao==1){
                Edicao += "Prémio: Melhor ator principal \n";
            }
            
            if (posicao==2){
                Edicao += "Prémio: Melhor atriz principal \n";
            }
            
            if (posicao==3){
                Edicao += "Prémio: Melhor ator secundário \n";
            }
            
            if (posicao==4){
                Edicao += "Prémio: Melhor atriz secundária \n";
            }
            
            if (posicao==5){
                Edicao += "Prémio: Melhor realizador \n";
            }
            
            if (posicao==6){
                Edicao += "Prémio: Melhor argumento \n";
            }
            
            if (posicao==7){
                Edicao += "Prémio: Melhor cinematografia \n";
            }
            
        }
      
        return Edicao; 
    }
    
    
}
    
