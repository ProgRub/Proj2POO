package com.mycompany.festivalcinema;

import java.util.ArrayList;

public class Edicao {

    private final int numEdicao;
    private final int ano;
    private final ArrayList<Filme> filmes;
    private final ArrayList<Premio> premios;
    private final ArrayList<Perito> peritos;

    protected Edicao(int numEdicao, int ano) {

        this.numEdicao = numEdicao;
        this.ano = ano;
        this.premios = new ArrayList<>(9);
        this.filmes = new ArrayList<>();
        this.peritos = new ArrayList<>();
        inserePremios();
    }

    @Override
    public String toString() {
        String Edicao;
        Edicao = "Edição: " + numEdicao + " | Ano: " + ano;

        return Edicao;
    }

    protected ArrayList<Perito> getPeritos() {
        return this.peritos;
    }

    /**
     * Método responsável por criar a lista dos 9 prémios de cada edição
     */
    protected final void inserePremios() {
        this.premios.add(new Premio("Melhor Ator Principal"));
        this.premios.add(new Premio("Melhor Atriz Principal"));
        this.premios.add(new Premio("Melhor Ator Secundário"));
        this.premios.add(new Premio("Melhor Atriz Secundária"));
        this.premios.add(new Premio("Melhor Filme"));
        this.premios.add(new Premio("Melhor Realizador"));
        this.premios.add(new Premio("Melhor Argumento"));
        this.premios.add(new Premio("Melhor Cinematografia"));
        this.premios.add(new Premio("Prémio Carreira"));
    }

    protected void insereFilmes(Filme filme) {
        this.filmes.add(filme);
    }

    protected void inserePerito(Perito perito) {
        this.peritos.add(perito);
        for (Premio p : this.premios) { //acede a cada prémio
            for (ArrayList<Integer> lista : p.getPontuacoes()) { //acede à lista de pontuações de cada candidato
                lista.add(0); //coloca a pontuação 0 (não avaliado), em cada candidato do prémio
            }
        }
    }

    protected int getNumEdicao() {
        return numEdicao;
    }

    protected ArrayList<Filme> getFilmes() {
        return this.filmes;
    }

    protected ArrayList<Premio> getPremios() {
        return this.premios;
    }

    /**
     * Método que lista todos os filmes da edição
     */
    protected void imprimeFilmes() {
        System.out.println("\nEDIÇÃO: " + this.numEdicao);
        if (filmes.isEmpty()) {
            System.out.println("! NÃO HÁ FILMES REGISTADOS !");
        } else {
            System.out.println("\nFILMES:\n");
            for (Filme filme : this.filmes) {
                System.out.println(filme);
            }
        }
    }

    /**
     * Método que lista os Premios a premiar na edição atual
     */
    protected void imprimePremios() {
        System.out.println("\nCATEGORIAS A PREMIAR:");
        int indice = 0;
        while (indice < premios.size()) {
            System.out.println(premios.get(indice));
            indice++;
        }
    }
    
    /**
     * Método que lista os filmes com pelo menos um prémio
     */
    protected void listarFilmesMaisPremiados() {
        boolean semPremiados = true; //true se nenhum filme foi premiado
        System.out.println("\nFILMES MAIS PREMIADOS: ");
        for (Filme filme : this.filmes) {
            if (filme.getNumeroPremios() > 0) { //se o filme tiver pelo menos 1 prémio
                System.out.println(filme.getNome() + ": " + filme.getNumeroPremios());
                semPremiados = false;
            }
        }
        if (semPremiados) {
            System.out.println("Nenhum filme foi premiado.");
        }
    }

    /**
     * Método que mostra os quatro candidatos ao prémio em cada categoria
     */
    protected void listarCandidatos() {
        int contaPremios = 1;
        System.out.println("\nCANDIDATOS AOS PRÉMIOS:");
        for (Premio premio : this.premios) {
            System.out.println();
            System.out.println(premio + ":");
            try {
                if (contaPremios <= 4) { //se o prémio está relacionado com atores
                    for (int i = 0; i < 4; i++) {
                        System.out.printf("- %s em %s\n", premio.getAtoresCandidatos().get(i).getNome(), premio.getFilmesCandidatos().get(i).getNome());
                    }
                } else if (contaPremios > 4 && contaPremios != 6 && contaPremios != 9) { //se o prémio está relacionado com filmes
                    for (int i = 0; i < 4; i++) {
                        System.out.println("- " + premio.getFilmesCandidatos().get(i).getNome());

                    }
                } else if (contaPremios == 9) { //se é o prémio carreira
                    for (int i = 0; i < 4; i++) {
                        System.out.println("- " + premio.getAtoresCandidatos().get(i).getNome());
                    }
                } else { //se é o prémio de melhor realizador
                    for (int i = 0; i < 4; i++) {
                        System.out.println("- " + premio.getFilmesCandidatos().get(i).getRealizador().getNome() + " por "+premio.getFilmesCandidatos().get(i).getNome());
                    }
                }
            } catch (NullPointerException | IndexOutOfBoundsException e) {
                System.out.println("- Não tem candidatos.\n");
            }
            contaPremios++;
        }
    }

    /**
     * Método que lista os vencedores dos prémios
     */
    protected void listarVencedores() {
        System.out.println("\nVENCEDORES:");
        for (Premio premio : this.premios) {
            premio.vencedorCategoria();
        }
    }

    /**
     * Método que, como o nome indica, lista os candidatos de cada prémio pela
     * ordem da sua pontuação
     */
    protected void listarPontuaçõesOrdenadas() {
        System.out.println("\nPONTUAÇÕES: ");
        for (Premio premio : this.premios) {
            premio.imprimePontuações();
        }
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
        Edicao edic = (Edicao) obj;
        return this.ano == edic.ano && this.numEdicao == edic.numEdicao && this.filmes.equals(edic.filmes) && this.peritos.equals(edic.peritos) && this.premios.equals(edic.premios);
    }
}
