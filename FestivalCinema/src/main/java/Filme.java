import java.util.ArrayList;

public class Filme {
    private String nome;
    private String genero;
    private int edição;
    private Pessoa realizador;
    private ArrayList<Pessoa> atores;
    private int posição = 0;
    
    public Filme(String nome, String genero, int edição, Pessoa realizador, ArrayList<Pessoa> atores){
        this.nome = nome;
        this.genero = genero;
        this.edição = edição;
        this.realizador = realizador;
        this.atores = atores;
    }
    
    public String toString(){
        String filme;
        filme = "Filme: "+nome+"\n";
        filme += "Género: "+genero+"\n";
        filme += "Edição: "+edição+"\n";
        filme += "Realizador: "+"GET NOME PESSOA"+"\n"; //falta fazer
        while(posição < atores.size()){
            if(posição == 0){
                 filme += "Ator Principal: "+"GET NOME ATOR"+"\n";
            }
            else if(posição == 1){
                 filme += "Atriz Principal: "+"GET NOME ATOR"+"\n";
            }
            else if (posição == 2){
                filme += "Atores Secundários: \n";
                filme += "GET NOME ATOR \n";
            }
            else{
                filme += "GET NOME ATOR \n";
            }
            posição++;
        }
        
        return filme;
    }
    
}
