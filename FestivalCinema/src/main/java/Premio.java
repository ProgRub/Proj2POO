import java.util.ArrayList;

public class Premio{
    private String nome;
    private ArrayList <Integer> pontuacoes;
    private ArrayList <Filme> filmes;
    private ArrayList <Pessoa> atores;
    
    public Premio(String nome){
        this.nome = nome;
        if(nome.contains("Ator")|| nome.contains("Atriz") || nome.contains("Carreira")){
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
    
    public ArrayList <Pessoa> getAtoresCandidatos(){
        return atores;
    }
    
     public ArrayList <Filme> getFilmesCandidatos(){
        return filmes;
    }
    
    public String toString(){
        return nome;
    }
    
}


