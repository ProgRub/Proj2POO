package FestCin;

public class Pessoa {

    private final String nome;
    private final boolean genero; //true->Homem false->Mulher

    protected Pessoa(String nome, boolean genero) {
        this.nome = nome;
        this.genero = genero;
    }

    protected String getNome() {
        return nome;
    }

    protected boolean getGenero() {
        return genero;
    }

    @Override
    public String toString() {
        String pessoa;
        pessoa = "Nome: " + getNome() + "\n";
        pessoa += "Género: " + (getGenero() ? "Masculino" : "Feminino") + "\n";
        return pessoa;
    }
}
