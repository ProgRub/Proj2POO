import java.util.ArrayList;

public class Premio{
    private String nome;
    private ArrayList <Integer> pontuacoes;
    private ArrayList <Filme> filmes;
    private ArrayList <Pessoa> atores;
    
    public Premio(String nome){
        this.nome = nome;
        if(nome.contains("Ator")|| nome.contains("Atriz")){
            this.filmes = null;
        }
        else{
            this.atores = null;
        }
    }
    
    public void setFilmes(ArrayList <Filme> filmes){
        this.filmes = filmes;
    }
    
    public void setAtores(ArrayList<Pessoa> atores){
        this.atores = atores;
    }
    
    public String toString(){
        return nome;
    }
    
}


