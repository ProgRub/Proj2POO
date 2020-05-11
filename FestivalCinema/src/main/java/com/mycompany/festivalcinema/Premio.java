package com.mycompany.festivalcinema;

import java.util.ArrayList;
import java.util.Collections;

public class Premio {

    private final String nome;
    private final ArrayList<ArrayList<Integer>> pontuacoes;
    private ArrayList<Filme> filmes;
    private ArrayList<Ator> atores;
    private final double[] mediasPontuacoes;
    private Filme vencedor;

    protected Premio(String nome) {
        this.nome = nome;
        this.filmes = new ArrayList<>(0);
        this.atores = new ArrayList<>(0);
        this.mediasPontuacoes = new double[4];
        //se o prémio for relacionado exclusivamente com filme (Melhor filme, melhor realizador, etc.)
        //pomos a lista dos atores a nula por questões de memória e tratamento de exceções
        if (!(nome.contains("Ator") || nome.contains("Atriz") || nome.contains("Carreira"))) {
            this.atores = null;
        }
        //se o prémio tem no nome "Carreira" (indica que é o prémio carreira)
        //pomos a lista dos filmes  a nula por questões de memória e tratamento de exceções
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
     */
    protected void calcularMedias() {
        int tam = pontuacoes.get(0).size(); //vê quantas possíveis pontuações foram atribuídas ao candidato
        for (int linha = 0; linha < 4; linha++) {
            double somaPontuaçõesCandidato = 0;
            int numPontuacoes = 0;
            for (int coluna = 0; coluna < tam; coluna++) {
                somaPontuaçõesCandidato += pontuacoes.get(linha).get(coluna);
                if (pontuacoes.get(linha).get(coluna) != 0) { //certificar que foi pontuado (se é 0 significa que não foi pontuado)
                    numPontuacoes++;
                }
            }
            mediasPontuacoes[linha] = somaPontuaçõesCandidato / numPontuacoes;
        }
    }

    /**
     * Método que ordena todas as listas relativamente à média de pontuações que
     * os candidatos obtiveram, através de um bubble sort.
     *
     * @throws IndexOutOfBoundsException - se não houver candidatos atira esta
     * exceção tratado noutro lugar
     */
    protected void ordenaPontuações() throws IndexOutOfBoundsException {
        int n = mediasPontuacoes.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (mediasPontuacoes[j] <= mediasPontuacoes[j + 1]) {
                    swap(j, j + 1);
                }
            }
        }
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
    private void swap(int i, int j) throws IndexOutOfBoundsException {
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
     * Este método imprime as pontuações e indica se ainda não foram atribuídas
     * as pontuações aos candidatos ou se o prémio não tem candidatos
     */
    protected void imprimePontuações() {
        System.out.println("\n- " + nome + ": ");
        try {
            calcularMedias();
            ordenaPontuações(); //se não houver candidatos, é atirada a IndexOutOfBoundsException e apanhada aqui
            empateVencedores();
            if (!Double.isNaN(mediasPontuacoes[0])) { //verifica se no vetor das médias das pontuações, na posição do vencedor aparece NaN (Not a Number)
                //pois se aparece então as pontuações ainda não foram atribuídas
                for (int i = 0; i < mediasPontuacoes.length; ++i) {
                    if (atores != null) {//se o prémio for para um ator/atriz
                        if (!this.nome.contains("Carreira")) { //se não for o prémio Carreira, indica-se por qual filme o ator participou
                            System.out.print(atores.get(i).getNome() + " em " + filmes.get(i).getNome());
                        } else { //se for o prémio carreira só se imprime o nome do ator
                            System.out.print(atores.get(i).getNome());
                        }
                    } else {//se o prémio for para um filme
                        if (this.nome.contains("Realizador")) { //se for o prémio de Melhor Realizador, indica-se este e o filme que ele direcionou
                            System.out.print(filmes.get(i).getRealizador().getNome() + " por " + vencedor.getNome());
                        } else { //se não, só se imprime o nome do filme
                            System.out.print(filmes.get(i).getNome());
                        }
                    }
                    System.out.printf(": %.2f\n", mediasPontuacoes[i]); //imprime pontuação
                }
            } else {
                System.out.println("Pontuações não atribuídas");
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) { //se ocorre uma destas exceções é que ainda não foram definidos os candidatos
            System.out.println("Não tem candidatos.");
        }
    }

    /**
     * Este método imprime o vencedor da categoria e indica ao utilizador se
     * ainda não é possível determinar um vencedor (ou não há candidatos ou as
     * pontuações ainda não foram atribuídas)
     */
    protected void vencedorCategoria() {
        determinaVencedor();
        System.out.print(nome + ": ");
        try {
            if (!Double.isNaN(mediasPontuacoes[0])) { //verifica se no vetor das médias das pontuações, na posição do vencedor aparece NaN (Not a Number)
                //pois se aparece então as pontuações ainda não foram atribuídas
                if (atores != null) {//se o prémio for para um ator/atriz
                    if (!this.nome.contains("Carreira")) {//se não for o prémio Carreira, indica-se por qual filme o vencedor participou
                        System.out.println(atores.get(0).getNome() + " em " + vencedor.getNome() + "\n");
                    } else {//se for o prémio carreira só se imprime o nome do ator
                        System.out.println(atores.get(0).getNome() + "\n");
                    }
                } else {//se o prémio for para um filme
                    if (this.nome.contains("Realizador")) {//se for o prémio de Melhor Realizador, indica-se este e o filme que ele direcionou
                        System.out.println(vencedor.getRealizador().getNome() + " por " + vencedor.getNome() + "\n");
                    } else {//se não, só se imprime o nome do filme
                        System.out.println(vencedor.getNome() + "\n");
                    }
                }
            } else {
                System.out.println("Ainda sem vencedor.\n");
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {//se ocorre uma destas exceções é que ainda não foram definidos os candidatos
            System.out.println("Ainda sem vencedor.\n");
        }
    }

    /**
     * Este método guarda em vencedor o filme que tem o candidato que ganhou o
     * prémio
     */
    protected void determinaVencedor() {
        calcularMedias();
        ordenaPontuações();
        empateVencedores();
        if (!Double.isNaN(mediasPontuacoes[0])) {//verifica se no vetor das médias das pontuações, na posição 0 aparece NaN (Not a Number)
            //pois se aparece então as pontuações ainda não foram atribuídas
            if (filmes != null && vencedor == null) { //certifica que o vencedor só é definido uma vez para não se aumentar 
                //o número de prémios em cada vez que esta função é chamada
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
    private void empateVencedores() {
        double[] desviosPadrao = {-1, -1, -1, -1}; //preenchemos o array assim por questões de verificação
        for (int i = 0; i < mediasPontuacoes.length - 1; i++) {//percorremos o vetor das médias (já ordenado) a verificar se houve empates
            if (mediasPontuacoes[i] == mediasPontuacoes[i + 1]) { //se há empate, será entre posições consecutivas e calcula-se o desvio padrão dos candidatos
                if (desviosPadrao[i] == -1) { //para certificar que não se calcula o desvio padrão demasiadas vezes
                    desviosPadrao[i] = desvioPadrao(i);
                }
                if (desviosPadrao[i + 1] == -1) { //para certificar que não se calcula o desvio padrão demasiadas vezes
                    desviosPadrao[i + 1] = desvioPadrao(i + 1);
                }
            }
        }
        //Bubble sort para organizar segundo os desvios padrão, se houver empate, quem tem menor desvio padrão "ganha"
        for (int i = 0; i < mediasPontuacoes.length; i++) {
            for (int j = 0; j < mediasPontuacoes.length - i - 1; j++) {
                if (mediasPontuacoes[j] == mediasPontuacoes[j + 1] && desviosPadrao[j] > desviosPadrao[j + 1]) {
                    swap(j, j + 1);
                    double aux = desviosPadrao[j]; //trocar o desvio padrão da posição j com j+1
                    desviosPadrao[j] = desviosPadrao[j + 1]; //se não a ordem dos candidatos ficaria incorreta
                    desviosPadrao[j + 1] = aux;
                }
            }
        }
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
    private double desvioPadrao(int posCandidato) {
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
