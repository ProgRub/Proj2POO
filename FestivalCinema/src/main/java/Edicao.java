
import java.util.ArrayList;

public class Edicao {

    private final int numEdicao;
    private final int ano;
    private final ArrayList<Filme> filmes;
    private final ArrayList<String> premios;

    public Edicao(int numEdicao, int ano) {

        this.numEdicao = numEdicao;
        this.ano = ano;
        premios = new ArrayList<String>();
        filmes = new ArrayList<Filme>();
        inserePremios();
    }

    public String toString() {
        String Edicao;
        Edicao = "Edição: " + numEdicao + " | Ano: " + ano;

        return Edicao;
    }

    public final void inserePremios() {
        premios.add("Melhor filme");
        premios.add("Melhor ator principal");
        premios.add("Melhor atriz principal");
        premios.add("Melhor ator secundário");
        premios.add("Melhor atriz secundária");
        premios.add("Melhor realizador");
        premios.add("Melhor argumento");
        premios.add("Melhor cinematografia");
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
        System.out.println("PRÉMIOS:");
        int indice = 0;
        while (indice < premios.size()) {
            System.out.println(premios.get(indice));
            indice++;
        }
    }

    public void insereFilmes(Filme filme) {
        filmes.add(filme);
    }

    public int getNumEdicao() {
        return numEdicao;
    }

    public ArrayList<Filme> getFilmes() {
        return this.filmes;
    }

}
