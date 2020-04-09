
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Premio {

    private String nome;
    private int[][] pontuacoes;
    private ArrayList<Filme> filmes;
    private ArrayList<Ator> atores;
    private static final int NUMEROPERITOS = 5;

    public Premio(String nome) {
        this.nome = nome;
        if (nome.contains("Ator") || nome.contains("Atriz") || nome.contains("Carreira")) {
            this.filmes = null;
        } else {
            this.atores = null;
        }
        this.pontuacoes = new int[4][NUMEROPERITOS]; //4 candidatos, 5 peritos
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

    public void setAtores(ArrayList<Ator> atores) {
        this.atores = atores;
    }

    public ArrayList<Ator> getAtoresCandidatos() {
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
            for (int coluna = 0; coluna < NUMEROPERITOS; coluna++) {
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
                    swap(pontuações, j, j + 1);
                }
            }
        }
        return pontuações;
    }

    private void swap(double[] pontuações, int i, int j) {
        double aux = pontuações[i];
        pontuações[i] = pontuações[j];
        pontuações[j] = aux;
        int[] aux2 = this.pontuacoes[i];
        this.pontuacoes[i] = this.pontuacoes[j];
        this.pontuacoes[j] = aux2;
        if (this.atores != null) {
            Collections.swap(this.atores, i, j);
        } else {
            Collections.swap(this.filmes, i, j);
        }
    }

    public void imprimePontuações(int pontuações[][]) { //UM ERRO 
        double[] pont = mediasPontuações(pontuações);
        double pont1 = pont[0]; //média do filme/ator da primeira linha
        double pont2 = pont[1]; //média do filme/ator da segunda linha
        double pont3 = pont[2]; //média do filme/ator da terceira linha
        pont = ordenaPontuações(mediasPontuações(pontuações));
        pont = empateVencedores(pontuacoes, pont);
        System.out.println("\n- " + nome + ": ");
        try {
            for (int i = 0; i < pont.length; ++i) {
                if (filmes == null && atores != null) {                     //se o prémio for para um ator/atriz
                    System.out.print(atores.get(i).getNome() + ": ");
                } else {                                                    //se o prémio for para um filme
                    System.out.print(filmes.get(i).getNome() + ": ");
                }
                System.out.printf("%.2f\n", pont[i]); //imprime pontuação
            }
        } catch (Exception e) {
            System.out.println("Os candidatos não foram avaliados.\n");
        }
    }

    public void vencedorCategoria(int pontuações[][]) {
        double[] aux = mediasPontuações(pontuações);
        double pont1 = aux[0]; //média do filme/ator da primeira linha
        double pont2 = aux[1]; //média do filme/ator da segunda linha
        double pont3 = aux[2]; //média do filme/ator da terceira linha
        double[] pont = ordenaPontuações(mediasPontuações(pontuações));
        pont = empateVencedores(pontuacoes, pont);
        System.out.print(nome + ": ");
        try {
            if (pont[0] > 0) {
                if (filmes == null && atores != null) {
                    System.out.println(atores.get(0).getNome() + "\n");
                } else {
                    System.out.println(filmes.get(0).getNome() + "\n");
                }
            } else {
                throw new NullPointerException("Pontuações não atribuídas");
            }
        } catch (NullPointerException e) {
            System.out.println("Ainda sem vencedor.\n");
        }
    }

    public double[] empateVencedores(int[][] pontuacoes, double[] mediasPontuacoes) {
        if (mediasPontuacoes[0] == mediasPontuacoes[1]) {
            double[] desviosPadrao = new double[4];
            desviosPadrao[0] = desvioPadrao(NUMEROPERITOS, pontuacoes, mediasPontuacoes, 0);
            desviosPadrao[1] = desvioPadrao(NUMEROPERITOS, pontuacoes, mediasPontuacoes, 1);
            desviosPadrao[2] = 0;
            desviosPadrao[3] = 0;
            if (mediasPontuacoes[2] == mediasPontuacoes[0]) {
                desviosPadrao[2] = desvioPadrao(NUMEROPERITOS, pontuacoes, mediasPontuacoes, 2);
            }
            if (mediasPontuacoes[3] == mediasPontuacoes[0]) {
                desviosPadrao[3] = desvioPadrao(NUMEROPERITOS, pontuacoes, mediasPontuacoes, 3);
            }
            for (int i = 0; i < desviosPadrao.length; i++) {
                for (int j = 0; j < desviosPadrao.length - i - 1; j++) {
                    if (desviosPadrao[j] > desviosPadrao[j + 1]) {
                        System.out.println("CHEGOU");
                        swap(mediasPontuacoes, j, j + 1);
                    }
                }
            }
        }
        return mediasPontuacoes;
    }

    private double desvioPadrao(int maxSoma, int[][] pontuacoes, double[] mediasPontuacoes, int posCandidato) {
        int soma = 0;
        for (int i = 0; i < NUMEROPERITOS; i++) {
            System.out.println(pontuacoes[posCandidato][i] + " " + mediasPontuacoes[posCandidato]);
            soma += Math.pow((double) pontuacoes[posCandidato][i] - mediasPontuacoes[posCandidato], 2);
        }
        System.out.println(Math.sqrt(soma / mediasPontuacoes.length));
        return Math.sqrt(soma / mediasPontuacoes.length);
    }

}
