
public class Perito extends Pessoa {

    private int pontuação;

    public Perito(String nome, boolean genero) {
        super(nome, genero);
    }

    public String toString() {
        String perito = super.toString();
        return perito;
    }

    public void inserePontuacao(int pontuacao) {
        this.pontuação = pontuacao;
    }

    public int getPontuacao() {
        return this.pontuação;
    }
}
