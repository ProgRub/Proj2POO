
public class Realizador extends Pessoa {

    public Realizador(String nome, boolean genero) {
        super(nome, genero);
    }

    public String toString() {
        String realizador;
        realizador = "Nome: " + getNome() + "\n";
        realizador += "Género: " + (getGenero() ? "Masculino" : "Feminino") + "\n";
        return realizador;
    }
}
