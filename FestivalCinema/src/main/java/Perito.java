
import java.util.Scanner;

public class Perito extends Pessoa {

    public Perito(String nome, boolean genero) {
        super(nome, genero);
    }

    @Override
    public String toString() {
        String perito = super.toString();
        return perito;
    }

    public boolean inserePontuacao(Premio premio, int indiceCandidato, int indicePerito, Scanner scan) {
        System.out.printf((this.getGenero() ? "O perito %s " : "A perita %s ") + "atribui ao candidato a pontuação: ", this.getNome());
        double pontuacao = scan.nextDouble();
        scan.nextLine();
        if (pontuacao > 0 && pontuacao <= 10 && pontuacao == (int) pontuacao) {
            premio.setPontuacao(indiceCandidato, indicePerito, (int) pontuacao);
        } else {
            return false;
        }
        return true;
    }
}
