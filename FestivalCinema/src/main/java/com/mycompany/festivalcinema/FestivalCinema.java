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
    private int ano;
    private int numEdicao; //numEdicao - 1 dá o índice da edição na lista edicoes da edicao com número numEdicao
    private final Scanner scan;
    private boolean quebra;
    private String opcao;

    public FestivalCinema() {
        this.edicoes = new ArrayList<>();
        this.atores = new ArrayList<>();
        this.opcao = "";
        this.ano = 0;
        this.numEdicao = 0;
        this.scan = new Scanner(System.in, "cp1252");
        this.quebra = false;
    }

    /**
     * O método que trata do display das opções possíveis, de receber a opção
     * que o utilizador escolheu e chamar o método que trata da operação que o
     * utilizador escolheu
     */
    public void menu() {
        System.out.println("\t\t\tFESTIVAL CINEMA");
        System.out.print("\nIndique o ano da edição do festival: ");
        ano = recebeInteiro();
        novoOuCarregar();
        quebra = false;
        limparConsola();
        while (!quebra) {
            System.out.print("Opções:\n(c): Criar\n(l): Listar\n(p): Atribuir Papel\n(h): Nova Edição\n(g): Gravar Dados\n(s): Sair\nOpção: ");
            opcao = scan.nextLine().trim().toLowerCase();
            limparConsola();
            switch (opcao.toLowerCase()) {
                case "c":
                    System.out.print("Opções:\n(f): Criar Filme\n(a): Criar Ator/Atriz\n(p): Criar Perito\n(c): Escolher candidatos\n(s): Atribuir Pontuação\nOpção: ");
                    opcao = scan.nextLine().trim().toLowerCase();
                    limparConsola();
                    switch (opcao) {
                        case "f":
                            criarFilme();
                            break;
                        case "a":
                            criarAtor();
                            break;
                        case "p":
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
                        case "c":
                            escolherCandidatos();
                            break;
                        case "s":
                            pontuarCandidatos(escolherPremio());
                            break;
                        default:
                            System.out.println("Por favor selecione uma das opções disponíveis.");
                    }
                    break;
                case "l":
                    System.out.print("Opções:\n(a): Listar Atores\n(f): Listar Filmes\n(p): Listar Prémios\n(i): Consultar Edições\nOpção: ");
                    opcao = scan.nextLine().trim().toLowerCase();
                    limparConsola();
                    switch (opcao) {
                        case "a":
                            System.out.print("Opções:\n(a): Listar da Edição Atual\n(t): Listar de todas as Edições\nOpção: ");
                            opcao = scan.nextLine().trim().toLowerCase();
                            limparConsola();
                            switch (opcao) {
                                case "a":
                                    listarAtores(true);
                                    break;
                                case "t":
                                    listarAtores(false);
                                    break;
                            }
                            break;
                        case "f":
                            System.out.print("Opções:\n(f): Listar Filmes\n(p): Listar Filmes Mais Premiados\nOpção: ");
                            opcao = scan.nextLine().trim();
                            limparConsola();
                            switch (opcao) {
                                case "f":
                                    boolean imprimiu = false;
                                    consultarEdicoes();
                                    while (!imprimiu) {
                                        try {
                                            System.out.print("\nInsira o número da edição.\nOpção: ");
                                            int opcaoPremio = recebeInteiro();
                                            edicoes.get(opcaoPremio - 1).imprimeFilmes();
                                            imprimiu = true;
                                        } catch (IndexOutOfBoundsException e) {
                                            System.out.print("\nEssa edição não existe.\n");
                                        }
                                    }
                                    break;
                                case "p":
                                    edicoes.get(numEdicao - 1).listarFilmesMaisPremiados();
                                    break;
                            }
                            break;

                        case "p":
                            System.out.print("Opções:\n(p): Listar Categorias\n(c): Listar Candidatos\n(o): Listar Candidatos (Ordenados por Avaliação)\n(v): Listar Vencedores\nOpção: ");
                            opcao = scan.nextLine().trim().toLowerCase();
                            limparConsola();
                            switch (opcao) {
                                case "p":
                                    edicoes.get(numEdicao - 1).imprimePremios();
                                    break;
                                case "c":
                                    edicoes.get(numEdicao - 1).listarCandidatos();
                                    break;
                                case "o":
                                    edicoes.get(numEdicao - 1).listarPontuaçõesOrdenadas();
                                    break;
                                case "v":
                                    edicoes.get(numEdicao - 1).listarVencedores();
                                    break;
                                default:
                                    System.out.println("Por favor selecione uma das opções disponíveis.");
                            }
                            break;
                        case "i":
                            consultarEdicoes();
                            break;
                        default:
                            System.out.println("Por favor selecione uma das opções disponíveis.");
                    }
                    break;
                case "p":
                    atribuirPapel();
                    break;
                case "h":
                    for (Ator a : atores) {
                        a.resetFilmesEdicaoAtual();
                    }
                    for (Ator a : atores) {
                        a.incrementaAnosCarreira();
                    }
                    novoOuCarregar();
                    quebra = false;
                    break;
                case "g":
                    System.out.print("Opções:\n(a): Gravar Atores e Filmes\n(c): Gravar Atores, Filmes e Candidatos\n(p): Gravar Peritos\n(t): Gravar Tudo\nOpção: ");
                    opcao = scan.nextLine().trim().toLowerCase();
                    limparConsola();
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
                                System.out.println("Por favor selecione uma das opções disponíveis.");
                        }
                    } catch (IOException e) {
                        System.out.println("Erro ao gravar os dados.\n");
                    }
                    break;
                case "s":
                    quebra = true;
                    break;
                default:
                    System.out.println("Por favor selecione uma das opções disponíveis.");
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
            for (Filme f : e.getFilmes()) { //percorrer os filmes previamente criados para verificar se o realizador já existe
                if (f.getRealizador().equals(realizador)) { //se o realizador criado já existia
                    realizador = f.getRealizador(); //o realizador do novo filme aponta para mesmo realizador anteriormente criado
                }
            }
        }
        Filme filme = new Filme(nome, genero, realizador);
        if (!edicoes.get(numEdicao - 1).getFilmes().contains(filme)) { //se o filme criado não existir na edição
            edicoes.get(numEdicao - 1).insereFilmes(filme); //insere na lista de filmes da edição atual o filme criado
        } else {
            System.out.println("Esse filme já existe na edição.");
        }
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
        if (!atores.contains(ator)) { //verifica se o ator criado já existe
            atores.add(ator); //adiciona o ator criado no array de atores
        } else {
            System.out.println((genero.equalsIgnoreCase("M") ? "Esse ator " : "Essa atriz ") + "já existe!");
        }
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
        if (edicoes.get(numEdicao - 1).getPeritos().contains(perito)) { //verifica se o perito criado já existe
            System.out.println("Esse perito já existe.");
            return;
        }
        edicoes.get(numEdicao - 1).inserePerito(perito); //insere o perito na lista de peritos da edição, caso ele não exista
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
                for (Filme f : edicoes.get(numEdicao - 1).getFilmes()) { //imprime a lista de filmes da edição atual
                    System.out.printf("%d. %s\n", i, f.getNome());
                    i++;
                }
                if (i == 1) {
                    System.out.println("Ainda não foram criados filmes na edição atual!");
                    return;
                }
                System.out.println("Em que filme " + (mudar.getGenero() ? "este ator" : "esta atriz") + " participará?");
                String nomeFilme = scan.nextLine().trim();
                try {
                    Filme casting = edicoes.get(numEdicao - 1).getFilmes().get(indexOfByFilmName(nomeFilme, edicoes.get(numEdicao - 1).getFilmes())); //guarda o filme no qual se pretende inserir o ator
                    String papel = "";
                    while (!(papel.equalsIgnoreCase("P") || papel.equalsIgnoreCase("S"))) {//verifica que inseriu uma opção válida
                        System.out.println("Qual o papel do ator/atriz (P-Principal ou S-Secundário)?");
                        papel = scan.nextLine().trim();
                        switch (papel.toLowerCase()) {
                            case "p": //se pretende-se que o ator/atriz seja principal
                                casting.insereAtor(mudar, true); //ver o método insereAtor em Filmes
                                break;
                            case "s": //se pretende-se que o ator/atriz seja secundário/a
                                casting.insereAtor(mudar, false); //ver o método insereAtor em Filmes
                                break;
                            default:
                                System.out.println("Opção inválida.");
                        }
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Esse filme não existe (possivelmente escreveu mal o nome).");
                }
            } else {
                System.out.println((mudar.getGenero() ? "O ator" : "A atriz") + " já participa em 2 filmes na edição atual!");
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
            System.out.println("Ainda não há nenhuma edição do festival!");
        } else {
            System.out.println("EDIÇÕES: ");
            while (aux < edicoes.size()) {
                System.out.println(edicoes.get(aux));
                aux++;
            }
        }
    }

    /**
     * Método que lista todos os Atores criados (quer carregados de um ficheiro
     * quer criados pelo teclado)
     *
     * @param atual - se for false, então é para imprimir todos os atores
     * previamente criados no programa, se for true só se imprime os atores que
     * participam em filmes na edição corrente
     */
    private void listarAtores(boolean atual) {
        boolean existe = false; //false se não existem atores na edição
        for (Ator ator : this.atores) {
            if (ator.getnumFilmesEdiçãoAtual() > 0 && atual) {
                System.out.println(ator);
                existe = true;
            } else if (!atual) {
                System.out.println(ator);
                existe = true;
            }
        }
        if (!existe && atual) {
            System.out.println("Não existem atores que participem num filme desta edição.");
        } else if (!existe && !atual) {
            System.out.println("Não existem atores no festival.");
        }
    }

    /**
     * Método que permite escolher os candidatos
     */
    private void escolherCandidatos() {
        Premio premioEscolhido = escolherPremio();
        switch (premioEscolhido.getNome()) { //verifica o nome do premio escolhido pelo utilizador
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
            default: //se o prémio for algum filme
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
                + "\nOpção: ");
        while (true) { //até não escolher uma opção válida
            try {
                int opcaoPremio = recebeInteiro();
                return edicoes.get(numEdicao - 1).getPremios().get(opcaoPremio - 1);
            } catch (IndexOutOfBoundsException e) {
                System.out.print("Escolha uma opção válida.\nOpção: ");
            }
        }
    }

    /**
     * Método que permite o utilizador escolher os filmes candidatos para um
     * dado prémio
     *
     * @param p - prémio ao qual pretende-se nomear filmes
     */
    private void escolherFilmesCandidatos(Premio p) {
        if (p.getFilmesCandidatos().size() == 4) { //se já foram escolhidos os candidatos
            System.out.println("Os candidatos para este prémio já foram escolhidos.\n");
            return;
        }
        ArrayList<Filme> possiveisCandidatos = new ArrayList<>(); //lista com os candidatos possiveis
        int contaCandidatosPossiveis = 0;
        for (Filme filme : edicoes.get(numEdicao - 1).getFilmes()) {
            possiveisCandidatos.add(filme);
            contaCandidatosPossiveis++;
        }
        if (contaCandidatosPossiveis < 4) { //se não há pelo menos 4 candidatos
            System.out.println("\nNão há candidatos suficientes para esta categoria.");
            return; //não continua
        }
        int indiceCandidato;
        while (p.getFilmesCandidatos().size() < 4) { //Obriga o utilizador a escolher os 4 candidatos de uma só vez   
            for (int j = 0; j < possiveisCandidatos.size(); j++) {
                System.out.printf("%d. %s\n", j + 1, possiveisCandidatos.get(j).getNome());
            }
            System.out.print("Indique o filme candidato: ");
            String nome = scan.nextLine().trim();
            limparConsola();
            try {
                indiceCandidato = indexOfByFilmName(nome, possiveisCandidatos); //indice do filme candidato, na lista dos possiveis candidatos
                Filme candidato = possiveisCandidatos.get(indiceCandidato);
                p.nomeiaFilme(candidato); //insere o filme candidato no prémio em questão
                possiveisCandidatos.remove(possiveisCandidatos.get(indiceCandidato)); //remove o filme escolhido, da lista dos candidatos. assim, não pode ser escolhido novamente
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Por favor, indique um nome válido.");
            }
        }
    }

    /**
     * Método que permite o utilizador escolher os melhores realizadores
     *
     * @param p - prémio ao qual pretende-se nomear realizadores
     */
    private void escolherRealizadorCandidatos(Premio p) {
        if (p.getFilmesCandidatos().size() != 4) { //se ainda não foram escolhidos os candidatos
            ArrayList<Realizador> possiveisCandidatos = new ArrayList<>(); //lista com os realizadores candidatos
            ArrayList<Filme> filmesPossiveisCandidatos = new ArrayList<>(); //lista com os filmes dos realizadores candidatos
            int contaCandidatosPossiveis = 0;
            for (Filme filme : edicoes.get(numEdicao - 1).getFilmes()) {
                if (!possiveisCandidatos.contains(filme.getRealizador())) { //se o realizador participa em 2 ou mais filmes, só conta como 1 candidato
                    contaCandidatosPossiveis++;
                }
                possiveisCandidatos.add(filme.getRealizador());
                filmesPossiveisCandidatos.add(filme);
            }
            if (contaCandidatosPossiveis < 4) { //se não há pelo menos 4 candidatos
                System.out.println("\nNão há candidatos suficientes para esta categoria.");
                return;
            }
            int indiceCandidato;
            int indiceFilmeCandidato;
            while (p.getFilmesCandidatos().size() < 4) { //Obriga o utilizador a escolher os 4 candidatos de uma só vez
                for (int j = 0; j < possiveisCandidatos.size(); j++) {
                    System.out.printf("%d. %s por %s\n", j + 1, possiveisCandidatos.get(j).getNome(), filmesPossiveisCandidatos.get(j).getNome());
                }
                System.out.print("Indique o nome do realizador nomeado: ");
                String nome = scan.nextLine().trim();
                try {
                    for (indiceCandidato = 0; indiceCandidato < possiveisCandidatos.size(); indiceCandidato++) { //procura o indice do realizador escolhido
                        if (nome.equalsIgnoreCase(possiveisCandidatos.get(indiceCandidato).getNome())) {
                            break; //para no indice do realizador escolhido
                        }
                    }
                    Realizador candidato = possiveisCandidatos.get(indiceCandidato);
                    if (indiceCandidato == possiveisCandidatos.lastIndexOf(candidato)) { //se o realizador apenas aparece 1 vez na lista de possiveis candidatos
                        limparConsola();
                        p.nomeiaFilme(filmesPossiveisCandidatos.get(indiceCandidato)); //adiciona filme do realizador
                        possiveisCandidatos.remove(indiceCandidato); //remove o realizador escolhido dos possiveis candidatos
                        filmesPossiveisCandidatos.remove(indiceCandidato); //remove o filme do realizador escolhido dos possiveis candidatos
                    } else {
                        System.out.println("Esse realizador direcionou 2 ou mais filmes, a qual se refere?");
                        String nomeFilme = scan.nextLine().trim();
                        limparConsola();
                        indiceFilmeCandidato = indexOfByFilmName(nomeFilme, filmesPossiveisCandidatos);
                        Filme filme = filmesPossiveisCandidatos.get(indiceFilmeCandidato);
                        if (filme.getRealizador() == candidato) {
                            p.nomeiaFilme(filme); //insere o filme do realizador escolhido no prémio em questão
                            possiveisCandidatos.remove(indiceFilmeCandidato); //remove o realizador escolhido dos possiveis candidatos
                            filmesPossiveisCandidatos.remove(indiceFilmeCandidato); //remove o filme do realizador escolhido dos possiveis candidatos
                            while (possiveisCandidatos.contains(candidato)) { //até o realizador não estar na lista dos possiveis candidatos
                                indiceCandidato = possiveisCandidatos.indexOf(candidato);
                                possiveisCandidatos.remove(candidato); //remove todas as ocorrências do realizador escolhido na lista dos possiveis candidatos
                                filmesPossiveisCandidatos.remove(indiceCandidato);//remove o filme do realizador escolhido dos possiveis candidatos
                            }
                        }
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Por favor, indique um nome válido.");
                }
            }
        } else {
            System.out.println("Os candidatos para este prémio já foram escolhidos.\n");
        }
    }

    /**
     * Método que permite o utilizador escolher os atores (ou as atrizes)
     * principais
     *
     * @param p - prémio ao qual se pretende nomear atores
     * @param homem - indica se é para escolher atores principais ou atrizes
     * principais (mesma lógica que em Ator, true-Ator, false-Atriz)
     */
    private void escolherAtoresPrincipaisCandidatos(Premio p, boolean homem) {
        if (p.getAtoresCandidatos().size() != 4) { //se ainda não foram escolhidos os candidatos
            ArrayList<Ator> possiveisCandidatos = new ArrayList<>(); //lista com os possiveis atores candidatos
            ArrayList<Filme> filmesPossiveisCandidatos = new ArrayList<>(); //lista com os filmes dos possiveis atores candidatos
            int contaCandidatosPossiveis = 0;
            if (homem) {
                for (Filme filme : edicoes.get(numEdicao - 1).getFilmes()) {
                    if (filme.getAtorPrincipal() != null) {
                        if (!possiveisCandidatos.contains(filme.getAtorPrincipal())) { //se o ator aparece em mais do que 1 filme, como ator principal, só conta como 1 candidato
                            contaCandidatosPossiveis++;
                        }
                        possiveisCandidatos.add(filme.getAtorPrincipal());
                        filmesPossiveisCandidatos.add(filme);
                    }
                }
            } else {
                for (Filme filme : edicoes.get(numEdicao - 1).getFilmes()) {
                    if (filme.getAtrizPrincipal() != null) {
                        if (!possiveisCandidatos.contains(filme.getAtrizPrincipal())) {//se a atriz aparece em mais do que 1 filme, como atriz principal, só conta como 1 candidat0
                            contaCandidatosPossiveis++;
                        }
                        possiveisCandidatos.add(filme.getAtrizPrincipal());
                        filmesPossiveisCandidatos.add(filme);
                    }
                }
            }
            if (contaCandidatosPossiveis < 4) { //se há menos de 4 candidatos diferentes
                System.out.println("\nNão há candidatos suficientes para esta categoria.");
                return;
            }
            int indiceCandidato;
            int indiceFilmeCandidato;
            while (p.getAtoresCandidatos().size() < 4) { //Obriga o utilizador a escolher os 4 candidatos de uma só vez
                for (int j = 0; j < possiveisCandidatos.size(); j++) {
                    System.out.printf("%d. %s por %s\n", j + 1, possiveisCandidatos.get(j).getNome(), filmesPossiveisCandidatos.get(j).getNome());
                }
                System.out.printf("Indique o nome do %s candidato: ", (homem ? "ator" : "atriz"));
                String nome = scan.nextLine().trim();
                try {
                    indiceCandidato = indexOfByActorName(nome, possiveisCandidatos); //posição do ator/atriz candidato na lista dos possiveis candidatos
                    Ator candidato = possiveisCandidatos.get(indiceCandidato); //ator/atriz correspondente ao indice
                    if (indiceCandidato == possiveisCandidatos.lastIndexOf(candidato)) { //se o ator/atriz só aparece uma vez na lista de candidatos (participa apenas num filme como principal)
                        limparConsola();
                        p.nomeiaAtor(candidato, filmesPossiveisCandidatos.get(indiceCandidato)); //insere o ator/atriz na lista de candidatos do prémio
                        possiveisCandidatos.remove(indiceCandidato); //remove o ator/atriz da lista de candidatos possiveis
                        filmesPossiveisCandidatos.remove(indiceCandidato); //remove o filme do ator/atriz da lista de candidatos possiveis
                    } else {
                        System.out.println((homem ? "Esse ator" : "Essa atriz") + " participa em 2 filmes, a qual se refere?");
                        String nomeFilme = scan.nextLine().trim();
                        limparConsola();
                        try {
                            indiceFilmeCandidato = indexOfByFilmName(nomeFilme, filmesPossiveisCandidatos); //posicao do filme do ator/atriz candidato na lista de filmes candidatos
                            Filme filme = filmesPossiveisCandidatos.get(indiceFilmeCandidato); //filme correspondente ao indice
                            if (homem) { //se for ator principal
                                if (filme.getAtorPrincipal() == candidato) { //se o ator principal desse filme for o ator principal escolhido
                                    p.nomeiaAtor(candidato, filme); //coloca o ator nos candidatos do prémio
                                    possiveisCandidatos.remove(indiceFilmeCandidato); //remove o ator da lista dos possiveis candidatos (do filme escolhido)
                                    indiceCandidato = possiveisCandidatos.indexOf(candidato); //indice do ator da lista dos possiveis candidatos
                                    possiveisCandidatos.remove(candidato); //remove o ator da lista dos possiveis candidatos
                                    filmesPossiveisCandidatos.remove(indiceFilmeCandidato); //remove o filme que foi escolhido do ator da lista de possiveis candidatos
                                    filmesPossiveisCandidatos.remove(indiceCandidato); //remove o filme que não foi escolhido
                                } else {
                                    System.out.println("Esse ator não participa no filme que inseriu.\n");
                                }
                            } else {
                                if (filme.getAtrizPrincipal() == candidato) { //se a atriz principal desse filme for o ator principal escolhido
                                    p.nomeiaAtor(candidato, filme); //coloca a atriz nos candidatos do prémio
                                    possiveisCandidatos.remove(indiceFilmeCandidato); //remove a atriz da lista dos possiveis candidatos (do filme escolhido)
                                    indiceCandidato = possiveisCandidatos.indexOf(candidato); //indice da atriz da lista dos possiveis candidatos
                                    possiveisCandidatos.remove(candidato); //remove a atriz da lista dos possiveis candidatos
                                    filmesPossiveisCandidatos.remove(indiceFilmeCandidato);  //remove o filme que foi escolhido da atriz da lista de possiveis candidatos
                                    filmesPossiveisCandidatos.remove(indiceCandidato); //remove o filme que não foi escolhido
                                } else {
                                    System.out.println("Essa atriz não participa no filme que inseriu.\n");
                                }
                            }
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Escreveu mal o nome do filme. Tente outra vez.");
                        }
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.printf("Por favor, escolha %s.\n\n", (homem ? "um ator válido" : "uma atriz válida"));
                }
            }
        } else {
            System.out.println("Os candidatos para este prémio já foram escolhidos.\n");
        }
    }

    /**
     * Método que permite o utilizador escolher os atores (ou as atrizes)
     * secundários
     *
     * @param p - prémio ao qual se pretende nomear atores
     * @param homem - indica se é para escolher atores secundários ou atrizes
     * secundárias (mesma lógica que em Ator, true-Ator, false-Atriz)
     */
    private void escolherAtoresSecundariosCandidatos(Premio p, boolean homem) {
        if (p.getAtoresCandidatos().size() != 4) { //se ainda não foram escolhidos os candidatos
            ArrayList<Ator> possiveisCandidatos = new ArrayList<>(); //lista com os possiveis atores candidatos
            ArrayList<Filme> filmesPossiveisCandidatos = new ArrayList<>(); //lista com os filmes dos possiveis atores candidatos
            int contaCandidatosPossiveis = 0;
            for (Filme filme : edicoes.get(numEdicao - 1).getFilmes()) {
                for (Ator a : filme.getAtoresSecundarios()) {
                    if (a.getGenero() == homem) {
                        if (!possiveisCandidatos.contains(a)) { //se o ator aparece em mais do que 1 filme, só conta como 1 candidato
                            contaCandidatosPossiveis++;
                        }
                        possiveisCandidatos.add(a);
                        filmesPossiveisCandidatos.add(filme);
                    }
                }
            }
            if (contaCandidatosPossiveis < 4) { //se há menos de 4 candidatos diferentes
                System.out.println("\nNão há candidatos suficientes para esta categoria.");
                return;
            }
            int indiceCandidato;
            int indiceFilmeCandidato;
            while (p.getAtoresCandidatos().size() < 4) {  //Obriga o utilizador a escolher os 4 candidatos de uma só vez
                for (int j = 0; j < possiveisCandidatos.size(); j++) {
                    System.out.printf("%d. %s por %s\n", j + 1, possiveisCandidatos.get(j).getNome(), filmesPossiveisCandidatos.get(j).getNome());
                }
                System.out.println("Indique o nome " + (homem ? " do ator candidato: " : "da atriz candidata: "));
                String nome = scan.nextLine().trim();
                try {
                    indiceCandidato = indexOfByActorName(nome, possiveisCandidatos); //posicao do filme do ator candidato na lista de filmes candidatos
                    Ator candidato = possiveisCandidatos.get(indiceCandidato); //ator correspondente ao indice
                    if (indiceCandidato == possiveisCandidatos.lastIndexOf(candidato)) { //se o ator/atriz só participa num filme
                        limparConsola();
                        p.nomeiaAtor(candidato, filmesPossiveisCandidatos.get(indiceCandidato));
                        possiveisCandidatos.remove(indiceCandidato); //remove o ator dos possiveis candidatos
                        filmesPossiveisCandidatos.remove(indiceCandidato); //remove o filme do ator dos filmes candidatos
                    } else {
                        System.out.println((homem ? "Esse ator" : "Essa atriz") + " participa em 2 filmes, a qual se refere?");
                        String nomeFilme = scan.nextLine().trim();
                        limparConsola();
                        try {
                            indiceFilmeCandidato = indexOfByFilmName(nomeFilme, filmesPossiveisCandidatos); //posicao do filme escolhido
                            Filme filme = filmesPossiveisCandidatos.get(indiceFilmeCandidato);  //filme escolhido
                            if (filme.getAtoresSecundarios().contains(candidato)) { //se o ator escolhido pertence a esse filme
                                p.nomeiaAtor(candidato, filme); //insere na lista de candidatos do premio o ator escolhido
                                possiveisCandidatos.remove(indiceFilmeCandidato); //remove o ator dos possiveis candidatos
                                indiceCandidato = possiveisCandidatos.indexOf(candidato);
                                possiveisCandidatos.remove(candidato); //remove a outra ocorrencia do ator nos possiveis candidatos (o outro filme)
                                filmesPossiveisCandidatos.remove(indiceFilmeCandidato); //remove o filme escolhido
                                filmesPossiveisCandidatos.remove(indiceCandidato); //remove o outro filme do ator
                            } else {
                                System.out.println((homem ? "Esse ator" : "Essa atriz") + " não participa no filme que inseriu.\n");
                            }
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Escreveu mal o nome do filme. Tente outra vez.");
                        }
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.printf("Por favor, escolha %s.\n", (homem ? "um ator válido" : "uma atriz válida"));
                }
            }
        } else {
            System.out.println("Os candidatos para este prémio já foram escolhidos.\n");
        }
    }

    /**
     * Método que permite ao utilizador escolher os nomeados ao Prémio Carreira
     *
     * @param p - prémio ao qual se pretende nomear os atores
     */
    private void escolherPremioCarreira(Premio p) {
        if (p.getAtoresCandidatos().size() != 4) { //se ainda não foram escolhidos os candidatos
            ArrayList<Ator> possiveisCandidatos = new ArrayList<>();
            int contaCandidatosPossiveis = 0;
            for (int posição1 = 0; posição1 < atores.size(); posição1++) {
                if (atores.get(posição1).getAnosCarreira() > 20) { //se o ator tem mais de 20 anos de carreira
                    possiveisCandidatos.add(atores.get(posição1));
                    contaCandidatosPossiveis++;
                }
            }
            int indiceCandidato;
            if (contaCandidatosPossiveis < 4) { //se há pelo menos 4 candidatos
                System.out.println("\nNão há candidatos suficientes para esta categoria.");
                return;
            }
            while (p.getAtoresCandidatos().size() < 4) { //Obriga o utilizador a escolher os 4 candidatos de uma só vez
                for (int j = 0; j < possiveisCandidatos.size(); j++) {
                    System.out.printf("%d. %s\n", j + 1, possiveisCandidatos.get(j).getNome());
                }
                System.out.print("Indique o nome do candidato: ");
                String escolhido = scan.nextLine().trim();
                limparConsola();
                try {
                    indiceCandidato = indexOfByActorName(escolhido, possiveisCandidatos); //posicao do ator candidato na lista de candidatos
                    Ator candidato = possiveisCandidatos.get(indiceCandidato); //ator correspondente
                    p.nomeiaAtor(candidato); //insere o ator nos candidatos do prémio
                    possiveisCandidatos.remove(indiceCandidato); //remove o ator dos possiveis candidatos
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Por favor, escolha um candidato válido.");
                }
            }
        } else {
            System.out.println("Os candidatos para este prémio já foram escolhidos.\n");
        }
    }

    /**
     * Método que permite pontuar os candidatos do Prémio passado como paramêtro
     *
     * @param premio - Prémio no qual se pretende pontuar os candidatos
     */
    private void pontuarCandidatos(Premio premio) {
        System.out.println("AVALIAÇÃO DO PRÉMIO: " + premio.toString().toUpperCase());
        if (premio.getNome().contains("Carreira")) {
            if (premio.getMediasPontuacoes()[0] != 0 && !Double.isNaN(premio.getMediasPontuacoes()[0])) { //verificar se o prémio carreira já foi pontuado (pois este não atualiza a variável vencedor do prémio)
                System.out.println("Os candidatos já foram pontuados para esta categoria.\n");
                return;
            }
        }
        if (premio.getVencedor() == null) { //se não for o prémio carreira, verifica-se se o vencedor é diferente de nulo, o que significa que o prémio ainda não foi pontuado
            boolean pontuou = false; //se esta variável permanecer falsa significa que não há peritos criados para avaliar os prémios
            try {
                if (premio.getNome().contains("Ator") || premio.getNome().contains("Atriz") || premio.getNome().contains("Carreira")) {//verificar se é um prémio para atores
                    for (int indiceCandidato = 0; indiceCandidato < 4; indiceCandidato++) { //percorrer a lista de candidatos
                        if (premio.getNome().contains("Carreira")) {
                            System.out.printf("\nCANDIDATO %d: %s\n", indiceCandidato + 1, premio.getAtoresCandidatos().get(indiceCandidato).getNome());
                        } else {
                            System.out.printf("\nCANDIDATO %d: %s em %s\n", indiceCandidato + 1, premio.getAtoresCandidatos().get(indiceCandidato).getNome(), premio.getFilmesCandidatos().get(indiceCandidato).getNome());
                        }
                        for (Perito p : edicoes.get(numEdicao - 1).getPeritos()) { //percorrer a lista de peritos para estes pontuarem o candidato em questão
                            pontuou = true; //se entra dentro deste ciclo então há peritos e atualiza-se esta variável para true
                            while (!p.inserePontuacao(premio, indiceCandidato, edicoes.get(numEdicao - 1).getPeritos().indexOf(p), scan)) { //se esta função retornar false quer dizer que o valor foi inválido
                                System.out.println("O valor precisa de ser entre 1 e 10 (inclusive) e inteiro!");
                            }//quando a função inserePontuacao retornar true significa que a pontuação foi válida
                        }
                    }
                } else {
                    for (int indiceCandidato = 0; indiceCandidato < 4; indiceCandidato++) { //percorrer a lista de candidatos
                        if (premio.getNome().contains("Realizador")) { //verificar se é o prémio de Melhor Realizador
                            System.out.printf("\nCANDIDATO %d: %s por %s\n", indiceCandidato + 1, premio.getFilmesCandidatos().get(indiceCandidato).getRealizador().getNome(), premio.getFilmesCandidatos().get(indiceCandidato).getNome());
                        } else {
                            System.out.printf("\nCANDIDATO %d: %s\n", indiceCandidato + 1, premio.getFilmesCandidatos().get(indiceCandidato).getNome());
                        }
                        for (Perito p : edicoes.get(numEdicao - 1).getPeritos()) { //percorrer a lista de peritos para pontuarem os candidatos
                            pontuou = true;//se entra dentro deste ciclo então há peritos e atualiza-se esta variável para true
                            while (!p.inserePontuacao(premio, indiceCandidato, edicoes.get(numEdicao - 1).getPeritos().indexOf(p), scan)) {//se esta função retornar false quer dizer que o valor foi inválido
                                System.out.println("O valor precisa de ser entre 1 e 10 (inclusive) e inteiro!");
                            }//quando a função inserePontuacao retornar true significa que a pontuação foi válida
                        }
                    }
                }
                limparConsola();
                if (!pontuou) { //se pontuou é false, como já foi dito, não há peritos para pontuar o prémio e informa-se o utilizador
                    System.out.println("Não há peritos para pontuar esta categoria.\n");
                    return;
                }
                premio.ordenaPontuações(); //agora que as pontuações foram dadas ao prémio chama-se esta função para calcular as médias e verificar quem venceu, etc.
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Não há candidatos para esta categoria.\n");
            }
        } else { //se o prémio já tem vencedor então os candidatos já foram pontuados
            System.out.println("Os candidatos já foram pontuados para esta categoria.\n");
        }
    }

    /**
     * Método que permite encontrar o índice de um Ator numa lista de atores
     * pelo nome
     *
     * @param nome - nome do Ator a pesquisar na lista
     * @param atores - lista na qual procurar o ator
     * @return o índice do ator na lista, se o ator não está na lista retorna-se
     * -1
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
     * @return o índice do filme, se foi encontrado. Caso contrário, retorna -1
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
     * @return o inteiro que o utilizador inseriu
     */
    private int recebeInteiro() {
        String aux;
        int num;
        while (true) {//não sai do while enquanto o utilizador não inserir um número
            aux = scan.nextLine().trim();
            try {
                num = Integer.parseInt(aux);
                break;//só chega aqui se o parseInt não atirar a NumberFormatException
            } catch (NumberFormatException e) {//entra aqui se não é inserido um número
                System.out.println("Insira um número inteiro!");
            }
        }
        return num;
    }

    /**
     * Método responsável pelo menu de inicio após a criação de uma edição
     */
    private void novoOuCarregar() {
        while (!quebra) {
            System.out.print("(n): Começar uma nova edição\n(c): Carregar dados\nOpção: ");
            opcao = scan.nextLine().trim().toLowerCase();
            switch (opcao) {
                case "c":
                    quebra = true;
                    numEdicao++;
                    if (numEdicao > 1) { //para avançar para o próximo ano (caso não seja a primeira edição)
                        ano++;
                    }
                    edicoes.add(new Edicao(numEdicao, ano));
                    System.out.print("Opções:\n(a): Carregar Atores e Filmes\n(c): Carregar Atores, Filmes e Candidatos\n(p): Carregar Tudo (sem pontuações)\n(t): Carregar Tudo (com pontuações)\nOpção: ");
                    opcao = scan.nextLine().trim().toLowerCase();
                    try {
                        switch (opcao.toLowerCase()) {
                            case "a":
                                carregaFilmes();
                                carregaAtores();
                                break;
                            case "c":
                                carregaFilmes();
                                carregaAtores();
                                carregaCandidatos();
                                break;
                            case "p":
                                carregaFilmes();
                                carregaAtores();
                                carregaPeritos();
                                carregaCandidatos();
                                break;
                            case "t":
                                carregaFilmes();
                                carregaAtores();
                                carregaPeritos();
                                carregaCandidatos();
                                carregaPontuacoes();
                                for (Premio p : edicoes.get(numEdicao - 1).getPremios()) { //faz-se este ciclo para sabermos logo no início
                                    //do programa os filmes mais premiados, já que todos os dados, incluindo nomeados e as suas pontuações, foram carregados
                                    p.ordenaPontuações();
                                }
                                break;
                            default:
                                quebra = false;
                                edicoes.remove(numEdicao - 1); //apaga-se a edição criada pois o utilizador inseriu uma opção inválida
                                numEdicao--;//decrementar o número de edição por causa da opção inválida
                                if (numEdicao > 1) {
                                    ano--;
                                }
                                System.out.println("Por favor selecione uma das opções disponíveis.");
                        }
                    } catch (IOException e) {
                        System.out.println("Erro ao carregar os dados.");
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
                    System.out.println("Por favor selecione uma das opções disponíveis.");
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
        FileReader inStream = new FileReader("Edicao" + numEdicao + "\\Atores.txt");
        BufferedReader lerDados = new BufferedReader(inStream);
        line = lerDados.readLine();
        while (line != null) {
            cria = true;
            switch (line) {
                case "Ator principal:":
                    nomeAtor = lerDados.readLine().trim();//ler a linha para saber o nome do ator
                    generoAtor = lerDados.readLine().trim().equals("M"); //ler a linha para saber o género do ator ator
                    anosCarreiraAtor = Integer.parseInt(lerDados.readLine().trim());//ler a linha para os anos de carreira do ator ator
                    if (!nomeAtor.equals("VAZIO")) { //se quando foi gravado o filme não tinha ator principal então no nome estará vazio
                        ator = new Ator(nomeAtor, generoAtor, anosCarreiraAtor);
                        for (Ator a : this.atores) {//percorremos a lista de atores para ver se o ator já foi criado
                            if (ator.equals(a)) { //se foi, inserimos no filme o ator previamente criado e não se cria um novo
                                ator = a;
                                cria = false;
                                break;
                            }
                        }
                        if (cria) { //se o ator já foi criado, não se cria de novo
                            atores.add(ator);
                        }
                        edicoes.get(numEdicao - 1).getFilmes().get(indexFilmes).insereAtor(ator, true); //insere-se o ator no filme
                    }
                    break;
                case "Atriz principal:":
                    nomeAtor = lerDados.readLine().trim();//ler a linha para saber o nome do atriz
                    generoAtor = lerDados.readLine().trim().equals("M");//ler a linha para saber o género do ator atriz
                    anosCarreiraAtor = Integer.parseInt(lerDados.readLine().trim());//ler a linha para os anos de carreira da atriz
                    if (!nomeAtor.equals("VAZIO")) {//se quando foi gravado o filme não tinha atriz principal então no nome estará vazio
                        ator = new Ator(nomeAtor, generoAtor, anosCarreiraAtor);
                        for (Ator a : this.atores) {//percorremos a lista de atores para ver se a atriz já foi criada
                            if (ator.equals(a)) {//se foi, inserimos no filme a atriz previamente criada e não se cria uma nova
                                ator = a;
                                cria = false;
                                break;
                            }
                        }
                        if (cria) {//se a atriz já foi criada, não se cria de novo
                            atores.add(ator);
                        }
                        edicoes.get(numEdicao - 1).getFilmes().get(indexFilmes).insereAtor(ator, true);//insere-se o ator no filme
                    }
                    break;
                case "Atores Secundarios:":
                    while (true) {
                        nomeAtor = lerDados.readLine(); //lê-se a linha, para ver se é o nome de um ator ou não
                        if (nomeAtor != null && !nomeAtor.contains("----------")) { //verificamos se ou já chegamos ao fim do ficheiro
                            //ou ao fim da lista de atores secundários do filme
                            nomeAtor = nomeAtor.trim();
                            generoAtor = lerDados.readLine().trim().equals("M");//ler a linha para saber o género do ator atriz
                            anosCarreiraAtor = Integer.parseInt(lerDados.readLine().trim());//ler a linha para os anos de carreira do ator
                            ator = new Ator(nomeAtor, generoAtor, anosCarreiraAtor);
                            for (Ator a : this.atores) {//percorremos a lista de atores para ver se o ator já foi criado
                                if (ator.equals(a)) {//se foi, inserimos no filme o ator previamente criado e não se cria um novo
                                    ator = a;
                                    cria = false;
                                    break;
                                }
                            }
                            if (cria) {//se o ator já foi criado, não se cria de novo
                                atores.add(ator);
                            }
                            edicoes.get(numEdicao - 1).getFilmes().get(indexFilmes).insereAtor(ator, false);
                        } else { //se a linha lida (em nomeAtor) é igual ao separador, aumenta-se o índice para guardar o próximo filme
                            indexFilmes++;
                            break; //
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
        Realizador realizador;
        String line;
        FileReader inStream = new FileReader("Edicao" + numEdicao + "\\Filmes.txt");
        BufferedReader lerDados = new BufferedReader(inStream);
        line = lerDados.readLine(); //ler a primeira linha, que é, se houver, o nome do primeiro filme
        while (line != null) {
            nomeFilme = line.trim();
            generoFilme = lerDados.readLine().trim(); //ler a linha que indica o género do filme
            nomeRealizador = lerDados.readLine().trim(); //ler a linha que indica o nome do realizador
            generoRealizador = lerDados.readLine().trim().equals("M"); //ler a linha que indica o género do realizador
            realizador = new Realizador(nomeRealizador, generoRealizador);
            for (int i = 0; i < numEdicao; i++) {
                for (Filme f : edicoes.get(i).getFilmes()) {//percorremos a lista de filmes para ver se o realizador já foi criado
                    if (f.getRealizador().equals(realizador)) {//se foi, inserimos no filme realizador previamente criado e não um novo
                        realizador = f.getRealizador();
                        break;
                    }
                }
            }
            Filme filme = new Filme(nomeFilme, generoFilme, realizador); //cria-se o filme
            edicoes.get(numEdicao - 1).insereFilmes(filme); //e acrescenta-se o filme à lista de filmes da edição que está a ser carregada
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
        FileReader inStream = new FileReader("Edicao" + numEdicao + "\\Peritos.txt");
        BufferedReader lerDados = new BufferedReader(inStream);
        String line;
        line = lerDados.readLine();//ler a primeira linha, que é, se houver, o nome do primeiro perito
        while (line != null) {
            nomePerito = line.trim();
            generoPerito = lerDados.readLine().trim().equals("M"); //ler a linha que indica o género do perito
            Perito perito = new Perito(nomePerito, generoPerito); //criar o perito
            edicoes.get(numEdicao - 1).inserePerito(perito); //e acrescentá-lo na lista de peritos da edição que está a ser carregada
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
        FileReader inStream = new FileReader("Edicao" + numEdicao + "\\Candidatos.txt");
        BufferedReader lerDados = new BufferedReader(inStream);
        String line;
        line = lerDados.readLine().trim();
        while (line != null && indexPremios < 9) {
            vazio = false;
            premio = edicoes.get(numEdicao - 1).getPremios().get(indexPremios); //guardar em premio o prémio dos quais estamos a carregar os candidatos
            if (indexPremios < 4) {
                while (premio.getAtoresCandidatos().size() < 4) {
                    nomeAtor = lerDados.readLine(); //lê-mos a linha, que ou é o nome do ator ou o separador, se não há candidatos para ler
                    if (nomeAtor == null || nomeAtor.contains("----------")) { //se não há candidatos para ler, ou já se chegou ao fim do ficheiro, saímos do while
                        vazio = true;
                        break;
                    }
                    nomeAtor = nomeAtor.trim();
                    filme = lerDados.readLine().trim(); //ler a linha que indica o nome do filme pelo qual o ator/atriz foi nomeado
                    for (Ator a : this.atores) { //percorrer a lista de atores para encontrar o ator
                        if (a.getNome().equals(nomeAtor)) { //se o nome do ator for igual ao ator na lista, nomeia-se o ator e encontra-se o filme na lista de filmes em que o ator participa
                            premio.nomeiaAtor(a, a.getFilmes().get(indexOfByFilmName(filme, a.getFilmes())));
                            break;
                        }
                    }
                }
            } else if (indexPremios > 3 && indexPremios < 8) {
                while (premio.getFilmesCandidatos().size() < 4) {
                    filme = lerDados.readLine();//lê-mos a linha, que ou é o nome do filme ou o separador, se não há candidatos para ler
                    if (filme == null || filme.contains("----------")) {//se não há candidatos para ler, ou já se chegou ao fim do ficheiro, saímos do while
                        vazio = true;
                        break;
                    }
                    filme = filme.trim();
                    for (Filme f : edicoes.get(numEdicao - 1).getFilmes()) { //percorre-se a lista de filmes para se encontrar o nomeado
                        if (filme.equals(f.getNome()) || filme.equals(f.getRealizador().getNome())) {//se o nome do filme for igual ao nome do filme na lista (ou o nome do realizador, no caso do prémio Melhor Realizador), nomeia-se o filme
                            premio.nomeiaFilme(f);
                            break;
                        }
                    }
                }
            } else if (indexPremios == 8) {
                while (premio.getAtoresCandidatos().size() < 4) {
                    nomeAtor = lerDados.readLine();//lê-mos a linha, que ou é o nome do ator ou o separador, se não há candidatos para ler
                    if (nomeAtor == null || nomeAtor.contains("----------")) {//se não há candidatos para ler, ou já se chegou ao fim do ficheiro, saímos do while
                        vazio = true;
                        break;
                    }
                    nomeAtor = nomeAtor.trim();
                    for (Ator a : this.atores) {//percorrer a lista de atores para encontrar o ator
                        if (a.getNome().equals(nomeAtor) && a.getAnosCarreira() > 20) { //se o nome do ator coincide com o nome do ator na lista e o ator na lista tem mais de 20 anos de carreira, nomeia-se o ator
                            premio.nomeiaAtor(a);
                            break;
                        }
                    }
                }
            }
            indexPremios++; //avançar para o próximo prémio
            if (!vazio) { //se vazio é false, lê-se a linha, que será um separador, se vazio é true já se leu o separador anteriormente
                line = lerDados.readLine();
            }
        }
        lerDados.close();
    }

    /**
     * Método que permite carregar as pontuações de um ficheiro
     */
    private void carregaPontuacoes() throws IOException {
        int i = 0; //variável que representa o índice da lista de pontuações do candidato
        int j = 0; //variável que representa a posição do valor da pontuação na lista de pontuações do candidato
        final int NUMPERITOS = edicoes.get(numEdicao - 1).getPeritos().size();
        String pontos = ""; //varíavel que guarda o valor da pontuação que se lê
        int indexPremios = -1;
        FileReader inStream = new FileReader("Edicao" + numEdicao + "\\Pontuacoes.txt");
        BufferedReader lerDados = new BufferedReader(inStream);
        String line;
        line = lerDados.readLine().trim();
        while (line != null) {
            if (!line.contains("----------")) { //vemos se a linha não é o separador
                for (char aux : line.toCharArray()) { //percorremos a linha
                    if (aux != '*') { //se o carater não é igual ao separador que separa os valor das pontuações
                        pontos += Character.toString(aux); //acrescenta-se o caráter ao valor da pontuação
                    } else { //se é igual, mete-se a pontuação na lista de pontuações do candidato
                        edicoes.get(numEdicao - 1).getPremios().get(indexPremios).setPontuacao(i, j % NUMPERITOS, Integer.parseInt(pontos));
                        //insere-se o valor inteiro representado por pontos na lista de pontuações do candidato i na posição j%NUMPERITOS
                        pontos = ""; //reset da variável que guarda a pontuação
                        if ((j + 1) % NUMPERITOS == 0) { //se chegou-se ao fim da lista de pontuações do candidato i, avança-se para o próximo
                            i++;
                        }
                        j++;
                    }
                }
            } else { //se a linha é o separador avançamos para o próximo prémio e faz-se reset das variáveis i e j
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
        String separador = "--------------------------------";
        FileWriter outStream = new FileWriter("Edicao" + numEdicao + "\\Atores.txt");
        BufferedWriter bW = new BufferedWriter(outStream);
        PrintWriter out = new PrintWriter(bW);
        for (Filme filme : edicoes.get(numEdicao - 1).getFilmes()) {
            out.println(separador); //inserimos o separador sempre que gravamos um filme novo
            if (filme.getAtorPrincipal() != null) {
                //verifica-se se o filme tem ator principal e imprime-se o separador "Ator Principal", e imprime-se o seu nome, M (género) e os seus anos de carreira
                out.printf("Ator principal:\n%s\nM\n%d\n", filme.getAtorPrincipal().getNome(), filme.getAtorPrincipal().getAnosCarreira());
            } else {
                //se não imprime-se o separador "Ator Principal", VAZIO,VAZIO e 0
                out.println("Ator principal:\nVAZIO\nVAZIO\n0");
            }
            if (filme.getAtrizPrincipal() != null) {
                //verifica-se se o filme tem atriz principal e imprime-se o separador "Atriz Principal", o seu nome, F (género) e os seus anos de carreira
                out.printf("Atriz principal:\n%s\nF\n%d\n", filme.getAtrizPrincipal().getNome(), filme.getAtrizPrincipal().getAnosCarreira());
            } else {
                //se não imprime-se o separador "Atriz Principal", VAZIO,VAZIO e 0
                out.println("Atriz principal:\nVAZIO\nVAZIO\n0");
            }
            out.println("Atores Secundarios:"); //imprime-se este separador para sinalizar que daqui para a frente estão gravados os atores secundários
            for (Ator atorSec : filme.getAtoresSecundarios()) { //imprime-se o nome, género e os anos de carreira de atorSec
                out.printf("%s\n%s\n%d\n", atorSec.getNome(), (atorSec.getGenero() ? "M" : "F"), atorSec.getAnosCarreira());
            }
        }
        out.close();
    }

    /**
     * Método que permite gravar os filmes num ficheiro
     */
    private void gravaFilmes() throws IOException {
        FileWriter outStream = new FileWriter("Edicao" + numEdicao + "\\Filmes.txt");
        BufferedWriter bW = new BufferedWriter(outStream);
        PrintWriter out = new PrintWriter(bW);
        for (Filme filme : edicoes.get(numEdicao - 1).getFilmes()) { //percorremos a lista de filmes para gravá-los
            //grava-se o nome do filme, o género e o nome do realizador e o género deste
            out.printf("%s\n%s\n%s\n%s\n", filme.getNome(), filme.getGenero(), filme.getRealizador().getNome(), (filme.getRealizador().getGenero() ? "M" : "F"));
        }
        out.close();
    }

    /**
     * Método que permite gravar os candidatos num ficheiro
     */
    private void gravaCandidatos() throws IOException {
        String separador = "--------------------------------";
        FileWriter outStream = new FileWriter("Edicao" + numEdicao + "\\Candidatos.txt");
        BufferedWriter bW = new BufferedWriter(outStream);
        int indexPremio = 0;
        PrintWriter out = new PrintWriter(bW);
        for (Premio premio : edicoes.get(numEdicao - 1).getPremios()) { //percorremos a lista de prémios
            out.println(separador); //grava-se o separador
            if (indexPremio < 4) { //se é um prémio de ator/atriz principal ou secundário grava-se o nome do candidato
                //e o filme no qual ele participa, em linhas diferentes
                for (int candidato = 0; candidato < premio.getAtoresCandidatos().size(); candidato++) {
                    out.printf("%s\n%s\n", premio.getAtoresCandidatos().get(candidato).getNome(), premio.getFilmesCandidatos().get(candidato).getNome());
                }
            } else if (indexPremio == 5) { //se é o prémio de melhor realizador grava-se o nome do realizador nomeado
                for (int candidato = 0; candidato < premio.getFilmesCandidatos().size(); candidato++) {
                    out.println(premio.getFilmesCandidatos().get(candidato).getRealizador().getNome());
                }
            } else if (indexPremio == 4 || (indexPremio > 5 && indexPremio < 8)) { //se é um prémio dado a um filme grava-se
                //o nome do filme nomeado
                for (int candidato = 0; candidato < premio.getFilmesCandidatos().size(); candidato++) {
                    out.println(premio.getFilmesCandidatos().get(candidato).getNome());
                }
            } else { //por último, se é o prémio carreira só se grava o nome do nomeado ao prémio
                for (int candidato = 0; candidato < premio.getAtoresCandidatos().size(); candidato++) {
                    out.println(premio.getAtoresCandidatos().get(candidato).getNome());
                }
            }
            indexPremio++; //avança-se para o próximo prémio
        }
        out.close();
    }

    /**
     * Método que permite gravar os peritos num ficheiro
     */
    private void gravaPeritos() throws IOException {
        FileWriter outStream = new FileWriter("Edicao" + numEdicao + "\\Peritos.txt");
        BufferedWriter bW = new BufferedWriter(outStream);
        PrintWriter out = new PrintWriter(bW);
        for (Perito perito : edicoes.get(numEdicao - 1).getPeritos()) { //percorremos a lista de peritos para gravá-los
            //grava-se o nome e o género do perito, em linhas diferentes
            out.printf("%s\n%s\n", perito.getNome(), (perito.getGenero() ? "M" : "F"));
        }
        out.close();
    }

    /**
     * Método que permite gravar as pontuações num ficheiro
     */
    private void gravaPontuações() throws IOException {
        String separador = "--------------------------------";
        FileWriter outStream = new FileWriter("Edicao" + numEdicao + "\\Pontuacoes.txt");
        BufferedWriter bW = new BufferedWriter(outStream);
        PrintWriter out = new PrintWriter(bW);
        for (Premio premio : edicoes.get(numEdicao - 1).getPremios()) {
            out.println(separador); //imprime-se o separador
            for (int linha = 0; linha < premio.getPontuacoes().size(); linha++) {
                //percorremos a lista de pontuações para ter as pontuações de cada candidato
                for (int coluna = 0; coluna < premio.getPontuacoes().get(linha).size(); coluna++) {
                    //percorremos a lista com as pontuações do candidato e grava-se as pontuações, separadas pelo asterisco
                    out.print(premio.getPontuacoes().get(linha).get(coluna) + "*");
                }
                out.println(); //fazemos parágrafo
            }
        }
        out.close();
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
        System.out.printf("\t\t\t%dª Edição do Festival de Cinema %d\n", numEdicao, ano);
    }
}
