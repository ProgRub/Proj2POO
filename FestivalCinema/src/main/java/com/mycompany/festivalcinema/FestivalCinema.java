package com.mycompany.festivalcinema;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.*;

public class FestivalCinema {

    private final ArrayList<Edicao> edicoes;
    private final ArrayList<Ator> atores;
    private int indexEdicoes;
    private int ano;
    private int numEdicao;
    private final Scanner scan;
    private boolean quebra;
    private final File ficheiroFilmes;
    private final File ficheiroAtores;
    private final File ficheiroPeritos;
    private final File ficheiroCandidatos;
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
        this.ficheiroFilmes = new File("filmes.txt");
        this.ficheiroAtores = new File("atores.txt");
        this.ficheiroPeritos = new File("peritos.txt");
        this.ficheiroCandidatos = new File("candidatos.txt");
    }

    public void menu() {
        System.out.println("\t\t\tFESTIVAL CINEMA");
        System.out.print("(n): Começar um novo programa\n(c): Carregar\nOpção: ");
        opcao = scan.nextLine();
        while (!(opcao.equalsIgnoreCase("c") || opcao.equalsIgnoreCase("n"))) {
            System.out.println("\nPor favor selecione uma das opções disponíveis.");
            System.out.print("(n): Começar um novo programa\n(c): Carregar\nOpção: ");
            opcao = scan.nextLine();
        }
        System.out.print("\nIndique o ano da edição do festival: ");
        ano = scan.nextInt();
        scan.nextLine();
        if (opcao.equalsIgnoreCase("c")) {
            numEdicao++;
            edicoes.add(new Edicao(numEdicao, ano));

            ano++;
            System.out.print("\nOpções:\n(a): Carregar Atores e Filmes\n(p): Carregar Peritos\n(c): Carregar Candidatos\n(t): Carregar Tudo\nOpção: ");
            opcao = scan.nextLine();
            switch (opcao.toLowerCase()) {
                case "a":
                    insereFilmesCarregados();
                    insereAtoresCarregados();
                    break;
                case "p":
                    inserePeritos();
                    break;
                case "c":
                    insereFilmesCarregados();
                    insereAtoresCarregados();
                    carregaCandidatos();// ESTE MÉTODO PRECISA DA LISTA DE FILMES E ATORES PARA FUNCIONAR!
                    break;
                case "t":
                    insereFilmesCarregados();
                    insereAtoresCarregados();
                    inserePeritos();
                    carregaCandidatos();
                    break;

            }
        } else {
            numEdicao++;
            edicoes.add(new Edicao(numEdicao, ano));
            ano++;
        }

        while (!quebra) {
            System.out.println("*****************************************************************");
            System.out.printf("\t\t\t%dª Edição do Festival de Cinema %d\n", numEdicao, ano - 1);
            System.out.print("Opções:\n(c): Criar\n(l): Listar\n(h): Nova Edição\n(s): Sair\nOpção: ");
            opcao = scan.nextLine();
            while (!(opcao.equalsIgnoreCase("c") || opcao.equalsIgnoreCase("l") || opcao.equalsIgnoreCase("h") || opcao.equalsIgnoreCase("s"))) {
                System.out.println("\nPor favor selecione uma das opções disponíveis.");
                System.out.print("(c): Criar\n(l): Listar\n(h): Nova Edição\n(s): Sair\nOpção: ");
                opcao = scan.nextLine();
            }
            switch (opcao.toLowerCase()) {

                case "c":
                    System.out.print("\nOpções:\n(f): Criar Filme\n(a): Criar Ator/Atriz\n(e): Criar Perito\n(p): Atribui Papel\n(c): Escolher candidatos\n(s): Atribuir Pontuação\nOpção: ");
                    opcao = scan.nextLine();
                    while (!(opcao.equalsIgnoreCase("f") || opcao.equalsIgnoreCase("a") || opcao.equalsIgnoreCase("e") || opcao.equalsIgnoreCase("p") || opcao.equalsIgnoreCase("c") || opcao.equalsIgnoreCase("s"))) {
                        System.out.println("\nPor favor selecione uma das opções disponíveis.");
                        System.out.print("\n(f): Criar Filme\n(a): Criar Ator/Atriz\n(e): Criar Perito\n(p): Atribui Papel\n(c): Escolher candidatos\n(s): Atribuir Pontuação\nOpção: ");
                        opcao = scan.nextLine();
                    }
                    switch (opcao) {
                        case "f":
                            criarFilme();
                            break;
                        case "a":
                            criarAtor();
                            break;
                        case "e":
                            boolean parar = false;
                            while (!parar && edicoes.get(indexEdicoes).getPeritos().size() < 5) {
                                criarPerito();
                                System.out.print("\nPrima 1 se pretende criar mais um perito e outro número caso contrário\nOpção: ");
                                int maisUm = scan.nextInt();
                                scan.nextLine();
                                if (maisUm != 1) {
                                    parar = true;
                                }
                            }
                            if (edicoes.get(indexEdicoes).getPeritos().size() >= 5) {
                                System.out.println("\nJá criou 5 peritos, o número máximo de peritos!");
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
                    }
                    break;
                case "l":
                    System.out.print("\nOpções:\n(a): Listar Atores\n(f): Listar Filmes\n(p): Listar Prémios\n(i): Consultar Edições\nOpção: ");
                    opcao = scan.nextLine();
                    while (!(opcao.equalsIgnoreCase("a") || opcao.equalsIgnoreCase("f") || opcao.equalsIgnoreCase("p") || opcao.equalsIgnoreCase("c") || opcao.equalsIgnoreCase("i"))) {
                        System.out.println("\nPor favor selecione uma das opções disponíveis.");
                        System.out.print("\n(a): Listar Atores\n(f): Listar Filmes\n(p): Listar Prémios\n(i): Consultar Edições\nOpção: ");
                        opcao = scan.nextLine();
                    }
                    switch (opcao) {
                        case "a":
                            listarAtores();
                            break;
                        case "f":
                            listarFilmes();
                            break;
                        case "p":
                            System.out.print("\n(p): Listar Categorias\n(c): Listar Candidatos\n(o): Listar Candidatos (Ordenados por Avaliação)\n(v): Listar Vencedores\nOpção: ");
                            opcao = scan.nextLine();
                            switch (opcao) {
                                case "p":
                                    listarPremios();
                                    break;
                                case "c":
                                    listarCandidatos();
                                    break;
                                case "o":
                                    listarPontuaçõesOrdenadas();
                                    break;
                                case "v":
                                    listarVencedores();
                                    break;
                            }
                            break;
                        case "i":
                            consultarEdicoes();
                            break;
                    }
                    break;
                case "h":
                    System.out.println("\nNOVA EDIÇÃO CRIADA.\n");
                    for (Ator a : atores) {
                        a.resetNumFilmesEdicaoAtual();
                        a.incrementaAnosCarreira();
                    }
                    numEdicao++;
                    indexEdicoes++;
                    edicoes.add(new Edicao(numEdicao, ano));
                    ano++;
                    break;
                case "s":
                    quebra = true;
                    break;
            }
        }
    }

    /**
     * Método que cria um Filme como o nome indica
     */
    private void criarFilme() {
        System.out.print("\nNOVO FILME\nNome do Filme: ");
        String nome = scan.nextLine();
        System.out.print("Género do Filme: ");
        String genero = scan.nextLine();
        System.out.print("Nome do Realizador: ");
        String nomeRealizador = scan.nextLine();
        System.out.print("Género do Realizador (M-Masculino; F-Feminino): ");
        String generoRealizador = scan.nextLine();
        while (!(generoRealizador.equalsIgnoreCase("M") || generoRealizador.equalsIgnoreCase("F"))) { //verifica que inseriu uma opção válida
            System.out.print("Género Inválido. Género (M-Masculino; F-Feminino): ");
            generoRealizador = scan.nextLine();
        }
        Realizador realizador = new Realizador(nomeRealizador, generoRealizador.equalsIgnoreCase("M"));
        Filme filme = new Filme(nome, genero, numEdicao, realizador);
        edicoes.get(indexEdicoes).insereFilmes(filme); //insere na lista de filmes da edição atual o filme criado
    }

    /**
     * Método que cria um Ator como o nome indica
     */
    private void criarAtor() {
        System.out.print("\nNOVO ATOR\nNome do Ator/Atriz: ");
        String nome = scan.nextLine();
        System.out.print("Ator ou Atriz (M-Masculino; F-Feminino): ");
        String genero = scan.nextLine();
        while (!(genero.equalsIgnoreCase("M") || genero.equalsIgnoreCase("F"))) { //verifica que inseriu uma opção válida
            System.out.print("Género Inválido. Género (M-Masculino; F-Feminino): ");
            genero = scan.nextLine();
        }
        System.out.print("Anos de carreira do Ator/Atriz: ");
        int anosCarreira = scan.nextInt();
        scan.nextLine(); //discarda o enter
        Ator ator = new Ator(nome, genero.equalsIgnoreCase("M"), anosCarreira);
        atores.add(ator); //adiciona o ator criado no array de atores
    }

    /**
     * Método que cria um Perito como o nome indica
     */
    private void criarPerito() {
        System.out.print("\nNOVO PERITO\nNome do perito: ");
        String nome = scan.nextLine();
        System.out.print("Género do Perito (M-Masculino; F-Feminino): ");
        String genero = scan.nextLine();
        while (!(genero.equalsIgnoreCase("M") || genero.equalsIgnoreCase("F"))) { //verifica que inseriu uma opção válida
            System.out.print("Género Inválido. Género (M-Masculino; F-Feminino): ");
            genero = scan.nextLine();
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
        System.out.println("Qual o nome do ator/atriz que pretende inserir no filme?");
        String nomeAtor = scan.nextLine();
        try {
            Ator mudar = indexOfByActorName(nomeAtor); //guarda o ator ao qual atribuir um papel, se possível, caso contrário apanha a excepção e imprime uma mensagem
            if (mudar.podeInserirFilme()) { //verifica que o ator não participa em 2 filmes na edição atual
                i = 1;
                for (Filme f : edicoes.get(indexEdicoes).getFilmes()) { //imprime a lista de filmes da edição atual
                    System.out.printf("%d. %s\n", i, f.getNome());
                    i++;
                }
                System.out.println("Em que filme este " + (mudar.getGenero() ? "ator" : "atriz") + " participará?");
                String nomeFilme = scan.nextLine();
                try {
                    Filme casting = edicoes.get(indexEdicoes).indexOfByFilmName(nomeFilme); //guarda o filme no qual se pretende inserir o ator
                    System.out.println("Qual o papel do ator/atriz (P-Principal ou S-Secundário)?");
                    String papel = scan.nextLine();
                    while (!(papel.equalsIgnoreCase("P") || papel.equalsIgnoreCase("S"))) {//verifica que inseriu uma opção válida
                        System.out.print("Opção inválida (P-Principal ou S-Secundário).\nOpção: ");
                        papel = scan.nextLine();
                    }
                    switch (papel.toLowerCase()) {
                        case "p": //se pretende-se que o ator/atriz seja principal
                            casting.insereAtor(mudar, mudar.getGenero() ? 0 : 1); //ver o método insereAtor em Filmes
                            break;
                        case "s": //se pretende-se que o ator/atriz seja secundário/a
                            casting.insereAtor(mudar, 2); //ver o método insereAtor em Filmes
                            break;
                    }
                } catch (NullPointerException e) {
                    System.out.println("Esse filme não existe (possivelmente escreveu mal o nome).");
                }
            } else {
                System.out.println((mudar.getGenero() ? "O ator" : "A atriz") + " já participa em 2 filmes na edição atual");
            }
        } catch (NullPointerException e) { //no caso de o utilizador quiser selecionar um ator que não exista
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
        if (!atores.isEmpty()) {
            for (Ator ator : this.atores) {
                System.out.println(ator);
            }
        } else {
            System.out.println("Não existem atores nesta edição.");
        }
    }

    /**
     * Método que lista todos os Filmes criados de uma edição à escolha do
     * utilizador(quer carregados de um ficheiro quer criados pelo teclado)
     */
    private void listarFilmes() {
        System.out.print("\nInsira o número da edição: ");
        int posiçãoEdição = scan.nextInt();
        scan.nextLine();
        try {
            System.out.println("\nEDIÇÃO: " + edicoes.get(posiçãoEdição - 1).getNumEdicao());
            if (edicoes.get(posiçãoEdição - 1).getFilmes().isEmpty()) {
                System.out.println("Não existem filmes nesta edição.\n");
            } else {
                edicoes.get(posiçãoEdição - 1).imprimeFilmes();
            }
        } catch (Exception e) {
            System.out.println("Essa edição não existe.");
        }
    }

    /**
     * Método que lista os Premios a premiar na edição atual
     */
    private void listarPremios() {
        System.out.println("\nCATEGORIAS A PREMIAR:");
        edicoes.get(indexEdicoes).imprimePremios();
    }

    /**
     * Método que permite escolher os candidatos
     */
    private void escolherCandidatos() {
        Premio premioEscolhido = escolherPremio();
        switch (premioEscolhido.getNome()) {
            case "Melhor Ator Principal":
                premioEscolhido.setAtores(escolherAtoresPrincipaisCandidatos(true));  //primeira ediçao apenas (mudar)
                break;
            case "Melhor Atriz Principal":
                premioEscolhido.setAtores(escolherAtoresPrincipaisCandidatos(false));  //primeira ediçao apenas (mudar)
                break;
            case "Melhor Ator Secundário":
                premioEscolhido.setAtores(escolherAtoresSecundariosCandidatos(true));  //primeira ediçao apenas (mudar)
                break;
            case "Melhor Atriz Secundária":
                premioEscolhido.setAtores(escolherAtoresSecundariosCandidatos(false));
                break;
            case "Melhor Realizador":
                premioEscolhido.setFilmes(escolherRealizadorCandidatos());  //primeira ediçao apenas (mudar)
                break;
            case "Prémio Carreira":
                premioEscolhido.setAtores(escolherPremioCarreira());  //primeira ediçao apenas (mudar)
                break;
            default:
                premioEscolhido.setFilmes(escolherFilmesCandidatos());  //primeira ediçao apenas (mudar)
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
        int opcaoPremio = scan.nextInt();
        scan.nextLine();
        return edicoes.get(indexEdicoes).getPremios().get(opcaoPremio - 1);
    }

    /**
     * Método que lista os vencedores dos prémios
     */
    private void listarVencedores() {
        System.out.println("\nVENCEDORES:");
        for (Premio premio : edicoes.get(indexEdicoes).getPremios()) {
            premio.vencedorCategoria(premio.getPontuacoes());
        }
    }

    /**
     * Método que permite o utilizador escolher os filmes candidatos para um
     * dado prémio
     *
     * @return a lista dos filmes nomeados ao prémio
     */
    private ArrayList<Filme> escolherFilmesCandidatos() {
        ArrayList<Filme> filmesCandidatos = new ArrayList<>();
        int i = 1;
        for (Filme filme : edicoes.get(indexEdicoes).getFilmes()) {
            System.out.printf("%d. %s\n", i, filme.getNome()); //mostra nome do realizador do filme
            i++;
        }
        while (filmesCandidatos.size() < 4) { //Obriga o utilizador a escolher os 4 candidatos de uma só vez            
            System.out.print("Indique o filme candidato: ");
            String pos = scan.nextLine();
            try {
                Filme candidato = edicoes.get(0).indexOfByFilmName(pos);
                filmesCandidatos.add(candidato);
            } catch (Exception e) {
                System.out.println("Por favor, indique um nome válido.");
            }
        }
        // System.out.print(filmesCandidatos); //PARA TESTAR
        return filmesCandidatos;
    }

    /**
     * método que permite o utilizador escolher os melhores realizadores
     * (devolve os filmes dos melhores realizadores)
     *
     * @return lista dos filmes que os realizadores que o utilizador nomeou
     * direcionam
     */
    private ArrayList<Filme> escolherRealizadorCandidatos() {
        ArrayList<Filme> filmesCandidatos = new ArrayList<>();
        int i = 1;
        for (Filme filme : edicoes.get(indexEdicoes).getFilmes()) {
            System.out.printf("%d. %s por %s\n", i, filme.getRealizador().getNome(), filme.getNome()); //mostra nome do realizador do filme
            i++;
        }
        while (filmesCandidatos.size() < 4) { //Obriga o utilizador a escolher os 4 candidatos de uma só vez
            System.out.print("Indique o filme do realizador candidato: ");
            String pos = scan.nextLine();
            try {
                Filme candidato = edicoes.get(indexEdicoes).indexOfByFilmName(pos);
                filmesCandidatos.add(candidato); //adiciona filme do realizador
            } catch (Exception e) {
                System.out.println("Por favor, indique um nome válido.");
            }
        }
        // System.out.print(filmesCandidatos); //PARA TESTAR
        return filmesCandidatos;
    }

    /**
     * método que permite o utilizador escolher os atores (e as atrizes)
     * principais
     *
     * @param homem - indica se é para escolher atores principais ou atrizes
     * principais (mesma lógica que em Ator, true-Ator, false-Atriz
     * @return lista dos atores/atrizes nomeados ao Prémio
     */
    private ArrayList<Ator> escolherAtoresPrincipaisCandidatos(boolean homem) {
        ArrayList<Ator> atoresCandidatos = new ArrayList<>();
        ArrayList<Ator> possiveisCandidatos = new ArrayList<>();
        int i = 1;
        if (homem) {
            for (Filme filme : edicoes.get(indexEdicoes).getFilmes()) {
                if (filme.getAtorPrincipal() != null) {
                    System.out.printf("%d. %s por %s\n", i, filme.getAtorPrincipal().getNome(), filme.getNome());
                    possiveisCandidatos.add(filme.getAtorPrincipal());
                    i++;
                }
            }
        } else {
            for (Filme filme : edicoes.get(indexEdicoes).getFilmes()) {
                if (filme.getAtrizPrincipal() != null) {
                    System.out.printf("%d. %s por %s\n", i, filme.getAtrizPrincipal().getNome(), filme.getNome());
                    possiveisCandidatos.add(filme.getAtrizPrincipal());
                    i++;
                }
            }
        }
        while (atoresCandidatos.size() < 4) { //Obriga o utilizador a escolher os 4 candidatos de uma só vez
            if (homem) {
                System.out.print("Indique o nome do ator candidato: ");
            } else {
                System.out.print("Indique o nome da atriz candidata: ");
            }
            String nome = scan.nextLine();
            try {
                Ator candidato = indexOfByActorName(nome);
                int conta = 0;
                for (Ator a : possiveisCandidatos) {
                    if (a == candidato) {
                        conta++;
                    }
                }
                if (conta == 1) {
                    atoresCandidatos.add(candidato);
                } else if (conta > 1) {
                    System.out.println(conta);
                    System.out.println((homem ? "Esse ator" : "Essa atriz") + " participa em 2 filmes, a qual se refere?");
                    String nomeFilme = scan.nextLine();
                    Filme filme = edicoes.get(indexEdicoes).indexOfByFilmName(nomeFilme);
                    if (homem) {
                        if (filme.getAtorPrincipal() == candidato) {
                            atoresCandidatos.add(candidato);
                        }
                    } else {
                        if (filme.getAtrizPrincipal() == candidato) {
                            atoresCandidatos.add(candidato);
                        }
                    }
                } else {
                    System.out.println("Escreveu mal o nome do ator, tente outra vez.");
                }
            } catch (Exception e) {
                System.out.println("Por favor, escolha um ator válido.");
            }
        }
        //System.out.print(atoresCandidatos); //PARA TESTAR
        return atoresCandidatos;
    }

    /**
     * Método que permite o utilizador escolher os atores (ou atrizes)
     * secundários nomeados
     *
     * @param homem - indica se é para escolher atores principais ou atrizes
     * principais (mesma lógica que em Ator, true-Ator, false-Atriz
     * @return lista dos atores/atrizes nomeados ao Prémio
     */
    private ArrayList<Ator> escolherAtoresSecundariosCandidatos(boolean homem) {
        ArrayList<Ator> possiveisCandidatos = new ArrayList<>();
        int i = 1;
        for (Filme filme : edicoes.get(indexEdicoes).getFilmes()) {
            for (Ator a : filme.getAtoresSecundarios()) {
                if (a.getGenero() == homem) {
                    System.out.printf("%d. %s em %s\n", i, a.getNome(), filme.getNome());
                    possiveisCandidatos.add(a);
                    System.out.print(a);
                    i++;
                }
            }
        }
        ArrayList<Ator> atoresCandidatos = new ArrayList<>();
        while (atoresCandidatos.size() < 4) {  //Obriga o utilizador a escolher os 4 candidatos de uma só vez
            System.out.println("Qual é o nome do " + (homem ? "ator" : "atriz") + " que pretende nomear?");
            String nome = scan.nextLine();
            try {
                Ator candidato = indexOfByActorName(nome);
                int conta = 0;
                for (Ator a : possiveisCandidatos) {
                    if (a == candidato) {
                        conta++;
                    }
                }
                if (conta == 1) {
                    atoresCandidatos.add(candidato);
                } else if (conta > 1) {
                    System.out.println((homem ? "Esse ator" : "Essa atriz") + " participa em 2 filmes, a qual se refere?");
                    String nomeFilme = scan.nextLine();
                    Filme f = edicoes.get(indexEdicoes).indexOfByFilmName(nomeFilme);
                    for (Pessoa a : f.getAtoresSecundarios()) {
                        if (a == candidato) {
                            atoresCandidatos.add(candidato);
                        }
                    }
                } else {
                    System.out.println("Escreveu mal o nome do ator, tente outra vez.");
                }
            } catch (Exception e) {
                System.out.println("Por favor, escolha um ator válido.");
            }
        }
        return atoresCandidatos;
    }

    /**
     * Método que mostra os quatro candidatos ao prémio em cada categoria
     */
    private void listarCandidatos() {
        int contaPremios = 1;
        System.out.println("\nCANDIDATOS AOS PRÉMIOS:");

        for (Premio premio : edicoes.get(indexEdicoes).getPremios()) {
            System.out.println(" ");
            System.out.println(premio + ":");
            try {
                if (contaPremios <= 4 || contaPremios == 9) {
                    for (int i = 0; i < 4; i++) {
                        System.out.println("- " + premio.getAtoresCandidatos().get(i).getNome());
                    }
                } else if (contaPremios > 4 && contaPremios != 6 && contaPremios != 9) {
                    for (int i = 0; i < 4; i++) {
                        System.out.println("- " + premio.getFilmesCandidatos().get(i).getNome());

                    }
                } else {
                    for (int i = 0; i < 4; i++) {
                        System.out.println("- " + premio.getFilmesCandidatos().get(i).getRealizador().getNome());
                    }
                }
            } catch (Exception e) {
                System.out.println("- Não tem candidatos.\n");
            }
            contaPremios++;
        }
    }

    /**
     * Método que permite ao utilizador escolher os nomeados ao Prémio Carreira
     *
     * @return
     */
    private ArrayList<Ator> escolherPremioCarreira() {
        ArrayList<Ator> atoresCandidatos = new ArrayList<>();
        while (atoresCandidatos.size() < 4) { //Obriga o utilizador a escolher os 4 candidatos de uma só vez
            int i = 1;
            for (int posição1 = 0; posição1 < atores.size(); posição1++) {
                if (atores.get(posição1).getAnosCarreira() > 20) {
                    System.out.printf("%d. %s\n", i, atores.get(posição1).getNome());
                    i++;
                }
            }
            System.out.println("Escolha um candidato: ");
            int escolhido = scan.nextInt();
            scan.nextLine();
            try {
                int procura = 1; //procurar o atores escolhido na lista
                for (int posição2 = 0; posição2 < atores.size(); posição2++) {
                    if (atores.get(posição2).getAnosCarreira() > 20) { //procura entre os atores com mais de 20 anos de carreira
                        if (procura == escolhido) { //se for o escolhido
                            Ator candidato = atores.get(posição2);
                            atoresCandidatos.add(candidato);
                            posição2 = atores.size(); //acabar de procurar
                        } else {
                            procura++; //continua a procurar
                        }
                    }
                }

            } catch (Exception e) {
                System.out.println("Por favor, escolha um candidato válido.");
            }
        }
        //System.out.print(atoresCandidatos); //PARA TESTAR
        return atoresCandidatos;
    }

    /**
     * Método que permite pontuar os candidatos do Prémio passado como paramêtro
     *
     * @param premio - Prémio no qual se pretende pontuar os candidatos
     */
    private void pontuarCandidatos(Premio premio) {
        System.out.println("\nAVALIAÇÃO DO PRÉMIO " + premio.toString().toUpperCase());
        if (premio.getNome().contains("Ator") || premio.getNome().contains("Atriz")) {
            for (int indiceCandidato = 0; indiceCandidato < premio.getAtoresCandidatos().size(); indiceCandidato++) {
                System.out.printf("CANDIDATO %d: %s\n", indiceCandidato + 1, premio.getAtoresCandidatos().get(indiceCandidato).getNome());
                for (Perito p : edicoes.get(indexEdicoes).getPeritos()) {
                    while (!p.inserePontuacao(premio, indiceCandidato, edicoes.get(indexEdicoes).getPeritos().indexOf(p), scan)) {
                        System.out.println("O valor precisa de ser entre 1 e 10 e inteiro!");
                    }
                }
                System.out.println("CHEGOU");
            }
        } else if (!premio.getNome().contains("Carreira")) {
            for (int indiceCandidato = 0; indiceCandidato < premio.getFilmesCandidatos().size(); indiceCandidato++) {
                System.out.printf("CANDIDATO %d: %s\n", indiceCandidato + 1, premio.getFilmesCandidatos().get(indiceCandidato).getNome());
                for (Perito p : edicoes.get(indexEdicoes).getPeritos()) {
                    while (!p.inserePontuacao(premio, indiceCandidato, edicoes.get(indexEdicoes).getPeritos().indexOf(p), scan)) {
                        System.out.println("O valor precisa de ser entre 1 e 10 e inteiro!");
                    }
                }
            }
        }
    }

    /**
     * Método que, como o nome indica, lista os candidatos de cada prémio pela
     * ordem da sua pntuação
     */
    private void listarPontuaçõesOrdenadas() {
        System.out.println("\nPONTUAÇÕES: ");
        for (Premio premio : edicoes.get(indexEdicoes).getPremios()) {
            premio.imprimePontuações(premio.getPontuacoes());
        }
    }

    /**
     * Método que permite encontrar um Ator na lista de Atores pelo nome
     *
     * @param nome - nome do Ator a pesquisar na lista
     * @return Ator encontrado, se não for encontrado nenhum retorna null
     */
    private Ator indexOfByActorName(String nome) {
        for (Ator a : this.atores) {
            if (nome.equalsIgnoreCase(a.getNome())) {
                return a;
            }
        }
        return null;
    }

    //-------------------------------------------------------------------------------------------
    private void insereAtoresCarregados() {
        String nomeAtor = "";
        boolean generoAtor = false;
        int anosCarreiraAtor;
        boolean criarAtorP = false;
        boolean criarAtrizP = false;
        boolean criarAtoresS = false;
        boolean cria;
        int i = 0;
        int indexFilmes = -1;

        try {
            Scanner lerDados = new Scanner(ficheiroAtores, "UTF-8");
            String line;
            while (lerDados.hasNextLine()) {
                line = lerDados.nextLine();
                cria = true;
                if (line.equals("--------------------------------")) {
                    criarAtoresS = false;
                    indexFilmes++;
                }
                if (criarAtorP) {

                    switch (i) {
                        case 0:
                            nomeAtor = line;
                            break;
                        case 1:
                            generoAtor = line.equals("M");
                            break;

                        case 2:
                            anosCarreiraAtor = Integer.parseInt(line);
                            Ator ator = new Ator(nomeAtor, generoAtor, anosCarreiraAtor);
                            for (Ator a : this.atores) {
                                //if (ator == a) {
                                if (equals(ator, a)) {
                                    ator = a;
                                    cria = false;
                                    break;
                                }
                            }
                            if (cria) {
                                atores.add(ator);
                            }
                            edicoes.get(indexEdicoes).getFilmes().get(indexFilmes).insereAtor(ator, 0);

                            criarAtorP = false;
                            break;
                    }
                    if (i == 2) {
                        i = 0;
                    } else {
                        i++;
                    }
                }

                if (criarAtrizP) {

                    switch (i) {
                        case 0:
                            nomeAtor = line;
                            break;
                        case 1:
                            generoAtor = line.equals("M");
                            break;
                        case 2:
                            anosCarreiraAtor = Integer.parseInt(line);
                            Ator ator = new Ator(nomeAtor, generoAtor, anosCarreiraAtor);
                            for (Ator a : this.atores) {
                                //if (ator == a) {
                                if (equals(ator, a)) {
                                    ator = a;
                                    cria = false;
                                    break;
                                }
                            }
                            if (cria) {
                                atores.add(ator);
                            }
                            edicoes.get(indexEdicoes).getFilmes().get(indexFilmes).insereAtor(ator, 1);

                            criarAtrizP = false;
                            break;
                    }
                    if (i == 2) {
                        i = 0;
                    } else {
                        i++;
                    }
                }

                if (criarAtoresS) {

                    switch (i) {
                        case 0:
                            nomeAtor = line;
                            break;
                        case 1:
                            generoAtor = line.equals("M");
                            break;
                        case 2:
                            anosCarreiraAtor = Integer.parseInt(line);
                            Ator ator = new Ator(nomeAtor, generoAtor, anosCarreiraAtor);
                            for (Ator a : this.atores) {
                                //if (ator == a) {
                                if (equals(ator, a)) {
                                    ator = a;
                                    cria = false;
                                    break;
                                }
                            }
                            if (cria) {
                                atores.add(ator);
                            }
                            edicoes.get(indexEdicoes).getFilmes().get(indexFilmes).insereAtor(ator, 2);
                            break;
                    }
                    if (i == 2) {
                        i = 0;
                    } else {
                        i++;
                    }
                }
                switch (line) {
                    case "Ator principal:":
                        criarAtorP = true;
                        break;
                    case "Atriz principal:":
                        criarAtrizP = true;
                        break;
                    case "Atores Secundarios:":
                        criarAtoresS = true;
                        break;
                    default:
                        break;
                }
            }
        } catch (IOException ioe) {
            System.out.println("Ocorreu um Erro");
        }
    }

    private void insereFilmesCarregados() {
        String nomeFilme = "";
        String generoFilme = "";
        String nomeRealizador = "";
        Boolean generoRealizador;
        int i = 0;
        String tracinhos = "--------------------------------";
        Boolean criarNovoFilme = false;

        try {
            Scanner lerDados = new Scanner(ficheiroFilmes, "UTF-8");
            String line;
            while (lerDados.hasNextLine()) {
                line = lerDados.nextLine();
                if (criarNovoFilme) {
                    switch (i) {
                        case 0:
                            nomeFilme = line;
                            break;
                        case 1:
                            generoFilme = line;
                            break;
                        case 2:
                            nomeRealizador = line;
                            break;
                        case 3:
                            generoRealizador = line.equals("M");
                            criarNovoFilme = false;
                            Realizador realizador = new Realizador(nomeRealizador, generoRealizador);
                            Filme filme = new Filme(nomeFilme, generoFilme, numEdicao, realizador);
                            edicoes.get(indexEdicoes).insereFilmes(filme);

                            break;
                    }
                    if (i == 3) {
                        i = 0;
                    } else {
                        i++;
                    }
                }
                if (line.equals(tracinhos)) {
                    criarNovoFilme = true;
                }
            }
        } catch (IOException ioe) {
            System.out.println("Ocorreu um Erro");
        }
    }

    private boolean equals(Ator a, Ator b) {
        return a.getAnosCarreira() == b.getAnosCarreira() && a.getGenero() == b.getGenero() && a.getNome().equals(b.getNome());
    }

    private void inserePeritos() {

        String nomePerito = " ";
        boolean generoPerito;
        int i = 0;

        try {
            Scanner lerDados = new Scanner(ficheiroPeritos, "UTF-8");
            String line;
            while (lerDados.hasNextLine()) {
                line = lerDados.nextLine();
                switch (i) {
                    case 0:
                        nomePerito = line;
                        break;
                    case 1:
                        generoPerito = line.equals("M");
                        Perito perito = new Perito(nomePerito, generoPerito);
                        edicoes.get(indexEdicoes).inserePerito(perito);
                        break;
                }
                if (i == 1) {
                    i = 0;
                } else {
                    i++;
                }
            }
        } catch (IOException ioe) {
            System.out.println("Ocorreu um Erro");
        }

    }

    
    
    private void imprimirPeritos() {
        for (int i = 0; i < edicoes.get(indexEdicoes).getPeritos().size(); i++) {
            System.out.println(edicoes.get(indexEdicoes).getPeritos().get(i));
        }
    }
    
    private void carregaCandidatos() {

        String tracinhos = "--------------------------------";
        int indexPremios = -1;
        Boolean cria;
        ArrayList<Ator> atoresA = new ArrayList<Ator>();
        ArrayList<Filme> filmesA = new ArrayList<Filme>();  
        int y =0;
        int w =0;
        //int i =0;
        
        
        try {
            Scanner lerDados = new Scanner(ficheiroCandidatos, "UTF-8");
            String line;
            while (lerDados.hasNextLine()) {
                line = lerDados.nextLine();
                
                if (line.equals(tracinhos)) {                   
                    cria = false;
                    indexPremios++;
                }                
                else{
                    cria = true;
                }
                
                if (cria){
                    if (indexPremios <4 || indexPremios == 8){                                               
                        for (Ator a : this.atores) {                           
                            if(a.getNome().equals(line)){  
                                atoresA.add(a);    
                            }                           
                        } 
                        if(y==3){
                            
                            ArrayList<Ator> auxA = new ArrayList<Ator>();                          
                            for(Ator e : atoresA){
                                auxA.add(e);
                            }
                            edicoes.get(indexEdicoes).getPremios().get(indexPremios).setAtores(auxA);
                            atoresA.clear();
                            y=0;
                        }
                        
                        else{
                            y++;
                        }                        
                    }

                    if (indexPremios >3 && indexPremios<8){
                        for (Filme f : edicoes.get(indexEdicoes).getFilmes()){
                            if(line.equals(f.getNome()) || line.equals(f.getRealizador().getNome())){
                                filmesA.add(f);                                
                            }                      
                        }  
                        if(y==3){
                            ArrayList<Filme> auxF = new ArrayList<Filme>();                          
                            for(Filme e : filmesA){
                                auxF.add(e);
                            }
                            edicoes.get(indexEdicoes).getPremios().get(indexPremios).setFilmes(auxF);
                            filmesA.clear();
                            y=0;
                        }
                        
                        else{
                            y++;
                        }      
                    }                   
                }            
            }
        } catch (IOException ioe) {
            System.out.println("Ocorreu um Erro");
            
        }           
    }

}
