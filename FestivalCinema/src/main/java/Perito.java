
public class Perito extends Pessoa {
    private int pontuação;
    
    public Perito(String primeiroNome, String ultimoNome, boolean genero){
        super(primeiroNome,ultimoNome,genero);
    }
    
    public String toString() {
        String perito;
        perito = "Nome: " + getPrimeiroNome() + " " + getUltimoNome() + "\n";
        perito += "Género: " + (getGenero() ? "Masculino" : "Feminino") + "\n";
        return perito;
    }
    
    public void inserePontuacao(int pontuacao){
        this.pontuação = pontuacao;
    }
    
    public int getPontuacao(){
        return this.pontuação;
    }
}
