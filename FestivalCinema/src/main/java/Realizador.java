
public class Realizador extends Pessoa {

    public Realizador(String primeiroNome, String ultimoNome, boolean genero) {
        super(primeiroNome, ultimoNome, genero);
    }
    
    public String toString() {
        String realizador;
        realizador = "Nome: " + getPrimeiroNome() + " " + getUltimoNome() + "\n";
        realizador += "Género: " + (getGenero() ? "Masculino" : "Feminino") + "\n";
        return realizador;
    }
}
