
public class Pessoa {

    private final String nome;
    private final boolean genero; //true->Homem false->Mulher

    public Pessoa(String nome, boolean genero) {
        this.nome = nome;
        this.genero = genero;
    }

    public String getNome() {
        return nome;
    }
    
    public boolean getGenero(){
          return genero;
    }
   
}
