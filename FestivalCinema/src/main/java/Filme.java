
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
        this.AtorPrincipal = null;
        this.AtrizPrincipal = null;
        this.atoresSecundarios = new ArrayList<Pessoa>(0);
    }

    public void insereAtor(Ator ator, int posição) {
        if (posição < 2) {
            if (posição == 0 && this.AtorPrincipal == null) {
                this.AtorPrincipal = ator;
            } else if (posição == 1 && this.AtrizPrincipal == null) {
                this.AtrizPrincipal = ator;
            } else {
                System.out.println("Já existe!");
            }
        } else {
            this.atoresSecundarios.add(ator);
        }
    }

    public String getNome() {
        return nome;
    }
    
    public Pessoa getAtorPrincipal(){
        return AtorPrincipal;
    }
    
    public Pessoa getAtrizPrincipal(){
        return AtrizPrincipal;
    }
    
    public Realizador getRealizador(){
        return realizador;
    }

    public String toString() {
        String filme;
        filme = "Filme: " + nome + "\n";
        filme += "Género: " + genero + "\n";
        filme += "Realizador: " + realizador.getPrimeiroNome() + " " + realizador.getUltimoNome() + "\n";
        if (AtorPrincipal != null) {
            filme += "Ator Principal: " + AtorPrincipal.getPrimeiroNome() + " " + AtorPrincipal.getUltimoNome() + "\n";
        }
        if (AtrizPrincipal != null) {
            filme += "Atriz Principal: " + AtrizPrincipal.getPrimeiroNome() + " " + AtrizPrincipal.getUltimoNome() + "\n";
        }
        int indice = 0;
        while (indice < atoresSecundarios.size()) {
            if (indice == 0) {
                filme += "Atores Secundários:\n";
            }
            filme += atoresSecundarios.get(indice).getPrimeiroNome() + " " + atoresSecundarios.get(indice).getUltimoNome() + "\n";
            indice++;
        }

        return filme;
    }

}
