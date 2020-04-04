
public class Pessoa {

    private final String primeiroNome;
    private final String ultimoNome;
    private final boolean genero; //true->Homem false->Mulher

    public Pessoa(String primeiroNome, String ultimoNome, boolean genero) {
        this.primeiroNome = primeiroNome;
        this.ultimoNome = ultimoNome;
        this.genero = genero;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public String getUltimoNome() {
        return ultimoNome;
    }

    public boolean getGenero(){
          return genero;
    }
   
}