
import java.util.ArrayList;

public class Filme {

    private final String nome;
    private final String genero;
    private final int edição;
    private final Realizador realizador;
    private Pessoa AtorPrincipal;
    private Pessoa AtrizPrincipal;
    private ArrayList<Pessoa> atoresSecundarios;

    public Filme(String nome, String genero, int edição, Realizador realizador) {
        this.nome = nome;
        this.genero = genero;
        this.edição = edição;
        this.realizador = realizador;
        this.atoresSecundarios = new ArrayList<Pessoa>();
    }

    public void insereAtor(Ator ator, int posição) {
        if (posição < 2) {
            if (posição == 0 && this.AtorPrincipal == null) {
                this.AtorPrincipal = ator;
            } else if (posição == 1 && this.AtrizPrincipal == null) {
                this.AtrizPrincipal = ator;
            } else {
                System.out.println("OCUPADO!");
            }
        } else {
            this.atoresSecundarios.add(ator);
        }
    }

    public String getNome() {
        return nome;
    }

    public String toString() {
        String filme;
        filme = "Filme: " + nome + "\n";
        filme += "Género: " + genero + "\n";
        filme += "Realizador: " + realizador.getPrimeiroNome() + " " + realizador.getUltimoNome() + "\n";
        int indice = 0;
        while (indice < atoresSecundarios.size()+2) {
            if (indice == 0) {
                filme += "Ator Principal: " + AtorPrincipal.getPrimeiroNome() + " " + AtorPrincipal.getUltimoNome() + "\n";
            } else if (indice == 1) {
                filme += "Atriz Principal: " + AtrizPrincipal.getPrimeiroNome() + " " + AtrizPrincipal.getUltimoNome() + "\n";
            } else if (indice == 2) {
                filme += "Atores Secundários: \n";
                filme += atoresSecundarios.get(indice-2).getPrimeiroNome() + " " + atoresSecundarios.get(indice-2).getUltimoNome() + "\n";
            } else {
                filme += atoresSecundarios.get(indice-2).getPrimeiroNome() + " " + atoresSecundarios.get(indice-2).getUltimoNome() + "\n";
            }
            indice++;
        }

        return filme;
    }

}
