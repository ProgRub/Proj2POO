
public class Perito extends Pessoa {

    public Perito(String nome, boolean genero) {
        super(nome, genero);
    }

    public String toString() {
        String perito = super.toString();
        return perito;
    }

    public void inserePontuacao(int pontuacao, Premio premio, int indiceCandidato, int indicePerito) {
        premio.setPontuacao(indiceCandidato, indicePerito, pontuacao);
        System.out.println(premio.getPontuacoes());
    }
}
