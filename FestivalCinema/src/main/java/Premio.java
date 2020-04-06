
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

        double[] medias = new double[4];
        int posição = 0;
        for (int coluna = 0; coluna < 5; coluna++) {
            int somaPontuaçõesCandidato = 0;
            double médiaCandidato = 0;
            for (int linha = 0; linha < 4; linha++) {
                somaPontuaçõesCandidato += pontuações[linha][coluna];
            }
            médiaCandidato = somaPontuaçõesCandidato / 4;
            medias[posição] = médiaCandidato;
            posição++;
        }
        return medias;
    }

    public void ordenaPontuações(double[] pontuações) {
        int n = pontuações.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (pontuações[j] > pontuações[j + 1]) {
                    double aux = pontuações[j];
                    pontuações[j] = pontuações[j + 1];
                    pontuações[j + 1] = aux;
                }
            }
        }
    }
    
    public void imprimePontuações(int pontuações[][]){
        double[] pont = mediasPontuações(pontuações);
        ordenaPontuações(mediasPontuações(pontuações));
        int n = pont.length;
        for (int i =0; i<n; ++i){
            System.out.println(pont[0]); //impreme pontuação
        }
        
    }

}
