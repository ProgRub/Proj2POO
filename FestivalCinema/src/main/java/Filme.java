import java.util.ArrayList;

public class Filme {
    private final String nome;
    private final String genero;
    private final int edição;
    private final Realizador realizador;
    private ArrayList<Pessoa> atores;
    private int posição = 0;
    
    public Filme(String nome, String genero, int edição, Realizador realizador){
        this.nome = nome;
        this.genero = genero;
        this.edição = edição;
        this.realizador = realizador;
        atores = new ArrayList<Pessoa>(2);
    }
    
    
    public void insereAtor(Ator ator, int posição){
        if (posição < atores.size()){
            if((posição == 0 || posição == 1) && atores.get(posição) == null){
                atores.add(posição, ator);
            }
            else{
                System.out.println("OCUPADO!");
            }
        }
        else{
            atores.add(ator);
        }
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
                filme += atores.get(posição).getPrimeiroNome()+atores.get(posição).getUltimoNome()+"\n";
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
