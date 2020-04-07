
import java.util.ArrayList;

public class Edicao {

    private final int numEdicao;
    private final int ano;
    private final ArrayList<Filme> filmes;
    private final ArrayList<Premio> premios;
    private ArrayList<Perito> peritos;

    public Edicao(int numEdicao, int ano) {

        this.numEdicao = numEdicao;
        this.ano = ano;
        this.premios = new ArrayList<Premio>(9);
        this.filmes = new ArrayList<Filme>();
        this.peritos = new ArrayList<Perito>();
        inserePremios();
    }

    public String toString() {
        String Edicao;
        Edicao = "Edição: " + numEdicao + " | Ano: " + ano;

        return Edicao;
    }

    public void inserePerito(Perito perito) {
        this.peritos.add(perito);
    }

    public ArrayList<Perito> getPeritos() {
        return this.peritos;
    }

    public final void inserePremios() {
        this.premios.add(new Premio("Melhor Ator Principal"));
        this.premios.add(new Premio("Melhor Atriz Principal"));
        this.premios.add(new Premio("Melhor Ator Secundário"));
        this.premios.add(new Premio("Melhor Atriz Secundária"));
        this.premios.add(new Premio("Melhor Filme"));
        this.premios.add(new Premio("Melhor Realizador"));
        this.premios.add(new Premio("Melhor Argumento"));
        this.premios.add(new Premio("Melhor Cinematografia"));
        this.premios.add(new Premio("Premio Carreira"));
    }

    public void imprimeFilmes() {
        System.out.println("FILMES:");
        if (filmes.isEmpty()) {
            System.out.println("! NÃO HÁ FILMES REGISTADOS !");
        } else {
            int indice = 0;
            while (indice < filmes.size()) {
                System.out.println(filmes.get(indice));
                indice++;
            }
        }
    }

    public void imprimePremios() {
        int indice = 0;
        while (indice < premios.size()) {
            System.out.println(premios.get(indice));
            indice++;
        }
    }

    public void insereFilmes(Filme filme) {
        this.filmes.add(filme);
    }

    public int getNumEdicao() {
        return numEdicao;
    }

    public ArrayList<Filme> getFilmes() {
        return this.filmes;
    }

    public ArrayList<Premio> getPremios() {
        return this.premios;
    }
}
