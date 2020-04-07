
import java.util.ArrayList;

public class Premio {

    private String nome;
    private int[][] pontuacoes;
    private ArrayList<Filme> filmes;
    private ArrayList<Pessoa> atores;

    public Premio(String nome) {
        this.nome = nome;
        if (nome.contains("Ator") || nome.contains("Atriz") || nome.contains("Carreira")) {
            this.filmes = null;
        } else {
            this.atores = null;
        }
        this.pontuacoes = new int[4][5]; //4 candidatos, 5 peritos
    }

    public String getNome() {
        return this.nome;
    }

    public int[][] getPontuacoes() {
        return this.pontuacoes;
    }

    public void setPontuacao(int candidato, int perito, int pontuacao) {
        this.pontuacoes[candidato][perito] = pontuacao;
    }

    public void setFilmes(ArrayList<Filme> filmes) {
        this.filmes = filmes;
    }

    public void setAtores(ArrayList<Pessoa> atores) {
        this.atores = atores;
    }

    public ArrayList<Pessoa> getAtoresCandidatos() {
        return atores;
    }

    public ArrayList<Filme> getFilmesCandidatos() {
        return filmes;
    }

    public String toString() {
        return nome;
    }

    public double[] mediasPontuações(int pontuações[][]) {
        double[] medias = new double[4]; //guarda medias dos filmes/atores pela ordem
        for (int linha = 0; linha < 4; linha++) {
            double somaPontuaçõesCandidato = 0;
            double médiaCandidato = 0;
            int numPontuacoes = 0;
            for (int coluna = 0; coluna < 5; coluna++) {
                somaPontuaçõesCandidato += pontuações[linha][coluna];
                if (pontuações[linha][coluna] != 0) {
                    numPontuacoes++;
                }
            }
            médiaCandidato = somaPontuaçõesCandidato / numPontuacoes;
            medias[linha] = médiaCandidato;
        }
        return medias;
    }

    public double[] ordenaPontuações(double[] pontuações) {
        int n = pontuações.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (pontuações[j] <= pontuações[j + 1]) {
                    double aux = pontuações[j];
                    pontuações[j] = pontuações[j + 1];
                    pontuações[j + 1] = aux;
                }
            }
        }
        return pontuações;
    }

    public void imprimePontuações(int pontuações[][]) {
        double[] pont = mediasPontuações(pontuações);
        double pont1 = pont[0]; //média do filme/ator da primeira linha
        double pont2 = pont[1]; //média do filme/ator da segunda linha
        double pont3 = pont[2]; //média do filme/ator da terceira linha
        pont = ordenaPontuações(mediasPontuações(pontuações));
        int n = pont.length;
        System.out.println(nome + ": ");
        System.out.println("- PONTUAÇÕES: ");
        for (int i = 0; i < n; ++i) {
            if (pont[i] == pont1) { //se a pontuação mais alta for do primeiro filme/ator (no caso de i=0)
                if (filmes == null && atores != null) {                     //se o prémio for para um ator/atriz
                    System.out.print(atores.get(0).getNome() + ": ");
                } else {                                                    //se o prémio for para um filme
                    System.out.print(filmes.get(0).getNome() + ": ");
                }
                pont1 = 0;
            } else if (pont[i] == pont2) { //se a pontuação mais alta for do segundo filme/ator (no caso de i=0)
                if (filmes == null && atores != null) {
                    System.out.print(atores.get(1).getNome() + ": ");
                } else {
                    System.out.print(filmes.get(1).getNome() + ": ");
                }
                pont2 = 0;
            } else if (pont[i] == pont3) { //se a pontuação mais alta for do terceiro filme/ator (no caso de i=0)
                if (filmes == null && atores != null) {
                    System.out.print(atores.get(2).getNome() + ": ");
                } else {
                    System.out.print(filmes.get(2).getNome() + ": ");
                }
                pont3 = 0;
            } else { //se a pontuação mais alta for do quarto filme/ator (no caso de i=0)
                if (filmes == null && atores != null) {
                    System.out.print(atores.get(3).getNome() + ": ");
                } else {
                    System.out.print(filmes.get(3).getNome() + ": ");
                }
            }
            System.out.println(pont[i]); //imprime pontuação
        }
    }

    public void vencedorCategoria(int pontuações[][]) {
        double[] pont = mediasPontuações(pontuações);
        double pont1 = pont[0]; //média do filme/ator da primeira linha
        double pont2 = pont[1]; //média do filme/ator da segunda linha
        double pont3 = pont[2]; //média do filme/ator da terceira linha
        pont = ordenaPontuações(mediasPontuações(pontuações));
        System.out.print(nome + ": ");
        try{
            if (pont[0] == pont1) {
            if (filmes == null && atores != null) {
                System.out.println(atores.get(0).getNome()+"\n");
            } else {  
                System.out.println(filmes.get(0).getNome()+"\n");
            }
        } else if (pont[0] == pont2) { 
            if (filmes == null && atores != null) {
                System.out.println(atores.get(1).getNome()+"\n");
            } else {
                System.out.println(filmes.get(1).getNome()+"\n");
            }
        } else if (pont[0] == pont3) {
            if (filmes == null && atores != null) {
                System.out.println(atores.get(2).getNome()+"\n");
            } else {
                System.out.println(filmes.get(2).getNome()+"\n");
            }
        } else { 
            if (filmes == null && atores != null) {
                System.out.println(atores.get(3).getNome()+"\n");
            } else {
                System.out.println(filmes.get(3).getNome()+"\n");
            }
        }
        }catch (Exception e){
            System.out.println("Ainda sem vencedor.\n");
        }
    }
    
    
}
