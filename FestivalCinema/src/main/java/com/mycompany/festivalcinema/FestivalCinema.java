package com.mycompany.festivalcinema;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class FestivalCinema {

    private final ArrayList<Edicao> edicoes;
    private final ArrayList<Ator> atores;
    private int indexEdicoes;
    private int ano;
    private int numEdicao;
    private final Scanner scan;
    private boolean quebra;
    private final String ficheiroFilmes;
    private final String ficheiroAtores;
    private final String ficheiroPeritos;
    private final String ficheiroCandidatos;
    private final String ficheiroPontuacoes;
    private String opcao;

    public FestivalCinema() {
        this.edicoes = new ArrayList<>();
        this.atores = new ArrayList<>();
        this.opcao = "";
        this.indexEdicoes = 0;
        this.ano = 0;
        this.numEdicao = 0;
        this.scan = new Scanner(System.in, "cp1252");
        this.quebra = false;
        this.ficheiroFilmes = "Filmes.txt";
        this.ficheiroAtores = "Atores.txt";
        this.ficheiroPeritos = "Peritos.txt";
        this.ficheiroCandidatos = "Candidatos.txt";
        this.ficheiroPontuacoes = "Pontuacoes.txt";
    }

    public void menu() {
        System.out.println("\t\t\tFESTIVAL CINEMA");
        System.out.print("\nIndique o ano da edição do festival: ");
        ano = recebeInteiro();
        novoOuCarregar();
        quebra = false;
        limparConsola();
        while (!quebra) {
            System.out.printf("\t\t\t%dª Edição do Festival de Cinema %d\n", numEdicao, ano);
            System.out.print("Opções:\n(c): Criar\n(l): Listar\n(h): Nova Edição\n(g): Gravar Dados\n(s): Sair\nOpção: ");
            opcao = scan.nextLine().trim();
            limparConsola();
            switch (opcao.toLowerCase()) {
                case "c":
                    System.out.print("Opções:\n(f): Criar Filme\n(a): Criar Ator/Atriz\n(e): Criar Perito\n(p): Atribui Papel\n(c): Escolher candidatos\n(s): Atribuir Pontuação\nOpção: ");
                    opcao = scan.nextLine().trim();
                    limparConsola();
                    switch (opcao) {
                        case "f":
                            criarFilme();
                            break;
                        case "a":
                            criarAtor();
                            break;
                        case "e":
                            boolean parar = false;
                            while (!parar) {
                                criarPerito();
                                System.out.print("\nPrima 1 se pretende criar mais um perito e outro número caso contrário\nOpção: ");
                                int maisUm = recebeInteiro();
                                if (maisUm != 1) {
                                    parar = true;
                                }
                            }
                            break;
                        case "p":
                            atribuirPapel();
                            break;
                        case "c":
                            escolherCandidatos();
                            break;
                        case "s":
                            pontuarCandidatos(escolherPremio());
                            break;
                        default:
                            System.out.println("\nPor favor selecione uma das opções disponíveis.");
                    }
                    break;
                case "l":
                    System.out.print("\nOpções:\n(a): Listar Atores\n(f): Listar Filmes\n(p): Listar Prémios\n(i): Consultar Edições\nOpção: ");
                    opcao = scan.nextLine().trim();
                    switch (opcao) {
                        case "a":
                            listarAtores();
                            break;
                        case "f":
                            System.out.print("\nOpções:\n(f): Listar Filmes\n(p): Listar Filmes Mais Premiados\nOpção: ");
                            opcao = scan.nextLine().trim();
                            switch (opcao) {
                                case "f":
                                    consultarEdicoes();
                                    edicoes.get(indexEdicoes).imprimeFilmes();
                                    break;
                                case "p":
                                    edicoes.get(indexEdicoes).listarFilmesMaisPremiados();
                                    break;
                                default:
                                    System.out.println("\nPor favor selecione uma das opções disponíveis.");
                            }
                            break;
                        case "p":
                            System.out.print("\n(p): Listar Categorias\n(c): Listar Candidatos\n(o): Listar Candidatos (Ordenados por Avaliação)\n(v): Listar Vencedores\nOpção: ");
                            opcao = scan.nextLine().trim();
                            switch (opcao) {
                                case "p":
                                    edicoes.get(indexEdicoes).imprimePremios();
                                    break;
                                case "c":
                                    edicoes.get(indexEdicoes).listarCandidatos();
                                    break;
                                case "o":
                                    edicoes.get(indexEdicoes).listarPontuaçõesOrdenadas();
                                    break;
                                case "v":
                                    edicoes.get(indexEdicoes).listarVencedores();
                                    break;
                            }
                            break;
                        case "i":
                            consultarEdicoes();
                            break;
                        default:
                            System.out.println("\nPor favor selecione uma das opções disponíveis.");
                    }
                    break;
                case "h":
                    for (Premio premio : edicoes.get(indexEdicoes).getPremios()) {
                        if (premio.getVencedor() == null && edicoes.get(indexEdicoes).getPremios().indexOf(premio) != 8) {
                            System.out.println("\nAinda não avaliou este prémio.");
                            pontuarCandidatos(premio);
                        }
                    }
                    for (Ator a : atores) {
                        a.resetNumFilmesEdicaoAtual();
                    }
                    indexEdicoes++;
                    novoOuCarregar();
                    for (Ator a : atores) {
                        a.incrementaAnosCarreira();
                    }
                    quebra = false;
                    break;
                case "g":
                    System.out.print("\nOpções:\n(a): Gravar Atores e Filmes\n(c): Gravar Atores, Filmes e Candidatos\n(p): Gravar Peritos\n(t): Gravar Tudo\nOpção: ");
                    opcao = scan.nextLine().trim();
                    try {
                        switch (opcao) {
                            case "a":
                                gravaAtores();
                                gravaFilmes();
                                break;
                            case "c":
                                gravaAtores();
                                gravaFilmes();
                                gravaCandidatos();
                                break;
                            case "p":
                                gravaPeritos();
                                break;
                            case "t":
                                gravaAtores();
                                gravaFilmes();
                                gravaCandidatos();
                                gravaPeritos();
                                gravaPontuações();
                                break;
                            default:
                                System.out.println("\nPor favor selecione uma das opções disponíveis.");
                        }
                    } catch (IOException e) {
                        System.out.println("\nErro ao gravar os dados.\n");
                    }
                    break;
                case "s":
                    quebra = true;
                    break;
                default:
                    System.out.println("\nPor favor selecione uma das opções disponíveis.");
            }
        }
    }

    /**
     * Método que cria um Filme como o nome indica
     */
    private void criarFilme() {
        System.out.print("NOVO FILME\nNome do Filme: ");
        String nome = scan.nextLine().trim();
        System.out.print("Género do Filme: ");
        String genero = scan.nextLine().trim();
        System.out.print("Nome do Realizador: ");
        String nomeRealizador = scan.nextLine().trim();
        System.out.print("Género do Realizador (M-Masculino; F-Feminino): ");
        String generoRealizador = scan.nextLine().trim();
        while (!(generoRealizador.equalsIgnoreCase("M") || generoRealizador.equalsIgnoreCase("F"))) { //verifica que inseriu uma opção válida
            System.out.print("Género Inválido. Género (M-Masculino; F-Feminino): ");
            generoRealizador = scan.nextLine().trim();
        }
        Realizador realizador = new Realizador(nomeRealizador, generoRealizador.equalsIgnoreCase("M"));
        for (Edicao e : edicoes) {
            for (Filme f : e.getFilmes()) {
                if (f.getRealizador().equals(realizador)) {
                    realizador = f.getRealizador();
                }
            }
        }
        Filme filme = new Filme(nome, genero, numEdicao, realizador);
        edicoes.get(indexEdicoes).insereFilmes(filme); //insere na lista de filmes da edição atual o filme criado
    }

    /**
     * Método que cria um Ator como o nome indica
     */
    private void criarAtor() {
        System.out.print("NOVO ATOR\nNome do Ator/Atriz: ");
        String nome = scan.nextLine().trim();
        System.out.print("Ator ou Atriz (M-Masculino; F-Feminino): ");
        String genero = scan.nextLine().trim();
        while (!(genero.equalsIgnoreCase("M") || genero.equalsIgnoreCase("F"))) { //verifica que inseriu uma opção válida
            System.out.print("Género Inválido. Género (M-Masculino; F-Feminino): ");
            genero = scan.nextLine().trim();
        }
        System.out.print("Anos de carreira do Ator/Atriz: ");
        int anosCarreira = recebeInteiro();
        Ator ator = new Ator(nome, genero.equalsIgnoreCase("M"), anosCarreira);
        atores.add(ator); //adiciona o ator criado no array de atores
    }

    /**
     * Método que cria um Perito como o nome indica
     */
    private void criarPerito() {
        System.out.print("NOVO PERITO\nNome do perito: ");
        String nome = scan.nextLine().trim();
        System.out.print("Género do Perito (M-Masculino; F-Feminino): ");
        String genero = scan.nextLine().trim();
        while (!(genero.equalsIgnoreCase("M") || genero.equalsIgnoreCase("F"))) { //verifica que inseriu uma opção válida
            System.out.print("Género Inválido. Género (M-Masculino; F-Feminino): ");
            genero = scan.nextLine().trim();
        }
        Perito perito = new Perito(nome, genero.equalsIgnoreCase("M"));
        edicoes.get(indexEdicoes).inserePerito(perito);
    }

    /**
     * Método que permite atribuir a um ator já criado um papel num filme já
     * criado
     */
    private void atribuirPapel() {
        int i = 1;
        for (Ator a : atores) { //imprime os atores criados anteriormente na execução do programa
            System.out.printf("%d. %s\n", i, a.getNome());
            i++;
        }
        if (i == 1) {
            System.out.println("Ainda não foram criados atores!");
            return;
        }
        System.out.println("Qual o nome do ator/atriz que pretende inserir no filme?");
        String nomeAtor = scan.nextLine().trim();
        try {
            Ator mudar = atores.get(indexOfByActorName(nomeAtor, this.atores)); //guarda o ator ao qual atribuir um papel, se possível, caso contrário apanha a excepção e imprime uma mensagem
            if (mudar.podeInserirFilme()) { //verifica que o ator não participa em 2 filmes na edição atual
                i = 1;
                for (Filme f : edicoes.get(indexEdicoes).getFilmes()) { //imprime a lista de filmes da edição atual
                    System.out.printf("%d. %s\n", i, f.getNome());
                    i++;
                }
                if (i == 1) {
                    System.out.println("Ainda não foram criados filmes na edição atual!");
                    return;
                }
                System.out.println("Em que filme este " + (mudar.getGenero() ? "ator" : "atriz") + " participará?");
                String nomeFilme = scan.nextLine().trim();
                try {
                    Filme casting = edicoes.get(indexEdicoes).getFilmes().get(indexOfByFilmName(nomeFilme, edicoes.get(indexEdicoes).getFilmes())); //guarda o filme no qual se pretende inserir o ator
                    System.out.println("Qual o papel do ator/atriz (P-Principal ou S-Secundário)?");
                    String papel = scan.nextLine().trim();
                    while (!(papel.equalsIgnoreCase("P") || papel.equalsIgnoreCase("S"))) {//verifica que inseriu uma opção válida
                        System.out.print("Opção inválida (P-Principal ou S-Secundário).\nOpção: ");
                        papel = scan.nextLine().trim();
                    }
                    switch (papel.toLowerCase()) {
                        case "p": //se pretende-se que o ator/atriz seja principal
                            casting.insereAtor(mudar, true); //ver o método insereAtor em Filmes
                            break;
                        case "s": //se pretende-se que o ator/atriz seja secundário/a
                            casting.insereAtor(mudar, false); //ver o método insereAtor em Filmes
                            break;
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Esse filme não existe (possivelmente escreveu mal o nome).");
                }
            } else {
                System.out.println((mudar.getGenero() ? "O ator" : "A atriz") + " já participa em 2 filmes na edição atual");
            }
        } catch (IndexOutOfBoundsException e) { //no caso de o utilizador quiser selecionar um ator que não exista
            System.out.println("Esse ator não existe (possivelmente escreveu mal o nome).");
        }
    }

    /**
     * Método que permite consultar todas as edições registadas no programa
     */
    private void consultarEdicoes() {
        int aux = 0;
        if (edicoes.isEmpty()) {
            System.out.println("\nAinda não há nenhuma edição do festival!");
        } else {
            System.out.println("\nEDIÇÕES: ");
            while (aux < edicoes.size()) {
                System.out.println(edicoes.get(aux));
                aux++;
            }
        }
    }

    /**
     * Método que lista todos os Atores criados (quer carregados de um ficheiro
     * quer criados pelo teclado)
     */
    private void listarAtores() {
        boolean existe = false;
        for (Ator ator : this.atores) {
            System.out.println(ator);
            existe = true;
        }
        if (!existe) {
            System.out.println("\nNão existem atores nesta edição.");
        }
    }

    /**
     * Método que permite escolher os candidatos
     */
    private void escolherCandidatos() {
        Premio premioEscolhido = escolherPremio();
        switch (premioEscolhido.getNome()) {
            case "Melhor Ator Principal":
                escolherAtoresPrincipaisCandidatos(premioEscolhido, true);
                break;
            case "Melhor Atriz Principal":
                escolherAtoresPrincipaisCandidatos(premioEscolhido, false);
                break;
            case "Melhor Ator Secundário":
                escolherAtoresSecundariosCandidatos(premioEscolhido, true);
                break;
            case "Melhor Atriz Secundária":
                escolherAtoresSecundariosCandidatos(premioEscolhido, false);
                break;
            case "Melhor Realizador":
                escolherRealizadorCandidatos(premioEscolhido);
                break;
            case "Prémio Carreira":
                escolherPremioCarreira(premioEscolhido);
                break;
            default:
                escolherFilmesCandidatos(premioEscolhido);
                break;
        }
    }

    /**
     * Método que trata da escolha do prémio que o utilizador pretende
     * ver/atribuir candidatos, etc
     *
     * @return Premio selecionado pelo utilizador
     */
    private Premio escolherPremio() {
        System.out.println("Escolha o prémio:");
        System.out.print("(1) Melhor Ator Principal\n" + "(2) Melhor Atriz Principal\n"
                + "(3) Melhor Ator Secundário\n" + "(4) Melhor Atriz Secundária\n"
                + "(5) Melhor Filme\n"
                + "(6) Melhor Realizador\n"
                + "(7) Melhor Argumento\n"
                + "(8) Melhor Cinematografia\n"
                + "(9) Prémio Carreira"
                + "\nOPÇÃO: ");
        int opcaoPremio = recebeInteiro();
        return edicoes.get(indexEdicoes).getPremios().get(opcaoPremio - 1);
    }

    /**
     * Método que permite o utilizador escolher os filmes candidatos para um
     * dado prémio
     */
    private void escolherFilmesCandidatos(Premio p) {
        if (p.getFilmesCandidatos().size() != 4) {
            ArrayList<Filme> possiveisCandidatos = new ArrayList<>();
            int i = 1;
            int contaCandidatosPossiveis = 0;
            for (Filme filme : edicoes.get(indexEdicoes).getFilmes()) {
                System.out.printf("%d. %s\n", i, filme.getNome()); //mostra nome do realizador do filme
                if (!possiveisCandidatos.contains(filme)) {
                    contaCandidatosPossiveis++;
                }
                possiveisCandidatos.add(filme);
                
                i++;
            }
            if (contaCandidatosPossiveis < 4) {
                System.out.println("\nNão há candidatos suficientes para esta categoria.");
                return;
            }
            int indiceCandidato;
            while (p.getFilmesCandidatos().size() < 4) { //Obriga o utilizador a escolher os 4 candidatos de uma só vez            
                System.out.print("Indique o filme candidato: ");
                String nome = scan.nextLine().trim();
                try {
                    indiceCandidato = indexOfByFilmName(nome, possiveisCandidatos);
                    Filme candidato = possiveisCandidatos.get(indiceCandidato);
                    p.nomeiaFilme(candidato);
                    possiveisCandidatos.remove(possiveisCandidatos.get(indiceCandidato));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Por favor, indique um nome válido.");
                }
            }
        } else {
            System.out.println("\nOs candidatos para este prémio já foram escolhidos.\n");
        }
    }

    /**
     * método que permite o utilizador escolher os melhores realizadores
     * (devolve os filmes dos melhores realizadores)
     *
     */
    private void escolherRealizadorCandidatos(Premio p) {
        if (p.getFilmesCandidatos().size() != 4) {
            ArrayList<Realizador> possiveisCandidatos = new ArrayList<>();
            ArrayList<Filme> filmesPossiveisCandidatos = new ArrayList<>();
            int i = 1;
            int contaCandidatosPossiveis = 0;
            for (Filme filme : edicoes.get(indexEdicoes).getFilmes()) {
                System.out.printf("%d. %s por %s\n", i, filme.getRealizador().getNome(), filme.getNome()); //mostra nome do realizador do filme
                if (!possiveisCandidatos.contains(filme.getRealizador())) {
                    contaCandidatosPossiveis++;
                }
                possiveisCandidatos.add(filme.getRealizador());
                filmesPossiveisCandidatos.add(filme);
                i++;
            }
            if (contaCandidatosPossiveis < 4) {
                System.out.println("\nNão há candidatos suficientes para esta categoria.");
                return;
            }
            int indiceCandidato;
            int indiceFilmeCandidato;
            while (p.getFilmesCandidatos().size() < 4) { //Obriga o utilizador a escolher os 4 candidatos de uma só vez
                System.out.print("Indique o nome do realizador nomeado: ");
                String nome = scan.nextLine().trim();
                try {
                    for (indiceCandidato = 0; indiceCandidato < possiveisCandidatos.size(); indiceCandidato++) {
                        if (nome.equalsIgnoreCase(possiveisCandidatos.get(indiceCandidato).getNome())) {
                            break;
                        }
                    }
                    Realizador candidato = possiveisCandidatos.get(indiceCandidato);
                    if (indiceCandidato == possiveisCandidatos.lastIndexOf(candidato)) {
                        p.nomeiaFilme(filmesPossiveisCandidatos.get(indiceCandidato)); //adiciona filme do realizador
                        possiveisCandidatos.remove(indiceCandidato);
                        filmesPossiveisCandidatos.remove(indiceCandidato);
                    } else {
                        System.out.println("Esse realizador direcionou 2 ou mais filmes, a qual se refere?");
                        String nomeFilme = scan.nextLine();
                        indiceFilmeCandidato = indexOfByFilmName(nomeFilme, filmesPossiveisCandidatos);
                        Filme filme = filmesPossiveisCandidatos.get(indiceFilmeCandidato);
                        if (filme.getRealizador() == candidato) {
                            p.nomeiaFilme(filme);
                            possiveisCandidatos.remove(indiceFilmeCandidato);
                            filmesPossiveisCandidatos.remove(indiceFilmeCandidato);
                        }
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Por favor, indique um nome válido.");
                }
            }
        } else {
            System.out.println("\nOs candidatos para este prémio já foram escolhidos.\n");

        }
    }

    /**
     * método que permite o utilizador escolher os atores (e as atrizes)
     * principais
     *
     * @param homem - indica se é para escolher atores principais ou atrizes
     * principais (mesma lógica que em Ator, true-Ator, false-Atriz)
     */
    private void escolherAtoresPrincipaisCandidatos(Premio p, boolean homem) {
        if (p.getAtoresCandidatos().size() != 4) {
            ArrayList<Ator> possiveisCandidatos = new ArrayList<>();
            ArrayList<Filme> filmesPossiveisCandidatos = new ArrayList<>();
            int i = 1;
            int contaCandidatosPossiveis = 0;
            if (homem) {
                for (Filme filme : edicoes.get(indexEdicoes).getFilmes()) {
                    if (filme.getAtorPrincipal() != null) {
                        System.out.printf("%d. %s por %s\n", i, filme.getAtorPrincipal().getNome(), filme.getNome());
                        if (!possiveisCandidatos.contains(filme.getAtorPrincipal())) {
                            contaCandidatosPossiveis++;
                        }
                        possiveisCandidatos.add(filme.getAtorPrincipal());
                        filmesPossiveisCandidatos.add(filme);
                        
                        i++;
                    }
                }
            } else {
                for (Filme filme : edicoes.get(indexEdicoes).getFilmes()) {
                    if (filme.getAtrizPrincipal() != null) {
                        System.out.printf("%d. %s por %s\n", i, filme.getAtrizPrincipal().getNome(), filme.getNome());
                        if (!possiveisCandidatos.contains(filme.getAtrizPrincipal())) {
                            contaCandidatosPossiveis++;
                        }
                        possiveisCandidatos.add(filme.getAtrizPrincipal());
                        filmesPossiveisCandidatos.add(filme);
                        
                        i++;
                    }
                }
            }
            if (contaCandidatosPossiveis < 4) {
                System.out.println("\nNão há candidatos suficientes para esta categoria.");
                return;
            }
            int indiceCandidato;
            int indiceFilmeCandidato;
            while (p.getAtoresCandidatos().size() < 4) { //Obriga o utilizador a escolher os 4 candidatos de uma só vez
                System.out.printf("Indique o nome do %s candidato: ", (homem ? "ator" : "atriz"));
                String nome = scan.nextLine().trim();
                try {
                    indiceCandidato = indexOfByActorName(nome, possiveisCandidatos);
                    Ator candidato = possiveisCandidatos.get(indiceCandidato);
                    if (indiceCandidato == possiveisCandidatos.lastIndexOf(candidato)) {
                        p.nomeiaAtor(candidato, filmesPossiveisCandidatos.get(indiceCandidato));
                        possiveisCandidatos.remove(indiceCandidato);
                        filmesPossiveisCandidatos.remove(indiceCandidato);
                    } else {
                        System.out.println((homem ? "Esse ator" : "Essa atriz") + " participa em 2 filmes, a qual se refere?");
                        String nomeFilme = scan.nextLine();
                        try {
                            indiceFilmeCandidato = indexOfByFilmName(nomeFilme, filmesPossiveisCandidatos);
                            Filme filme = filmesPossiveisCandidatos.get(indiceFilmeCandidato);
                            if (homem) {
                                if (filme.getAtorPrincipal() == candidato) {
                                    p.nomeiaAtor(candidato, filme);
                                    possiveisCandidatos.remove(indiceFilmeCandidato);
                                    filmesPossiveisCandidatos.remove(indiceFilmeCandidato);
                                } else {
                                    System.out.println("Esse ator não participa no filme que inseriu.\n");
                                }
                            } else {
                                if (filme.getAtrizPrincipal() == candidato) {
                                    p.nomeiaAtor(candidato, filme);
                                    possiveisCandidatos.remove(indiceFilmeCandidato);
                                    filmesPossiveisCandidatos.remove(indiceFilmeCandidato);
                                } else {
                                    System.out.println("Essa atriz não participa no filme que inseriu.\n");
                                }
                            }
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Escreveu mal o nome do filme. Tente outra vez.");
                        }
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.printf("Por favor, escolha %s válido.\n", (homem ? "um ator" : "uma atriz"));
                }
            }
        } else {
            System.out.println("\nOs candidatos para este prémio já foram escolhidos.\n");
        }
    }

    /**
     * Método que permite o utilizador escolher os atores (ou atrizes)
     * secundários nomeados
     *
     * @param homem - indica se é para escolher atores principais ou atrizes
     * principais (mesma lógica que em Ator, true-Ator, false-Atriz
     */
    private void escolherAtoresSecundariosCandidatos(Premio p, boolean homem) {
        if (p.getAtoresCandidatos().size() != 4) {
            ArrayList<Ator> possiveisCandidatos = new ArrayList<>();
            ArrayList<Filme> filmesPossiveisCandidatos = new ArrayList<>();
            int i = 1;
            int contaCandidatosPossiveis = 0;
            for (Filme filme : edicoes.get(indexEdicoes).getFilmes()) {
                for (Ator a : filme.getAtoresSecundarios()) {
                    if (a.getGenero() == homem) {
                        System.out.printf("%d. %s em %s\n", i, a.getNome(), filme.getNome());
                        possiveisCandidatos.add(a);
                        filmesPossiveisCandidatos.add(filme);
                        if (!possiveisCandidatos.contains(a)) {
                            contaCandidatosPossiveis++;
                        }
                        i++;
                    }
                }
            }
            if (contaCandidatosPossiveis < 4) {
                System.out.println("\nNão há candidatos suficientes para esta categoria.");
                return;
            }
            int indiceCandidato;
            int indiceFilmeCandidato;
            while (p.getAtoresCandidatos().size() < 4) {  //Obriga o utilizador a escolher os 4 candidatos de uma só vez
                System.out.println("Indique o nome " + (homem ? " do ator candidato: " : "da atriz candidata: "));
                String nome = scan.nextLine().trim();
                try {
                    indiceCandidato = indexOfByActorName(nome, possiveisCandidatos);
                    Ator candidato = possiveisCandidatos.get(indiceCandidato);
                    if (indiceCandidato == possiveisCandidatos.lastIndexOf(candidato)) {
                        p.nomeiaAtor(candidato, filmesPossiveisCandidatos.get(indiceCandidato));
                        possiveisCandidatos.remove(indiceCandidato);
                        filmesPossiveisCandidatos.remove(indiceCandidato);
                    } else {
                        System.out.println((homem ? "Esse ator" : "Essa atriz") + " participa em 2 filmes, a qual se refere?");
                        String nomeFilme = scan.nextLine();
                        try {
                            indiceFilmeCandidato = indexOfByFilmName(nomeFilme, filmesPossiveisCandidatos);
                            Filme filme = filmesPossiveisCandidatos.get(indiceFilmeCandidato);
                            if (filme.getAtoresSecundarios().contains(candidato)) {
                                p.nomeiaAtor(candidato, filme);
                                possiveisCandidatos.remove(indiceFilmeCandidato);
                                filmesPossiveisCandidatos.remove(indiceFilmeCandidato);
                            } else {
                                System.out.println((homem ? "Esse ator" : "Essa atriz") + " não participa no filme que inseriu.\n");
                            }
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Escreveu mal o nome do filme. Tente outra vez.");
                        }
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.printf("Por favor, escolha %s válido.\n", (homem ? "um ator" : "uma atriz"));
                }
            }
        } else {
            System.out.println("\nOs candidatos para este prémio já foram escolhidos.\n");
        }
    }

    /**
     * Método que permite ao utilizador escolher os nomeados ao Prémio Carreira
     *
     */
    private void escolherPremioCarreira(Premio p) {
        if (p.getAtoresCandidatos().size() != 4) {
            ArrayList<Ator> possiveisCandidatos = new ArrayList<>();
            int i = 1;
            for (int posição1 = 0; posição1 < atores.size(); posição1++) {
                if (atores.get(posição1).getAnosCarreira() > 20) {
                    System.out.printf("%d. %s\n", i, atores.get(posição1).getNome());
                    possiveisCandidatos.add(atores.get(posição1));
                    i++;
                }
            }
            int indiceCandidato;
            if (i < 5) {
                System.out.println("\nNão há candidatos suficientes para esta categoria.");
                return;
            }
            while (p.getAtoresCandidatos().size() < 4) { //Obriga o utilizador a escolher os 4 candidatos de uma só vez
                System.out.print("Indique o nome do candidato: ");
                String escolhido = scan.nextLine().trim();
                try {
                    indiceCandidato = indexOfByActorName(escolhido, possiveisCandidatos);
                    Ator candidato = possiveisCandidatos.get(indiceCandidato);
                    p.nomeiaAtor(candidato);
                    possiveisCandidatos.remove(indiceCandidato);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Por favor, escolha um candidato válido.");
                }
            }
        } else {
            System.out.println("\nOs candidatos para este prémio já foram escolhidos.\n");
        }
    }

    /**
     * Método que permite pontuar os candidatos do Prémio passado como paramêtro
     *
     * @param premio - Prémio no qual se pretende pontuar os candidatos
     */
    private void pontuarCandidatos(Premio premio) {
        System.out.println("\nAVALIAÇÃO DO PRÉMIO: " + premio.toString().toUpperCase());
        if (premio.getVencedor() == null) {
            boolean pontuou = false;
            try {
                if (premio.getNome().contains("Ator") || premio.getNome().contains("Atriz") || premio.getNome().contains("Carreira")) {
                    for (int indiceCandidato = 0; indiceCandidato < premio.getAtoresCandidatos().size(); indiceCandidato++) {
                        System.out.printf("\nCANDIDATO %d: %s\n", indiceCandidato + 1, premio.getAtoresCandidatos().get(indiceCandidato).getNome());
                        for (Perito p : edicoes.get(indexEdicoes).getPeritos()) {
                            pontuou = true;
                            while (!p.inserePontuacao(premio, indiceCandidato, edicoes.get(indexEdicoes).getPeritos().indexOf(p), scan)) {
                                System.out.println("O valor precisa de ser entre 1 e 10 e inteiro!");
                            }
                        }
                    }
                } else {
                    for (int indiceCandidato = 0; indiceCandidato < premio.getFilmesCandidatos().size(); indiceCandidato++) {
                        System.out.printf("\nCANDIDATO %d: %s\n", indiceCandidato + 1, premio.getFilmesCandidatos().get(indiceCandidato).getNome());
                        for (Perito p : edicoes.get(indexEdicoes).getPeritos()) {
                            pontuou = true;
                            while (!p.inserePontuacao(premio, indiceCandidato, edicoes.get(indexEdicoes).getPeritos().indexOf(p), scan)) {
                                System.out.println("O valor precisa de ser entre 1 e 10 e inteiro!");
                            }
                        }
                    }
                }
                if (!pontuou) {
                    System.out.println("Não há candidatos para esta categoria.\n");
                    return;
                }
                premio.determinaVencedor();
            } catch (NullPointerException | IndexOutOfBoundsException e) {
                System.out.println("Não há candidatos para esta categoria.\n");
            }
        } else {
            System.out.println("Os candidatos já foram pontuados para esta categoria.\n");
        }
    }

    /**
     * Método que permite encontrar um Ator na lista de Atores pelo nome
     *
     * @param nome - nome do Ator a pesquisar na lista
     * @return Ator encontrado, se não for encontrado nenhum retorna null
     */
    private int indexOfByActorName(String nome, ArrayList<Ator> atores) {
        for (Ator a : atores) {
            if (nome.equalsIgnoreCase(a.getNome())) {
                return atores.indexOf(a);
            }
        }
        return -1;
    }

    /**
     * Método que pesquisa a lista de filmes pelo filme especificado pelo
     * parametro nome
     *
     * @param nome - nome do filme que pretende-se encontrar
     * @param filmes - lista de filmes em que procurar
     * @return o filme, se foi encontrado. Caso contrário, retorna null
     */
    private int indexOfByFilmName(String nome, ArrayList<Filme> filmes) {
        for (Filme f : filmes) {
            if (nome.equalsIgnoreCase(f.getNome())) {
                return filmes.indexOf(f);
            }
        }
        return -1;
    }

    /**
     * Método que trata de receber inteiros e a possível exceção associada
     *
     * @return o inteiro que o utilizador inserir
     */
    protected int recebeInteiro() {
        String aux;
        int num;
        while (true) {
            aux = scan.nextLine().trim();
            try {
                num = Integer.parseInt(aux);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Insira um número!");
            }
        }
        return num;
    }

    /**
     * Método responsável pelo menu de inicio após a criação de uma edição
     */
    private void novoOuCarregar() {
        while (!quebra) {
            System.out.print("(n): Começar um novo programa\n(c): Carregar dados\nOpção: ");
            opcao = scan.nextLine().trim();
            switch (opcao) {
                case "c":
                    quebra = true;
                    numEdicao++;
                    if (numEdicao > 1) {
                        ano++;
                    }
                    edicoes.add(new Edicao(numEdicao, ano));
                    System.out.print("\nOpções:\n(a): Carregar Atores e Filmes\n(c): Carregar Atores, Filmes e Candidatos\n(p): Carregar Peritos\n(t): Carregar Tudo\nOpção: ");
                    opcao = scan.nextLine().trim();
                    try {
                        switch (opcao.toLowerCase()) {
                            case "a":
                                carregaFilmes();
                                carregaAtores();
                                break;
                            case "p":
                                carregaPeritos();
                                break;
                            case "c":
                                carregaFilmes();
                                carregaAtores();
                                carregaCandidatos();// ESTE MÉTODO PRECISA DA LISTA DE FILMES E ATORES PARA FUNCIONAR!
                                break;
                            case "t":
                                carregaFilmes();
                                carregaAtores();
                                carregaPeritos();
                                carregaCandidatos();
                                carregaPontuacoes();
                                for (Premio p : edicoes.get(indexEdicoes).getPremios()) {
                                    p.determinaVencedor();
                                }
                                break;
                            default:
                                quebra = false;
                                edicoes.remove(edicoes.indexOf(edicoes.size() - 1));
                                System.out.println("\nPor favor selecione uma das opções disponíveis.");
                        }
                    } catch (IOException e) {
                        System.out.println("\nErro ao carregar os dados.");
                    }
                    break;
                case "n":
                    numEdicao++;
                    if (numEdicao > 1) {
                        ano++;
                    }
                    edicoes.add(new Edicao(numEdicao, ano));
                    quebra = true;
                    break;
                default:
                    System.out.println("\nPor favor selecione uma das opções disponíveis.");
            }
        }
    }

    //-------------------------------------------------------------------------------------------
    /**
     * Método que permite carregar os atores de um ficheiro
     */
    private void carregaAtores() throws IOException {
        Ator ator;
        String nomeAtor;
        boolean generoAtor;
        int anosCarreiraAtor;
        boolean cria;
        String line;
        int indexFilmes = 0;
        FileReader inStream = new FileReader("Edicao" + numEdicao + "\\" + ficheiroAtores);
        BufferedReader lerDados = new BufferedReader(inStream);
        line = lerDados.readLine();
        while (line != null) {
            cria = true;
            switch (line) {
                case "Ator principal:":
                    nomeAtor = lerDados.readLine().trim();
                    generoAtor = lerDados.readLine().trim().equals("M");
                    anosCarreiraAtor = Integer.parseInt(lerDados.readLine().trim());
                    if (!nomeAtor.equals("VAZIO")) {
                        ator = new Ator(nomeAtor, generoAtor, anosCarreiraAtor);
                        for (Ator a : this.atores) {
                            if (ator.equals(a)) {
                                ator = a;
                                cria = false;
                                break;
                            }
                        }
                        if (cria) {
                            atores.add(ator);
                        }
                        edicoes.get(indexEdicoes).getFilmes().get(indexFilmes).insereAtor(ator, true);
                    }
                    break;
                case "Atriz principal:":
                    nomeAtor = lerDados.readLine().trim();
                    generoAtor = lerDados.readLine().trim().equals("M");
                    anosCarreiraAtor = Integer.parseInt(lerDados.readLine().trim());
                    if (!nomeAtor.equals("VAZIO")) {
                        ator = new Ator(nomeAtor, generoAtor, anosCarreiraAtor);
                        for (Ator a : this.atores) {
                            if (ator.equals(a)) {
                                ator = a;
                                cria = false;
                                break;
                            }
                        }
                        if (cria) {
                            atores.add(ator);
                        }
                        edicoes.get(indexEdicoes).getFilmes().get(indexFilmes).insereAtor(ator, true);
                    }
                    break;
                case "Atores Secundarios:":
                    while (true) {
                        nomeAtor = lerDados.readLine();
                        if (nomeAtor != null && !nomeAtor.equals("--------------------------------")) {
                            nomeAtor = nomeAtor.trim();
                            generoAtor = lerDados.readLine().trim().equals("M");
                            anosCarreiraAtor = Integer.parseInt(lerDados.readLine().trim());
                            ator = new Ator(nomeAtor, generoAtor, anosCarreiraAtor);
                            for (Ator a : this.atores) {
                                if (ator.equals(a)) {
                                    ator = a;
                                    cria = false;
                                    break;
                                }
                            }
                            if (cria) {
                                atores.add(ator);
                            }
                            edicoes.get(indexEdicoes).getFilmes().get(indexFilmes).insereAtor(ator, false);
                        } else {
                            indexFilmes++;
                            break;
                        }
                    }
                default:
                    break;
            }
            line = lerDados.readLine();
        }
        lerDados.close();
    }

    /**
     * Método que permite carregar os filmes de um ficheiro
     */
    private void carregaFilmes() throws IOException {
        String nomeFilme;
        String generoFilme;
        String nomeRealizador;
        boolean generoRealizador;
        String line;
        FileReader inStream = new FileReader("Edicao" + numEdicao + "\\" + ficheiroFilmes);
        BufferedReader lerDados = new BufferedReader(inStream);
        line = lerDados.readLine();
        while (line != null) {
            nomeFilme = line.trim();
            generoFilme = lerDados.readLine().trim();
            nomeRealizador = lerDados.readLine().trim();
            generoRealizador = lerDados.readLine().trim().equals("M");
            Realizador realizador = new Realizador(nomeRealizador, generoRealizador);
            Filme filme = new Filme(nomeFilme, generoFilme, numEdicao, realizador);
            edicoes.get(indexEdicoes).insereFilmes(filme);
            line = lerDados.readLine();
        }
        lerDados.close();

    }

    /**
     * Método que permite carregar os peritos de um ficheiro
     */
    private void carregaPeritos() throws IOException {
        String nomePerito;
        boolean generoPerito;
        FileReader inStream = new FileReader("Edicao" + numEdicao + "\\" + ficheiroPeritos);
        BufferedReader lerDados = new BufferedReader(inStream);
        String line;
        line = lerDados.readLine();
        while (line != null) {
            nomePerito = line.trim();
            generoPerito = lerDados.readLine().trim().equals("M");
            Perito perito = new Perito(nomePerito, generoPerito);
            edicoes.get(indexEdicoes).inserePerito(perito);
            line = lerDados.readLine();
        }
        lerDados.close();

    }

    /**
     * Método que permite carregar os candidatos de um ficheiro
     */
    private void carregaCandidatos() throws IOException {
        String nomeAtor;
        String filme;
        Premio premio;
        boolean vazio;
        int indexPremios = 0;
        FileReader inStream = new FileReader("Edicao" + numEdicao + "\\" + ficheiroCandidatos);
        BufferedReader lerDados = new BufferedReader(inStream);
        String line;
        line = lerDados.readLine().trim();
        while (line != null) {
            vazio = false;
            premio = edicoes.get(indexEdicoes).getPremios().get(indexPremios);
            if (indexPremios < 4) {
                while (premio.getAtoresCandidatos().size() < 4) {
                    nomeAtor = lerDados.readLine();
                    if (nomeAtor == null || nomeAtor.contains("----------")) {
                        vazio = true;
                        break;
                    }
                    nomeAtor = nomeAtor.trim();
                    filme = lerDados.readLine().trim();
                    for (Ator a : this.atores) {
                        if (a.getNome().equals(nomeAtor)) {
                            premio.nomeiaAtor(a, a.getFilmes().get(indexOfByFilmName(filme, a.getFilmes())));
                            break;
                        }
                    }
                }
            } else if (indexPremios > 3 && indexPremios < 8) {
                while (premio.getFilmesCandidatos().size() < 4) {
                    filme = lerDados.readLine();
                    if (filme == null || filme.contains("--------")) {
                        vazio = true;
                        break;
                    }
                    filme = filme.trim();
                    for (Filme f : edicoes.get(indexEdicoes).getFilmes()) {
                        if (filme.equals(f.getNome()) || filme.equals(f.getRealizador().getNome())) {
                            premio.nomeiaFilme(f);
                            break;
                        }
                    }
                }
            } else if (indexPremios == 8) {
                while (premio.getAtoresCandidatos().size() < 4) {
                    nomeAtor = lerDados.readLine().trim();
                    if (nomeAtor == null || nomeAtor.contains("--------")) {
                        vazio = true;
                        break;
                    }
                    nomeAtor = nomeAtor.trim();
                    for (Ator a : this.atores) {
                        if (a.getNome().equals(nomeAtor) && a.getAnosCarreira() > 20) {
                            premio.nomeiaAtor(a);
                            break;
                        }
                    }
                }
            }
            indexPremios++;
            if (!vazio) {
                line = lerDados.readLine();
            }
        }
        lerDados.close();
    }

    /**
     * Método que permite carregar as pontuações de um ficheiro
     */
    private void carregaPontuacoes() throws IOException {
        int i = 0;
        int j = 0;
        String pontos = "";
        int indexPremios = -1;
        String tracinhos = "--------------------------------";
        FileReader inStream = new FileReader("Edicao" + numEdicao + "\\" + ficheiroPontuacoes);
        BufferedReader lerDados = new BufferedReader(inStream);
        String line;
        line = lerDados.readLine().trim();
        while (line != null) {
            if (!line.equals(tracinhos)) {
                for (char aux : line.toCharArray()) {
                    if (aux != '*') {
                        pontos += Character.toString(aux);
                    } else {
                        if (j % 5 == 0 && j != 0) {
                            i++;
                        }
                        edicoes.get(indexEdicoes).getPremios().get(indexPremios).setPontuacao(i % 4, j % 5, Integer.parseInt(pontos));
                        pontos = "";
                        j++;
                    }
                }
            } else {
                indexPremios++;
                i = 0;
                j = 0;
            }
            line = lerDados.readLine();
        }
        lerDados.close();
    }

    /**
     * Método que permite gravar os atores num ficheiro
     */
    private void gravaAtores() throws IOException {
        String tracinhos = "--------------------------------";
        FileWriter outStream = new FileWriter("Edicao" + numEdicao + "\\" + ficheiroAtores);
        BufferedWriter bW = new BufferedWriter(outStream);
        try (PrintWriter out = new PrintWriter(bW)) {
            for (Filme filme : edicoes.get(indexEdicoes).getFilmes()) {
                out.println(tracinhos);
                if (filme.getAtorPrincipal() != null) {
                    out.printf("Ator principal:\n%s\nM\n%d\n", filme.getAtorPrincipal().getNome(), filme.getAtorPrincipal().getAnosCarreira());
                } else {
                    out.println("Ator principal:\nVAZIO\nVAZIO\n0");
                }
                if (filme.getAtrizPrincipal() != null) {
                    out.printf("Atriz principal:\n%s\nF\n%d\n", filme.getAtrizPrincipal().getNome(), filme.getAtrizPrincipal().getAnosCarreira());
                } else {
                    out.println("Atriz principal:\nVAZIO\nVAZIO\n0");
                }
                out.println("Atores Secundarios:");
                for (Ator atorSec : filme.getAtoresSecundarios()) {
                    out.printf("%s\n%s\n%d\n", atorSec.getNome(), (atorSec.getGenero() ? "M" : "F"), atorSec.getAnosCarreira());
                }
            }
            out.close();
        }
    }

    /**
     * Método que permite gravar os filmes num ficheiro
     */
    private void gravaFilmes() throws IOException {
        FileWriter outStream = new FileWriter("Edicao" + numEdicao + "\\" + ficheiroFilmes);
        BufferedWriter bW = new BufferedWriter(outStream);
        try (PrintWriter out = new PrintWriter(bW)) {
            for (Filme filme : edicoes.get(indexEdicoes).getFilmes()) {
                out.printf("%s\n%s\n%s\n%s\n", filme.getNome(), filme.getGenero(), filme.getRealizador().getNome(), (filme.getRealizador().getGenero() ? "M" : "F"));
            }
            out.close();
        }
    }

    /**
     * Método que permite gravar os candidatos num ficheiro
     */
    private void gravaCandidatos() throws IOException {
        String tracinhos = "--------------------------------";
        FileWriter outStream = new FileWriter("Edicao" + numEdicao + "\\" + ficheiroCandidatos);
        BufferedWriter bW = new BufferedWriter(outStream);
        int indexPremio = -1;
        try (PrintWriter out = new PrintWriter(bW)) {
            for (Premio premio : edicoes.get(indexEdicoes).getPremios()) {
                out.println(tracinhos);
                indexPremio++;
                if (indexPremio < 4) {
                    for (int candidato = 0; candidato < premio.getAtoresCandidatos().size(); candidato++) {
                        out.printf("%s\n%s\n", premio.getAtoresCandidatos().get(candidato).getNome(), premio.getFilmesCandidatos().get(candidato).getNome());
                    }
                } else if (indexPremio == 5) {
                    for (int candidato = 0; candidato < premio.getFilmesCandidatos().size(); candidato++) {
                        out.println(premio.getFilmesCandidatos().get(candidato).getRealizador().getNome());
                    }
                } else if (indexPremio == 4 || (indexPremio > 5 && indexPremio < 8)) {
                    for (int candidato = 0; candidato < premio.getFilmesCandidatos().size(); candidato++) {
                        out.println(premio.getFilmesCandidatos().get(candidato).getNome());
                    }
                } else {
                    for (int candidato = 0; candidato < premio.getAtoresCandidatos().size(); candidato++) {
                        out.println(premio.getAtoresCandidatos().get(candidato).getNome());
                    }
                }
            }
            out.close();
        }
    }

    /**
     * Método que permite gravar os peritos num ficheiro
     */
    private void gravaPeritos() throws IOException {
        FileWriter outStream = new FileWriter("Edicao" + numEdicao + "\\" + ficheiroPeritos);
        BufferedWriter bW = new BufferedWriter(outStream);
        try (PrintWriter out = new PrintWriter(bW)) {
            for (Perito perito : edicoes.get(indexEdicoes).getPeritos()) {
                out.printf("%s\n%s\n", perito.getNome(), (perito.getGenero() ? "M" : "F"));
            }
            out.close();
        }
    }

    /**
     * Método que permite gravar as pontuações num ficheiro
     */
    private void gravaPontuações() throws IOException {
        String tracinhos = "--------------------------------";
        FileWriter outStream = new FileWriter("Edicao" + numEdicao + "\\" + ficheiroPontuacoes);
        BufferedWriter bW = new BufferedWriter(outStream);
        try (PrintWriter out = new PrintWriter(bW)) {
            for (Premio premio : edicoes.get(indexEdicoes).getPremios()) {
                out.println(tracinhos);
                for (int linha = 0; linha < premio.getPontuacoes().size(); linha++) {
                    for (int coluna = 0; coluna < premio.getPontuacoes().get(linha).size(); coluna++) {
                        out.print(premio.getPontuacoes().get(linha).get(coluna) + "*");
                    }
                    out.println();
                }
            }
            out.close();
        }
    }

    /**
     * Método responsavel pela limpeza da consola
     */
    private void limparConsola() {
        try {
            Robot limpa = new Robot();
            limpa.keyPress(KeyEvent.VK_CONTROL);
            limpa.keyPress(KeyEvent.VK_L);
            limpa.keyRelease(KeyEvent.VK_CONTROL);
            limpa.keyRelease(KeyEvent.VK_L);
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                System.out.println("ERRO");
            }

        } catch (AWTException e) {
            System.out.print("ERRO\n");
        }
    }
}
