package com.mycompany.festivalcinema;

import java.util.ArrayList;
import java.util.Collections;

public class Premio {

    private final String nome;
    private final ArrayList<ArrayList<Integer>> pontuacoes;
    private ArrayList<Filme> filmes;
    private ArrayList<Ator> atores;
    private Filme vencedor;

    protected Premio(String nome) {
        this.nome = nome;
        this.filmes = new ArrayList<>(0);
        this.atores = new ArrayList<>(0);
        if (!(nome.contains("Ator") || nome.contains("Atriz") || nome.contains("Carreira"))) {
            this.atores = null;
        }
        if (nome.contains("Carreira")) {
            this.filmes = null;
        }

        this.pontuacoes = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            this.pontuacoes.add(new ArrayList<>(0));
        }
        this.vencedor = null;
    }

    protected String getNome() {
        return this.nome;
    }

    protected ArrayList<ArrayList<Integer>> getPontuacoes() {
        return this.pontuacoes;
    }

    protected void setPontuacao(int candidato, int perito, int pontuacao) {
        this.pontuacoes.get(candidato).set(perito, pontuacao);
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

    protected void nomeiaAtor(Ator ator) {
        this.atores.add(ator);
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

    /**
     * Método que calcula as médias das pontuações atribuídas aos candidatos
     *
     * @return o vetor que contém as médias
     */
    protected double[] mediasPontuações() {
        double[] medias = new double[4]; //guarda medias dos filmes/atores pela ordem
        int tam = pontuacoes.get(0).size();
        for (int linha = 0; linha < 4; linha++) {
            double somaPontuaçõesCandidato = 0;
            int numPontuacoes = 0;
            for (int coluna = 0; coluna < tam; coluna++) {
                somaPontuaçõesCandidato += pontuacoes.get(linha).get(coluna);
                if (pontuacoes.get(linha).get(coluna) != 0) { //certificar que foi pontuado
                    numPontuacoes++;
                }
            }
            double médiaCandidato = somaPontuaçõesCandidato / numPontuacoes;
            medias[linha] = médiaCandidato;
        }
        return medias;
    }

    /**
     * Método que ordena os candidatos de acordo com as suas médias de
     * pontuações
     *
     * @param mediasPontuacoes
     * @return
     */
    protected double[] ordenaPontuações(double[] mediasPontuacoes) {
        int n = mediasPontuacoes.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (mediasPontuacoes[j] <= mediasPontuacoes[j + 1]) {
                    swap(mediasPontuacoes, j, j + 1);
                }
            }
        }
        return mediasPontuacoes;
    }

    /**
     * Este método é um auxiliar aos bubble sort's que usamos, troca toda a
     * informação do candidato na posição i com a informação do candidato na
     * posição j
     *
     * @param mediasPontuacoes - o vetor que contém as médias das pontuações dos
     * candidatos
     * @param i
     * @param j
     */
    private void swap(double[] mediasPontuacoes, int i, int j) {
        double aux = mediasPontuacoes[i];
        mediasPontuacoes[i] = mediasPontuacoes[j];
        mediasPontuacoes[j] = aux;
        Collections.swap(this.pontuacoes, i, j);
        if (this.atores != null) {
            Collections.swap(this.atores, i, j);
        }
        if (this.filmes != null) {
            Collections.swap(this.filmes, i, j);
        }
    }

    /**
     * Este método imprime as pontuações
     */
    protected void imprimePontuações() {
        double[] pont = ordenaPontuações(mediasPontuações());
        pont = empateVencedores(pont);
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
                System.out.println("Pontuações não atribuídas");
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("Ainda sem vencedor.\n");
        }
    }

    /**
     * Este método imprime o vencedor da categoria
     */
    protected void vencedorCategoria() {
        this.determinaVencedor();
        System.out.print(nome + ": ");
        try {
            if (atores != null) {
                if (!this.nome.contains("Carreira")) {
                    System.out.println(atores.get(0).getNome() + " em " + vencedor.getNome() + "\n");
                } else {
                    System.out.println(atores.get(0).getNome() + "\n");
                }
            } else {
                System.out.println(vencedor.getNome() + "\n");
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("Ainda sem vencedor.\n");
        }
    }

    /**
     * Este método guarda em vencedor o filme que tem o candidato que ganhou o
     * prémio
     */
    protected void determinaVencedor() {
        double[] pont = ordenaPontuações(mediasPontuações());
        pont = empateVencedores(pont);
        if (!Double.isNaN(pont[0])) {
            if (filmes != null) {
                this.vencedor = filmes.get(0);
                this.vencedor.incrementaNumeroPremios();
            }
        }
    }

    /**
     * Verifica se houve empates entre os candidatos, o desempate será feito
     * calculando os desvios padrões
     *
     * @param mediasPontuacoes
     * @return o vetor das medias pontuacoes, para guardar as modificações
     */
    private double[] empateVencedores(double[] mediasPontuacoes) {
        if (mediasPontuacoes[0] == mediasPontuacoes[1]) { //verifica se há empate para primeiro
            double[] desviosPadrao = new double[4];
            desviosPadrao[0] = desvioPadrao(mediasPontuacoes, 0);
            desviosPadrao[1] = desvioPadrao(mediasPontuacoes, 1);
            desviosPadrao[2] = 0;
            desviosPadrao[3] = 0;
            if (mediasPontuacoes[2] == mediasPontuacoes[0]) { //verifica se há empate para primeiro entre 3 candidatos
                desviosPadrao[2] = desvioPadrao(mediasPontuacoes, 2);
            }
            if (mediasPontuacoes[3] == mediasPontuacoes[0]) { //verifica se todos os candidatos estão empatados
                desviosPadrao[3] = desvioPadrao(mediasPontuacoes, 3);
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

    /**
     * Este método vai calcular o desvio padrão das pontuações atribuídas ao
     * candidato especificado por posCandidato
     *
     * @param mediasPontuacoes - o vetor que contém as médias das pontuações dos
     * candidatos
     * @param posCandidato - a posição do candidato do qual calcular o desvio
     * padrão
     * @return o valor de desvio padrão
     */
    private double desvioPadrao(double[] mediasPontuacoes, int posCandidato) {
        double soma = 0;
        int tam = pontuacoes.get(posCandidato).size();
        for (int i = 0; i < tam; i++) {
            soma += Math.pow((double) pontuacoes.get(posCandidato).get(i) - mediasPontuacoes[posCandidato], 2);
        }
        return Math.sqrt(soma / mediasPontuacoes.length);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Premio pre = (Premio) obj;
        return this.nome.equalsIgnoreCase(pre.nome) && this.vencedor.equals(pre.vencedor) && this.filmes.equals(pre.filmes) && this.atores.equals(pre.atores) && this.pontuacoes == pre.pontuacoes;
    }
}
