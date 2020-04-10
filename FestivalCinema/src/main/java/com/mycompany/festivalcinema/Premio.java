package com.mycompany.festivalcinema;

import java.util.ArrayList;
import java.util.Collections;

public class Premio {

    private final String nome;
    private final int[][] pontuacoes;
    private ArrayList<Filme> filmes;
    private ArrayList<Ator> atores;
    private Filme vencedor;
    private static final int NUMEROPERITOS = 5;

    protected Premio(String nome) {
        this.nome = nome;
        this.filmes = new ArrayList<>(0);
        this.atores = new ArrayList<>(0);
        if (!(nome.contains("Ator") || nome.contains("Atriz") || nome.contains("Carreira"))) {
            this.atores = null;
        }
        if(nome.contains("Carreira")){
            this.filmes = null;
        }

        this.pontuacoes = new int[4][NUMEROPERITOS]; //4 candidatos, 5 peritos
        this.vencedor = null;
    }

    protected String getNome() {
        return this.nome;
    }

    protected int[][] getPontuacoes() {
        return this.pontuacoes;
    }

    protected void setPontuacao(int candidato, int perito, int pontuacao) {
        this.pontuacoes[candidato][perito] = pontuacao;
    }

    protected void setFilmes(ArrayList<Filme> filmes) {
        this.filmes = filmes;
    }

    protected void setAtores(ArrayList<Ator> atores) {
        this.atores = atores;
    }

    protected void nomeiaFilme(Filme filme) {
        this.filmes.add(filme);
    }

    protected void nomeiaAtor(Ator ator, Filme filme) {
        this.atores.add(ator);
        nomeiaFilme(filme);
    }

    protected ArrayList<Ator> getAtoresCandidatos() {
        return atores;
    }

    protected ArrayList<Filme> getFilmesCandidatos() {
        return filmes;
    }

    protected Filme getVencedor() {
        return vencedor;
    }

    @Override
    public String toString() {
        return nome;
    }

    protected double[] mediasPontuações(int pontuações[][]) {
        double[] medias = new double[4]; //guarda medias dos filmes/atores pela ordem
        for (int linha = 0; linha < 4; linha++) {
            double somaPontuaçõesCandidato = 0;
            int numPontuacoes = 0;
            for (int coluna = 0; coluna < NUMEROPERITOS; coluna++) {
                somaPontuaçõesCandidato += pontuações[linha][coluna];
                if (pontuações[linha][coluna] != 0) {
                    numPontuacoes++;
                }
            }
            double médiaCandidato = somaPontuaçõesCandidato / numPontuacoes;
            medias[linha] = médiaCandidato;
        }
        return medias;
    }

    protected double[] ordenaPontuações(double[] pontuações) {
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
        }
        if (this.filmes != null) {
            Collections.swap(this.filmes, i, j);
        }
    }

    protected void imprimePontuações(int pontuações[][]) {
        double[] pont = ordenaPontuações(mediasPontuações(pontuações));
        pont = empateVencedores(pontuacoes, pont);
        System.out.println("\n- " + nome + ": ");
        try {
            if (!Double.isNaN(pont[0])) {
                for (int i = 0; i < pont.length; ++i) {
                    if (atores != null) {                     //se o prémio for para um ator/atriz
                        System.out.print(atores.get(i).getNome() + ": ");
                    } else {                                                    //se o prémio for para um filme
                        System.out.print(filmes.get(i).getNome() + ": ");
                    }
                    System.out.printf("%.2f\n", pont[i]); //imprime pontuação
                }
            } else {
                throw new Exception("Pontuações não atribuídas");
            }
        } catch (Exception e) {
            System.out.println("Os candidatos não foram avaliados.\n");
        }
    }

    protected void vencedorCategoria() {
        double[] pont = ordenaPontuações(mediasPontuações(pontuacoes));
        pont = empateVencedores(pontuacoes, pont);
        System.out.print(nome + ": ");
        try {
            if (!Double.isNaN(pont[0])) {
                if ( atores != null) {
                    System.out.println(atores.get(0).getNome() + "\n");
                } else {
                    System.out.println(filmes.get(0).getNome() + "\n");
                }
            } else {
                throw new Exception("Pontuações não atribuídas");
            }
        } catch (Exception e) {
            System.out.println("Ainda sem vencedor.\n");
        }
    }

    protected void determinaVencedor() {
        double[] pont = ordenaPontuações(mediasPontuações(pontuacoes));
        pont = empateVencedores(pontuacoes, pont);
        try {
            if (!Double.isNaN(pont[0])) {
                if (filmes != null) {
                    this.vencedor = filmes.get(0);
                    this.vencedor.incrementaNumeroPremios();
                }
            } else {
                throw new Exception("Pontuações não atribuídas");
            }
        } catch (Exception e) {
            System.out.println("Ainda sem vencedor.\n");
        }
    }

    private double[] empateVencedores(int[][] pontuacoes, double[] mediasPontuacoes) {
        if (mediasPontuacoes[0] == mediasPontuacoes[1]) {
            double[] desviosPadrao = new double[4];
            desviosPadrao[0] = desvioPadrao(pontuacoes, mediasPontuacoes, 0);
            desviosPadrao[1] = desvioPadrao(pontuacoes, mediasPontuacoes, 1);
            desviosPadrao[2] = 0;
            desviosPadrao[3] = 0;
            if (mediasPontuacoes[2] == mediasPontuacoes[0]) {
                desviosPadrao[2] = desvioPadrao(pontuacoes, mediasPontuacoes, 2);
            }
            if (mediasPontuacoes[3] == mediasPontuacoes[0]) {
                desviosPadrao[3] = desvioPadrao(pontuacoes, mediasPontuacoes, 3);
            }
            for (int i = 0; i < desviosPadrao.length; i++) {
                for (int j = 0; j < desviosPadrao.length - i - 1; j++) {
                    if (desviosPadrao[j] > desviosPadrao[j + 1]) {
                        swap(mediasPontuacoes, j, j + 1);
                    }
                }
            }
        }
        return mediasPontuacoes;
    }

    private double desvioPadrao(int[][] pontuacoes, double[] mediasPontuacoes, int posCandidato) {
        double soma = 0;
        for (int i = 0; i < NUMEROPERITOS; i++) {
            soma += Math.pow((double) pontuacoes[posCandidato][i] - mediasPontuacoes[posCandidato], 2);
        }
        return Math.sqrt(soma / mediasPontuacoes.length);
    }

}
