package com.mycompany.festivalcinema;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
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
    private final File ficheiroPontuacoes;
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
        this.ficheiroPontuacoes = new File("pontuacoes.txt");
    }

    public void menu() {
        System.out.println("\t\t\tFESTIVAL CINEMA");
        System.out.print("(n): Começar um novo programa\n(c): Carregar\nOpção: ");
        opcao = scan.nextLine().trim();
        while (!(opcao.equalsIgnoreCase("c") || opcao.equalsIgnoreCase("n"))) {
            System.out.println("\nPor favor selecione uma das opções disponíveis.");
            System.out.print("(n): Começar um novo programa\n(c): Carregar\nOpção: ");
            opcao = scan.nextLine().trim();
        }
        System.out.print("\nIndique o ano da edição do festival: ");
        String aux;
        while (true) {
            aux = scan.nextLine().trim();
            try {
                ano = Integer.parseInt(aux);
                break;
            } catch (NumberFormatException e) {
                System.out.println("O ano deve ser um número!");
            }
        }
        if (opcao.equalsIgnoreCase("c")) {
            numEdicao++;
            edicoes.add(new Edicao(numEdicao, ano));
            ano++;
            System.out.print("\nOpções:\n(a): Carregar Atores e Filmes\n(c): Carregar Atores, Filmes e Candidatos\n(p): Carregar Peritos\n(t): Carregar Tudo\nOpção: ");
            opcao = scan.nextLine().trim();
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
                    listarCandidatos();
                    break;
                case "t":
                    insereFilmesCarregados();
                    insereAtoresCarregados();
                    inserePeritos();
                    carregaCandidatos();
                    //carregaPontuacoes();
                    break;
            }
        } else {
            numEdicao++;
            edicoes.add(new Edicao(numEdicao, ano));
            ano++;
        }
        carregaPontuacoes();

        while (!quebra) {
            limparConsola();
            System.out.printf("\t\t\t%dª Edição do Festival de Cinema %d\n", numEdicao, ano - 1);
            System.out.print("Opções:\n(c): Criar\n(l): Listar\n(h): Nova Edição\n(g): Gravar Dados\n(s): Sair\nOpção: ");
            opcao = scan.nextLine().trim();
            while (!(opcao.equalsIgnoreCase("c") || opcao.equalsIgnoreCase("l") || opcao.equalsIgnoreCase("h") || opcao.equalsIgnoreCase("g") || opcao.equalsIgnoreCase("s"))) {
                System.out.println("\nPor favor selecione uma das opções disponíveis.");
                System.out.print("(c): Criar\n(l): Listar\n(h): Nova Edição\n(g): Gravar Dados\n(s): Sair\nOpção: ");
                opcao = scan.nextLine().trim();
            }
            limparConsola();
            switch (opcao.toLowerCase()) {

                case "c":
                    System.out.print("\nOpções:\n(f): Criar Filme\n(a): Criar Ator/Atriz\n(e): Criar Perito\n(p): Atribui Papel\n(c): Escolher candidatos\n(s): Atribuir Pontuação\nOpção: ");
                    opcao = scan.nextLine().trim();
                    while (!(opcao.equalsIgnoreCase("f") || opcao.equalsIgnoreCase("a") || opcao.equalsIgnoreCase("e") || opcao.equalsIgnoreCase("p") || opcao.equalsIgnoreCase("c") || opcao.equalsIgnoreCase("s"))) {
                        System.out.println("\nPor favor selecione uma das opções disponíveis.");
                        System.out.print("\n(f): Criar Filme\n(a): Criar Ator/Atriz\n(e): Criar Perito\n(p): Atribui Papel\n(c): Escolher candidatos\n(s): Atribuir Pontuação\nOpção: ");
                        opcao = scan.nextLine().trim();
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
                                int maisUm;
                                while (true) {
                                    aux = scan.nextLine().trim();
                                    try {
                                        maisUm = Integer.parseInt(aux);
                                        break;
                                    } catch (NumberFormatException e) {
                                        System.out.println("O ano deve ser um número!");
                                    }
                                }
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
                    opcao = scan.nextLine().trim();
                    while (!(opcao.equalsIgnoreCase("a") || opcao.equalsIgnoreCase("f") || opcao.equalsIgnoreCase("p") || opcao.equalsIgnoreCase("c") || opcao.equalsIgnoreCase("i"))) {
                        System.out.println("\nPor favor selecione uma das opções disponíveis.");
                        System.out.print("\n(a): Listar Atores\n(f): Listar Filmes\n(p): Listar Prémios\n(i): Consultar Edições\nOpção: ");
                        opcao = scan.nextLine().trim();
                    }
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
                                    listarFilmes();
                                    break;
                                case "p":
                                    listarFilmesMaisPremiados();
                                    break;
                            }
                            break;
                        case "p":
                            System.out.print("\n(p): Listar Categorias\n(c): Listar Candidatos\n(o): Listar Candidatos (Ordenados por Avaliação)\n(v): Listar Vencedores\nOpção: ");
                            opcao = scan.nextLine().trim();
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
                    for (Premio premio : edicoes.get(indexEdicoes).getPremios()) {
                        if (premio.getVencedor() == null) {
                            System.out.println("\nAinda não avaliou este prémio.");
                            pontuarCandidatos(premio);
                        }
                    }

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
                case "g":
                    System.out.print("\nOpções:\n(a): Gravar Atores e Filmes\n(c): Gravar Atores, Filmes e Candidatos\n(p): Gravar Peritos\n(t): Gravar Tudo\nOpção: ");
                    opcao = scan.nextLine().trim();
                    try {
                        switch (opcao) {
                            case "a":
                                escreveAtores();
                                escreveFilmes();
                                break;
                            case "c":
                                escreveAtores();
                                escreveFilmes();
                                escreveCandidatos();
                                break;
                            case "p":
                                escrevePeritos();
                                break;
                            case "t":
                                escreveAtores();
                                escreveFilmes();
                                escreveCandidatos();
                                escrevePeritos();
                                escrevePontuações();
                                break;
                        }
                    } catch (IOException e) {
                        System.out.println("\nErro ao gravar os dados.\n");
                    }
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
        Filme filme = new Filme(nome, genero, numEdicao, realizador);
        edicoes.get(indexEdicoes).insereFilmes(filme); //insere na lista de filmes da edição atual o filme criado
    }

    /**
     * Método que cria um Ator como o nome indica
     */
    private void criarAtor() {
        System.out.print("\nNOVO ATOR\nNome do Ator/Atriz: ");
        String nome = scan.nextLine().trim();
        System.out.print("Ator ou Atriz (M-Masculino; F-Feminino): ");
        String genero = scan.nextLine().trim();
        while (!(genero.equalsIgnoreCase("M") || genero.equalsIgnoreCase("F"))) { //verifica que inseriu uma opção válida
            System.out.print("Género Inválido. Género (M-Masculino; F-Feminino): ");
            genero = scan.nextLine().trim();
        }
        System.out.print("Anos de carreira do Ator/Atriz: ");
        String aux;
        int anosCarreira;
        while (true) {
            aux = scan.nextLine().trim();
            try {
                anosCarreira = Integer.parseInt(aux);
                break;
            } catch (NumberFormatException e) {
                System.out.println("O ano deve ser um número!");
            }
        }
        Ator ator = new Ator(nome, genero.equalsIgnoreCase("M"), anosCarreira);
        atores.add(ator); //adiciona o ator criado no array de atores
    }

    /**
     * Método que cria um Perito como o nome indica
     */
    private void criarPerito() {
        System.out.print("\nNOVO PERITO\nNome do perito: ");
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
            Ator mudar = indexOfByActorName(nomeAtor); //guarda o ator ao qual atribuir um papel, se possível, caso contrário apanha a excepção e imprime uma mensagem
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
                    Filme casting = edicoes.get(indexEdicoes).indexOfByFilmName(nomeFilme); //guarda o filme no qual se pretende inserir o ator
                    System.out.println("Qual o papel do ator/atriz (P-Principal ou S-Secundário)?");
                    String papel = scan.nextLine().trim();
                    while (!(papel.equalsIgnoreCase("P") || papel.equalsIgnoreCase("S"))) {//verifica que inseriu uma opção válida
                        System.out.print("Opção inválida (P-Principal ou S-Secundário).\nOpção: ");
                        papel = scan.nextLine().trim();
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
        boolean existe = false;
        for (Ator ator : this.atores) {
            if (ator.getnumFilmesEdiçãoAtual() != 0) {
                System.out.println(ator);
                existe = true;
            }
        }

        if (!existe) {
            System.out.println("\nNenhum ator participa num filme desta edição.");
        }
    }

    /**
     * Método que lista todos os Filmes criados de uma edição à escolha do
     * utilizador(quer carregados de um ficheiro quer criados pelo teclado)
     */
    private void listarFilmes() {
        System.out.print("\nInsira o número da edição: ");
        String aux;
        int posiçãoEdição;
        while (true) {
            aux = scan.nextLine().trim();
            try {
                posiçãoEdição = Integer.parseInt(aux);
                break;
            } catch (NumberFormatException e) {
                System.out.println("O ano deve ser um número!");
            }
        }
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
                escolherAtoresPrincipaisCandidatos(true);
                break;
            case "Melhor Atriz Principal":
                escolherAtoresPrincipaisCandidatos(false);
                break;
            case "Melhor Ator Secundário":
                escolherAtoresSecundariosCandidatos(true);
                break;
            case "Melhor Atriz Secundária":
                escolherAtoresSecundariosCandidatos(false);
                break;
            case "Melhor Realizador":
                premioEscolhido.setFilmes(escolherRealizadorCandidatos());
                break;
            case "Prémio Carreira":
                premioEscolhido.setAtores(escolherPremioCarreira());
                break;
            default:
                premioEscolhido.setFilmes(escolherFilmesCandidatos());
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
        String aux;
        int opcaoPremio;
        while (true) {
            aux = scan.nextLine().trim();
            try {
                opcaoPremio = Integer.parseInt(aux);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Escreva o número do prémio que quer escolher!");
            }
        }
        return edicoes.get(indexEdicoes).getPremios().get(opcaoPremio - 1);
    }

    /**
     * Método que lista os vencedores dos prémios
     */
    private void listarVencedores() {
        System.out.println("\nVENCEDORES:");
        for (Premio premio : edicoes.get(indexEdicoes).getPremios()) {
            premio.vencedorCategoria();
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
        if (i >= 5) {
            while (filmesCandidatos.size() < 4) { //Obriga o utilizador a escolher os 4 candidatos de uma só vez            
                System.out.print("Indique o nome do filme candidato: ");
                String pos = scan.nextLine().trim();
                try {
                    Filme candidato = edicoes.get(0).indexOfByFilmName(pos);
                    filmesCandidatos.add(candidato);
                } catch (Exception e) {
                    System.out.println("Por favor, indique um nome válido.");
                }
            }
        } else {
            System.out.println("\nNão há filmes suficientes.");
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
            String pos = scan.nextLine().trim();
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
        ArrayList<Filme> filmesPossiveisCandidatos = new ArrayList<>();
        int i = 1;
        if (homem) {
            for (Filme filme : edicoes.get(indexEdicoes).getFilmes()) {
                if (filme.getAtorPrincipal() != null) {
                    System.out.printf("%d. %s por %s\n", i, filme.getAtorPrincipal().getNome(), filme.getNome());
                    possiveisCandidatos.add(filme.getAtorPrincipal());
                    filmesPossiveisCandidatos.add(filme);
                    i++;
                }
            }
        } else {
            for (Filme filme : edicoes.get(indexEdicoes).getFilmes()) {
                if (filme.getAtrizPrincipal() != null) {
                    System.out.printf("%d. %s por %s\n", i, filme.getAtrizPrincipal().getNome(), filme.getNome());
                    possiveisCandidatos.add(filme.getAtrizPrincipal());
                    filmesPossiveisCandidatos.add(filme);
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
            String nome = scan.nextLine().trim();
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
                    edicoes.get(indexEdicoes).getPremios().get(homem ? 0 : 1).nomeiaAtor(candidato, filmesPossiveisCandidatos.get(possiveisCandidatos.indexOf(candidato)));
                } else if (conta > 1) {
                    System.out.println((homem ? "Esse ator" : "Essa atriz") + " participa em 2 filmes, a qual se refere?");
                    String nomeFilme = scan.nextLine().trim();
                    Filme filme = indexOfByFilmName(nomeFilme, filmesPossiveisCandidatos);
                    if (homem) {
                        if (filme.getAtorPrincipal() == candidato) {
                            atoresCandidatos.add(candidato);
                            edicoes.get(indexEdicoes).getPremios().get(0).nomeiaAtor(candidato, filme);
                        }
                    } else {
                        if (filme.getAtrizPrincipal() == candidato) {
                            atoresCandidatos.add(candidato);
                            edicoes.get(indexEdicoes).getPremios().get(1).nomeiaAtor(candidato, filme);
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
        return null;
        //return atoresCandidatos;
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
        ArrayList<Filme> filmesPossiveisCandidatos = new ArrayList<>();
        int i = 1;
        for (Filme filme : edicoes.get(indexEdicoes).getFilmes()) {
            for (Ator a : filme.getAtoresSecundarios()) {
                if (a.getGenero() == homem) {
                    System.out.printf("%d. %s em %s\n", i, a.getNome(), filme.getNome());
                    possiveisCandidatos.add(a);
                    filmesPossiveisCandidatos.add(filme);
                    i++;
                }
            }
        }
        ArrayList<Ator> atoresCandidatos = new ArrayList<>();
        while (atoresCandidatos.size() < 4) {  //Obriga o utilizador a escolher os 4 candidatos de uma só vez
            System.out.println("Qual é o nome do " + (homem ? "ator" : "atriz") + " que pretende nomear?");
            String nome = scan.nextLine().trim();
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
                    edicoes.get(indexEdicoes).getPremios().get(homem ? 2 : 3).nomeiaAtor(candidato, filmesPossiveisCandidatos.get(possiveisCandidatos.indexOf(candidato)));
                } else if (conta > 1) {
                    System.out.println((homem ? "Esse ator" : "Essa atriz") + " participa em 2 filmes, a qual se refere?");
                    String nomeFilme = scan.nextLine().trim();
                    Filme f = indexOfByFilmName(nomeFilme, filmesPossiveisCandidatos);
                    for (Pessoa a : f.getAtoresSecundarios()) {
                        if (a == candidato) {
                            atoresCandidatos.add(candidato);
                            edicoes.get(indexEdicoes).getPremios().get(homem ? 2 : 3).nomeiaAtor(candidato, f);
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
                if (contaPremios <= 4) {
                    for (int i = 0; i < 4; i++) {
                        System.out.printf("- %s em %s\n", premio.getAtoresCandidatos().get(i).getNome(), premio.getFilmesCandidatos().get(i).getNome());
                    }
                } else if (contaPremios > 4 && contaPremios != 6 && contaPremios != 9) {
                    for (int i = 0; i < 4; i++) {
                        System.out.println("- " + premio.getFilmesCandidatos().get(i).getNome());

                    }
                } else if (contaPremios == 9) {
                    for (int i = 0; i < 4; i++) {
                        System.out.println("- " + premio.getAtoresCandidatos().get(i).getNome());
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
            String aux;
            int escolhido;
            while (true) {
                aux = scan.nextLine().trim();
                try {
                    escolhido = Integer.parseInt(aux);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("O ano deve ser um número!");
                }
            }
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
            } catch (Exception e) {
                System.out.println("Não há candidatos para esta categoria.\n");
            }
        } else {
            System.out.println("Os candidatos já foram pontuados para esta categoria.\n");
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

    private void listarFilmesMaisPremiados() {
        boolean semPremiados = true;
        System.out.println("\nFILMES MAIS PREMIADOS: ");
        for (Filme filme : edicoes.get(indexEdicoes).getFilmes()) {
            if (filme.getNumeroPremios() > 0) {
                System.out.println(filme.getNome() + ": " + filme.getNumeroPremios());
                semPremiados = false;
            }
        }
        if (semPremiados) {
            System.out.println("Nenhum filme foi premiado.");
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

    private Filme indexOfByFilmName(String nome, ArrayList<Filme> filmes) {
        for (Filme f : filmes) {
            if (nome.equalsIgnoreCase(f.getNome())) {
                return f;
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

    private void carregaCandidatos() {

        String tracinhos = "--------------------------------";
        int indexPremios = -1;
        Boolean cria;
        ArrayList<Ator> atoresA = new ArrayList<>();
        ArrayList<Filme> filmesA = new ArrayList<>();
        int y = 0;

        try {
            Scanner lerDados = new Scanner(ficheiroCandidatos, "UTF-8");
            String line;
            while (lerDados.hasNextLine()) {
                line = lerDados.nextLine();

                if (line.equals(tracinhos)) {

                    if (indexPremios != -1) {
                        if (!atoresA.isEmpty() || !filmesA.isEmpty()) {
                            filmesA.clear();
                            atoresA.clear();
                            y = 0;
                            System.out.println("Por favor insira 4 candidatos VÁLIDOS no prémio: " + edicoes.get(indexEdicoes).getPremios().get(indexPremios).getNome());
                        }

                    }
                    cria = false;
                    indexPremios++;
                } else {
                    cria = true;
                }

                if (cria) {
                    if (indexPremios < 4) {
                        for (Ator a : this.atores) {
                            if (a.getNome().equals(line)) {
                                atoresA.add(a);
                                edicoes.get(indexEdicoes).getPremios().get(indexPremios).nomeiaAtor(a, a.getFilmes().get(0));

                            }
                        }
                        if (y == 3) {
                            if (atoresA.size() == 4) {
                                atoresA.clear();
                                y = 0;
                            }
                        } else {
                            y++;
                        }
                    }

                    if (indexPremios > 3 && indexPremios < 8) {
                        for (Filme f : edicoes.get(indexEdicoes).getFilmes()) {
                            if (line.equals(f.getNome()) || line.equals(f.getRealizador().getNome())) {
                                filmesA.add(f);
                            }
                        }
                        if (y == 3) {
                            ArrayList<Filme> auxF = new ArrayList<>();
                            for (Filme e : filmesA) {
                                auxF.add(e);
                            }
                            edicoes.get(indexEdicoes).getPremios().get(indexPremios).setFilmes(auxF);

                            if (auxF.size() == 4) {
                                filmesA.clear();
                                y = 0;
                            }

                        } else {
                            y++;
                        }
                    }

                    if (indexPremios == 8) {
                        for (Ator a : this.atores) {
                            if (a.getNome().equals(line) && a.getAnosCarreira() > 20) {
                                atoresA.add(a);
                            }
                        }
                        if (y == 3) {

                            ArrayList<Ator> auxA = new ArrayList<>();
                            for (Ator e : atoresA) {
                                auxA.add(e);
                            }
                            edicoes.get(indexEdicoes).getPremios().get(indexPremios).setAtores(auxA);

                            if (auxA.size() == 4) {
                                atoresA.clear();
                                y = 0;
                            }

                        } else {
                            y++;
                        }
                    }
                }
            }
        } catch (IOException ioe) {
            System.out.println("Ocorreu um Erro");

        }
    }

    private void escreveAtores() throws IOException {
        String tracinhos = "--------------------------------";
        FileWriter outStream = new FileWriter(ficheiroAtores);
        BufferedWriter bW = new BufferedWriter(outStream);
        try ( PrintWriter out = new PrintWriter(bW)) {
            for (Filme filme : edicoes.get(indexEdicoes).getFilmes()) {
                out.println(tracinhos);
                out.println("Ator principal:");
                out.println(filme.getAtorPrincipal().getNome());
                out.println("M");
                out.println(filme.getAtorPrincipal().getAnosCarreira());
                out.println("Atriz principal:");
                out.println(filme.getAtrizPrincipal().getNome());
                out.println("F");
                out.println(filme.getAtrizPrincipal().getAnosCarreira());
                out.println("Atores Secundarios:");
                for (Ator atorSec : filme.getAtoresSecundarios()) {
                    out.println(atorSec.getNome());
                    if (atorSec.getGenero()) {
                        out.println("M");
                    } else {
                        out.println("F");
                    }
                    out.println(atorSec.getAnosCarreira());
                }
            }
        }
    }

    private void escreveFilmes() throws IOException {
        String tracinhos = "--------------------------------";
        FileWriter outStream = new FileWriter(ficheiroFilmes);
        BufferedWriter bW = new BufferedWriter(outStream);
        try ( PrintWriter out = new PrintWriter(bW)) {
            for (Filme filme : edicoes.get(indexEdicoes).getFilmes()) {
                out.println(tracinhos);
                out.println(filme.getNome());
                out.println(filme.getGenero());
                out.println(filme.getRealizador().getNome());
                if (filme.getRealizador().getGenero()) {
                    out.println("M");
                } else {
                    out.println("F");
                }
            }
        }
    }

    private void escreveCandidatos() throws IOException {
        String tracinhos = "--------------------------------";
        FileWriter outStream = new FileWriter(ficheiroCandidatos);
        BufferedWriter bW = new BufferedWriter(outStream);
        try ( PrintWriter out = new PrintWriter(bW)) {
            int indexPremio = -1;
            for (Premio premio : edicoes.get(indexEdicoes).getPremios()) {
                out.println(tracinhos);
                indexPremio++;
                if (indexPremio < 4 || indexPremio == 8) {
                    for (int candidato = 0; candidato <= 3; candidato++) {
                        out.println(premio.getAtoresCandidatos().get(candidato).getNome());
                    }
                } else if (indexPremio == 5) {
                    for (int candidato = 0; candidato <= 3; candidato++) {
                        out.println(premio.getFilmesCandidatos().get(candidato).getRealizador().getNome());
                    }
                } else {
                    for (int candidato = 0; candidato <= 3; candidato++) {
                        out.println(premio.getFilmesCandidatos().get(candidato).getNome());
                    }
                }
            }
            out.print(tracinhos);
        }
    }

    private void escrevePeritos() throws IOException {
        FileWriter outStream = new FileWriter(ficheiroPeritos);
        BufferedWriter bW = new BufferedWriter(outStream);
        try ( PrintWriter out = new PrintWriter(bW)) {
            for (Perito perito : edicoes.get(indexEdicoes).getPeritos()) {
                out.println(perito.getNome());
                if (perito.getGenero()) {
                    out.println("M");
                } else {
                    out.println("F");
                }
            }
        }
    }

    private void escrevePontuações() throws IOException {
        String tracinhos = "--------------------------------";
        FileWriter outStream = new FileWriter(ficheiroPontuacoes);
        BufferedWriter bW = new BufferedWriter(outStream);
        try (PrintWriter out = new PrintWriter(bW)) {
            for (Premio premio : edicoes.get(indexEdicoes).getPremios()) {
                out.println(tracinhos);
                for (int linha = 0; linha < 4; linha++) {
                    for (int coluna = 0; coluna < 5; coluna++) {
                        out.print(premio.getPontuacoes()[linha][coluna]+"*");
                    }
                    out.println();
                }
            }
        }
    }

    private void carregaPontuacoes() {
        String pontuacao = "";
        int i = 0;
        int j = 0;
        int indexPremios = -1;
        String tracinhos = "--------------------------------";
        try {
            Scanner lerDados = new Scanner(ficheiroPontuacoes, "UTF-8");
            String line;
            while (lerDados.hasNextLine()) {
                line = lerDados.nextLine();
                String pontos = "";
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
            }
        } catch (IOException ioe) {
            System.out.println("Ocorreu um Erro");
        }
    }

    private void limparConsola() {
        try {
            Robot limpa = new Robot();
            limpa.keyPress(KeyEvent.VK_CONTROL);
            limpa.keyPress(KeyEvent.VK_L);
            limpa.keyRelease(KeyEvent.VK_CONTROL);
            limpa.keyRelease(KeyEvent.VK_L);
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                System.out.println("ERRO");
            }

        } catch (AWTException e) {
            System.out.print("ERRO\n");
        }
    }
}
