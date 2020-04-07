
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.*;

public class FestivalCinema {

    private ArrayList<Edicao> edicoes;
    private ArrayList<Ator> atores;
    private boolean novo;
    private int indexEdicoes;
    private int ano;
    private int numEdicao;
    private Scanner scan;
    private boolean quebra;
    private File ficheiroFilmes;
    private File ficheiroAtores;

    public FestivalCinema() {
        this.edicoes = new ArrayList<>();
        this.atores = new ArrayList<>();
        this.novo = true;
        this.indexEdicoes = 0;
        this.ano = 0;
        this.numEdicao = 0;
        this.scan = new Scanner(System.in, "cp1252");
        this.quebra = false;
        this.ficheiroFilmes = new File("filmes.txt");
        this.ficheiroAtores = new File("atores.txt");
    }

    public void menu() {
        System.out.println("\t\t\tFESTIVAL CINEMA");
        System.out.print("(n): Começar um novo programa\t(c): Carregar um programa anterior\nOpção: ");
        String opcao = scan.nextLine();
        while (!(opcao.equalsIgnoreCase("c") || opcao.equalsIgnoreCase("n"))) {
            System.out.println("Por favor selecione uma das opções disponíveis.");
            System.out.print("(n): Começar um novo programa\t(c): Carregar um programa anterior\nOpção: ");
            opcao = scan.nextLine();
        }
        if (opcao.equalsIgnoreCase("c")) {
            novo = false;
            numEdicao++;
            ano++;
            edicoes.add(new Edicao(numEdicao, ano));
            insereFilmesCarregados(edicoes, ficheiroFilmes, numEdicao, indexEdicoes);
            insereAtoresCarregados(indexEdicoes, edicoes, ficheiroAtores);

        } else {
            numEdicao++;
            ano++;
            edicoes.add(new Edicao(numEdicao, ano));
        }

        while (!quebra) {
            System.out.println("*****************************************************************");
            System.out.print("\t\t\tOpções:\n(c): Criar algo\t(l): Listar algo\t(h): Criar nova Edição\t(s): Sair\nOpção: ");
            String opcaoGeral = scan.nextLine();
            while (opcaoGeral.length() > 1) {
                System.out.println("Por favor selecione uma das opções disponíveis.");
                System.out.print("(c): Criar algo\t(l): Listar algo\t(h): Criar nova Edição\t(s): Sair\nOpção: ");
                opcaoGeral = scan.nextLine();
            }
            switch (opcaoGeral.toLowerCase()) {

                case "c":
                    System.out.print("\t\t\tOpções\n(f): Criar Filme\t(a): Criar Ator/Atriz\t(e): Criar Perito\t(p): Atribui Papel\t(c): Escolher candidatos\t(s): Atribuir Pontuação\nOpção: ");
                    opcao = scan.nextLine();
                    while (opcao.length() > 1) {
                        System.out.println("Por favor selecione uma das opções disponíveis.");
                        System.out.print("(f): Criar Filme\t(a): Criar Ator/Atriz\t(e): Criar Perito\t(p): Atribui Papel\t(c): Escolher candidatos\t(s): Atribuir Pontuação\nOpção: ");
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
                                System.out.print("Prima 1 se pretende criar mais um perito e outro número caso contrário\nOpção: ");
                                int maisUm = scan.nextInt();
                                scan.nextLine();
                                if (maisUm != 1) {
                                    parar = true;
                                }
                            }
                            if (edicoes.get(indexEdicoes).getPeritos().size() >= 5) {
                                System.out.println("Já criou 5 peritos, o número máximo de peritos!");
                            }
                            break;
                        case "p":
                            atribuirPapel();
                            break;
                        case "c":
                            escolherCandidatos();
                            break;
                        case "s":
                            pontuarCandidatos(edicoes.get(indexEdicoes).getPremios().get(4));
                            break;
                    }
                    break;
                case "l":
                    System.out.print("\t\t\tOpções\n(a): Listar Atores\t(f): Listar Filmes\t(p): Listar Prémios\t(c) : Listar Candidatos\t(i): Consultar Edição\nOpção: ");
                    opcao = scan.nextLine();
                    while (opcao.length() > 1) {
                        System.out.println("Por favor selecione uma das opções disponíveis.");
                        System.out.print("(a): Listar Atores\t(f): Listar Filmes\t(p): Listar Prémios\t(c) : Listar Candidatos\t(i): Consultar Edição\nOpção: ");
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
                            listarPremios();
                            break;
                        case "i":
                            consultarEdicoes();
                            break;
                        case "c":
                            listarCandidatos();
                            break;
                    }
                    break;
                case "h":
                    System.out.print("NOVA EDIÇÃO CRIADA");
                    for (Ator a : atores) {
                        a.resetNumFilmesEdicaoAtual();
                        a.incrementaAnosCarreira();
                    }
                    numEdicao++;
                    ano++;
                    indexEdicoes++;
                    edicoes.add(new Edicao(numEdicao, ano));
                    break;
                case "s":
                    quebra = true;
                    break;
            }
        }
    }

    /**
     * Como o nome indica este método é para criar um filme
     *
     * @param scan - o scanner
     * @param numEdicao - o numero da edição
     * @param indexEdicoes - a edição onde é para inserir o filme
     * @param edicoes - o array das edições
     */
    private void criarFilme() {
        System.out.print("NOVO FILME\nNome do Filme: ");
        String nome = scan.nextLine();
        System.out.print("Género do Filme: ");
        String genero = scan.nextLine();
        System.out.print("Nome do Realizador: ");
        String nomeRealizador = scan.nextLine();
        System.out.print("Género do Realizador (M-Masculino; F-Feminino): ");
        String generoRealizador = scan.nextLine();
        while (generoRealizador.length() > 1 || !(generoRealizador.equalsIgnoreCase("M") || generoRealizador.equalsIgnoreCase("F"))) { //verifica que inseriu uma opção válida
            System.out.print("Género Inválido. Género (M-Masculino; F-Feminino): ");
            generoRealizador = scan.nextLine();
        }
        Realizador realizador = new Realizador(nomeRealizador, generoRealizador.equalsIgnoreCase("M"));
        Filme filme = new Filme(nome, genero, numEdicao, realizador);
        edicoes.get(indexEdicoes).insereFilmes(filme); //insere na lista de filmes da edição atual o filme criado
    }

    /**
     *
     * @param scan - o scanner
     * @param atores - lista de atores criados durante a execução do programa
     */
    private void criarAtor() {
        System.out.print("NOVO ATOR\nNome do Ator/Atriz: ");
        String nome = scan.nextLine();
        System.out.print("Ator ou Atriz (M-Masculino; F-Feminino): ");
        String genero = scan.nextLine();
        while (genero.length() > 1 || !(genero.equalsIgnoreCase("M") || genero.equalsIgnoreCase("F"))) { //verifica que inseriu uma opção válida
            System.out.print("Género Inválido. Género (M-Masculino; F-Feminino): ");
            genero = scan.nextLine();
        }
        System.out.print("Anos de carreira do Ator/Atriz: ");
        int anosCarreira = scan.nextInt();
        scan.nextLine(); //discarda o enter
        Ator ator = new Ator(nome, genero.equalsIgnoreCase("M"), anosCarreira);
        atores.add(ator); //adiciona o ator criado no array de atores
    }

    private void criarPerito() {
        System.out.print("NOVO PERITO\nNome do perito: ");
        String nome = scan.nextLine();
        System.out.print("Género do Perito (M-Masculino; F-Feminino): ");
        String genero = scan.nextLine();
        while (genero.length() > 1 || !(genero.equalsIgnoreCase("M") || genero.equalsIgnoreCase("F"))) { //verifica que inseriu uma opção válida
            System.out.print("Género Inválido. Género (M-Masculino; F-Feminino): ");
            genero = scan.nextLine();
        }
        Perito perito = new Perito(nome, genero.equalsIgnoreCase("M"));
        edicoes.get(indexEdicoes).inserePerito(perito);
    }

    /**
     *
     * @param atores - array de atores criado durante a execução do programa
     * @param scan - o scanner
     * @param indexEdicoes - o indice que indica a edição atual
     * @param edicoes - o array das edições
     */
    private void atribuirPapel() {
        int i = 1;
        for (Ator a : atores) { //imprime os atores criados anteriormente na execução do programa
            System.out.printf("%d. %s\n", i, a.getNome());
            i++;
        }
        System.out.print("Escolha a posição do ator que quer inserir num filme\nPosição: ");
        int pos = scan.nextInt();
        scan.nextLine(); //discarda o enter
        try {
            Ator mudar = atores.get(pos - 1); //guarda o ator ao qual atribuir um papel, se possível, caso contrário apanha a excepção e imprime uma mensagem
            if (mudar.podeInserirFilme()) { //verifica que o ator não participa em 2 filmes na edição atual
                i = 1;
                for (Filme f : edicoes.get(indexEdicoes).getFilmes()) { //imprime a lista de filmes da edição atual
                    System.out.printf("%d. %s\n", i, f.getNome());
                    i++;
                }
                System.out.println("Qual o filme?");
                int posFilme = scan.nextInt();
                scan.nextLine(); //discarda o enter
                Filme casting = edicoes.get(indexEdicoes).getFilmes().get(posFilme - 1); //guarda o filme no qual se pretende inserir o ator
                System.out.println("Qual o papel do ator/atriz (P-Principal ou S-Secundário)?");
                String papel = scan.nextLine();
                while (papel.length() > 1 || !(papel.equalsIgnoreCase("P") || papel.equalsIgnoreCase("S"))) {//verifica que inseriu uma opção válida
                    System.out.print("Opção inválida. Opção (P-Principal ou S-Secundário): ");
                    papel = scan.nextLine();
                }
                switch (papel.toLowerCase()) {
                    case "p": //se pretende-se que o ator/atriz seja principal
                        casting.insereAtor(mudar, mudar.getGenero() ? 0 : 1); //ver o método insereAtor em Filmes
                        mudar.inserirFilme(casting); //insere o filme na lista de filmes em que o ator/atriz participa
                        break;
                    case "s": //se pretende-se que o ator/atriz seja secundário/a
                        casting.insereAtor(mudar, 2); //ver o método insereAtor em Filmes
                        mudar.inserirFilme(casting); //insere o filme na lista de filmes em que o ator/atriz participa
                        break;
                }
            }
        } catch (Exception e) { //no caso de o utilizador quiser selecionar um ator ou filme que não exista (apanha a exceção NullPointerException)
            System.out.println("Posição não existe.");
        }
    }

    private void consultarEdicoes() {
        int aux = 0;
        if (edicoes.isEmpty()) {
            System.out.println("Ainda não há nehuma edição do festival!");
        } else {
            while (aux < edicoes.size()) {
                System.out.println(edicoes.get(aux));
                aux++;
            }
        }
    }

    private void listarAtores() {
        int posição = 0;
        if (!atores.isEmpty()) {
            while (posição < atores.size()) {
                System.out.print(atores.get(posição) + "\n");
                posição++;
            }
        } else {
            System.out.println("Não existem atores nesta edição.");
        }
    }

    private void listarFilmes() {
        int posiçãoFilme = 0;
        for (int posiçãoEdição = 0; posiçãoEdição < edicoes.size(); posiçãoEdição++) {
            System.out.println("EDIÇÃO: " + edicoes.get(posiçãoEdição).getNumEdicao());
            if (edicoes.get(posiçãoEdição).getFilmes().isEmpty()) {
                System.out.println("Não existem filmes nesta edição.\n");
            } else {
                System.out.println("FILMES: \n");
                while (posiçãoFilme < edicoes.get(posiçãoEdição).getFilmes().size()) {
                    System.out.print(edicoes.get(posiçãoEdição).getFilmes().get(posiçãoFilme) + "\n");
                    posiçãoFilme++;
                }
                posiçãoFilme = 0;
            }
        }
    }

    private void listarPremios() {
        System.out.println("CATEGORIAS A PREMIAR:");
        edicoes.get(indexEdicoes).imprimePremios();
    }

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
    
    private Premio escolherPremio(){
        System.out.println("Escolha o prémio:");
        System.out.print("(1) Melhor Ator Principal\n" + "(2) Melhor Atriz Principal\n"
                + "(3) Melhor Ator Secundário\n" + "(4) Melhor Atriz Secundária\n"
                + "(5) Melhor Filme\n"
                + "(6) Melhor Realizador\n"
                + "(7) Melhor Argumento\n"
                + "(8) Melhor Cinematografia\n"
                + "(9) Prémio Carreira"
                + "\nOPÇÃO: ");
        int opcao = scan.nextInt();
        scan.nextLine();
        return edicoes.get(indexEdicoes).getPremios().get(opcao-1);
    }

    //método que permite o utilizador escolher os filmes candidatos para um dado prémio:
    private ArrayList<Filme> escolherFilmesCandidatos() {
        ArrayList<Filme> filmesCandidatos = new ArrayList<Filme>();
        while (filmesCandidatos.size() < 4) { //até escolher os 4 candidatos
            int i = 1;
            for (Filme filme : edicoes.get(indexEdicoes).getFilmes()) {
                System.out.printf("%d. %s\n", i, filme.getNome());
                i++;
            }
            System.out.println("Escolha um candidato: ");
            int pos = scan.nextInt();
            scan.nextLine();
            try {
                Filme candidato = edicoes.get(0).getFilmes().get(pos - 1);
                filmesCandidatos.add(candidato);
            } catch (Exception e) {
                System.out.println("Por favor, escolha um filme válido.");
            }
        }
        // System.out.print(filmesCandidatos); //PARA TESTAR
        return filmesCandidatos;
    }

    //método que permite o utilizador escolher os melhores realizadores (devolve os filmes dos melhores realizadores):
    private ArrayList<Filme> escolherRealizadorCandidatos() {
        ArrayList<Filme> filmesCandidatos = new ArrayList<Filme>();
        while (filmesCandidatos.size() < 4) { //até escolher os 4 candidatos
            int i = 1;
            for (Filme filme : edicoes.get(indexEdicoes).getFilmes()) {
                System.out.printf("%d. %s\n", i, filme.getRealizador().getNome()); //mostra nome do realizador do filme
                i++;
            }
            System.out.println("Escolha um candidato: ");
            int pos = scan.nextInt();
            scan.nextLine();
            try {
                Filme candidato = edicoes.get(indexEdicoes).getFilmes().get(pos - 1);
                filmesCandidatos.add(candidato); //adiciona filme do realizador
            } catch (Exception e) {
                System.out.println("Por favor, escolha um realizador válido.");
            }
        }
        // System.out.print(filmesCandidatos); //PARA TESTAR
        return filmesCandidatos;
    }

    //método que permite o utilizador escolher os atores (e as atrizes) principais:
    private ArrayList<Pessoa> escolherAtoresPrincipaisCandidatos(boolean homem) {
        ArrayList<Pessoa> atoresCandidatos = new ArrayList<Pessoa>();
        int i;
        while (atoresCandidatos.size() < 4) { //até escolher os 4 candidatos
            i = 1;
            if (homem) { //para ator principal
                for (Filme filme : edicoes.get(indexEdicoes).getFilmes()) {
                    if (filme.getAtorPrincipal() != null) {
                        System.out.printf("%d. %s\n", i, filme.getAtorPrincipal().getNome());
                        i++;
                    }
                }
                System.out.println("Escolha um candidato: ");
                int pos = scan.nextInt();
                scan.nextLine();
                try {
                    Filme candidato = edicoes.get(indexEdicoes).getFilmes().get(pos - 1);
                    atoresCandidatos.add(candidato.getAtorPrincipal());
                } catch (Exception e) {
                    System.out.println("Por favor, escolha um ator válido.");
                }
            } else { //para atriz principal
                for (Filme filme : edicoes.get(indexEdicoes).getFilmes()) {
                    if (filme.getAtrizPrincipal() != null) {
                        System.out.printf("%d. %s\n", i, filme.getAtrizPrincipal().getNome());
                        i++;
                    }
                }
                System.out.println("Escolha um candidato: ");
                int pos = scan.nextInt();
                scan.nextLine();
                try {
                    Filme candidato = edicoes.get(indexEdicoes).getFilmes().get(pos - 1);
                    atoresCandidatos.add(candidato.getAtrizPrincipal());
                } catch (Exception e) {
                    System.out.println("Por favor, escolha um ator válido.");
                }
            }
        }
        // System.out.print(atoresCandidatos); //PARA TESTAR
        return atoresCandidatos;
    }

    private ArrayList<Pessoa> escolherAtoresSecundariosCandidatos(boolean homem) {
        ArrayList<Pessoa> atoresCandidatos = new ArrayList<Pessoa>();
        ArrayList<Pessoa> auxiliar;
        int i, j;
        while (atoresCandidatos.size() <= 4) { //até escolher os 4 candidatos
            auxiliar = new ArrayList<Pessoa>();
            i = 1;
            for (Filme filme : edicoes.get(indexEdicoes).getFilmes()) {
                System.out.printf("%d. %s\n\tAtores Secundários:\n", i, filme.getNome());
                for (Pessoa a : filme.getAtoresSecundarios()) {
                    if (a.getGenero() == homem) {
                        System.out.printf("\t%s\n", a.getNome());
                    }
                }
                i++;
            }
            System.out.println("Escolha o filme do candidato que pretende selecionar: ");
            int posFilme = scan.nextInt();
            scan.nextLine();
            try {
                Filme candidato = edicoes.get(indexEdicoes).getFilmes().get(posFilme - 1);
                for (Pessoa a : candidato.getAtoresSecundarios()) {
                    if (a.getGenero() == homem) {
                        auxiliar.add(a);
                    }
                }
                j = 1;
                for (Pessoa a : auxiliar) {
                    System.out.printf("%d. %s\n", j, a.getNome());
                    j++;
                }
                System.out.print("Agora escolha o ator secundário nomeado ao prémio (pelo número associado): ");
                int posCandidato = scan.nextInt();
                scan.nextLine();
                try {
                    Pessoa escolhido = auxiliar.get(posCandidato - 1);
                    Pessoa nomeado = null;
                    for (Pessoa a : candidato.getAtoresSecundarios()) {
                        if (a == escolhido) {
                            nomeado = a;
                        }
                    }
                    if (nomeado != null) {
                        atoresCandidatos.add(nomeado);
                    }
                } catch (Exception e) {
                    System.out.println("Esse ator não participa neste filme.");
                }
            } catch (Exception e) {
                System.out.println("Por favor, escolha um filme válido.");
            }
        }
        auxiliar = null;
        return atoresCandidatos;
    }

    //método que mostra os quatro candidatos ao prémio em cada categoria
    private void listarCandidatos() {
        int contaPremios = 1;
        System.out.println("CANDIDATOS AOS PRÉMIOS:");

        for (Premio premio : edicoes.get(indexEdicoes).getPremios()) {
            System.out.println(premio + ":");
            try {
                if (contaPremios <= 4 || contaPremios == 9) {
                    for (int i = 0; i < 4; i++) {
                        System.out.println(premio.getAtoresCandidatos().get(i).getNome());
                    }
                } else if (contaPremios > 4 && contaPremios != 6 && contaPremios != 9) {
                    for (int i = 0; i < 4; i++) {
                        System.out.println(premio.getFilmesCandidatos().get(i).getNome());
                    }
                } else {
                    for (int i = 0; i < 4; i++) {
                        System.out.println(premio.getFilmesCandidatos().get(i).getRealizador().getNome());
                    }
                }
            } catch (Exception e) {
                System.out.println("Não tem candidatos.\n");
            }
            contaPremios++;
        }
    }

    private ArrayList<Pessoa> escolherPremioCarreira() {
        ArrayList<Pessoa> atoresCandidatos = new ArrayList<Pessoa>();
        while (atoresCandidatos.size() < 4) { //até escolher os 4 candidatos
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
                System.out.println("Porfavor, escolha um candidato válido.");
            }
        }
        //System.out.print(atoresCandidatos); //PARA TESTAR
        return atoresCandidatos;
    }

    private void pontuarCandidatos(Premio premio) {
        System.out.println("AVALIAÇÃO DO PRÉMIO " + premio.toString().toUpperCase());
        if (premio.getNome().contains("Ator") || premio.getNome().contains("Ator")) {
            for (int indiceCandidato = 0; indiceCandidato < premio.getAtoresCandidatos().size(); indiceCandidato++) {
                for (Perito p : edicoes.get(indexEdicoes).getPeritos()) {
                    System.out.printf((p.getGenero() ? "O perito %s " : "A perita %s ") + "atribui ao candidato a pontuação (de 1 a 10): ", p.getNome());
                    p.inserePontuacao(scan.nextInt(), premio, indiceCandidato, edicoes.get(indexEdicoes).getPeritos().indexOf(p));
                    scan.nextLine();
                }
            }
        } else if (!premio.getNome().contains("Carreira")) {
            for (int indiceCandidato = 0; indiceCandidato < premio.getFilmesCandidatos().size(); indiceCandidato++) {
                for (Perito p : edicoes.get(indexEdicoes).getPeritos()) {
                    System.out.printf((p.getGenero() ? "O perito %s " : "A perita %s ") + "atribui ao candidato a pontuação (de 1 a 10): ", p.getNome());
                    p.inserePontuacao(scan.nextInt(), premio, indiceCandidato, edicoes.get(indexEdicoes).getPeritos().indexOf(p));
                    scan.nextLine();
                }
            }
        }
        premio.imprimePontuações(premio.getPontuacoes());
    }

    //-------------------------------------------------------------------------------------------
    public void insereAtoresCarregados(int indexEdicoes, ArrayList<Edicao> edicoes, File ficheiro) {
        String nomeAtor = " ";
        Boolean generoAtor = false;
        //int anosCarreiraAtor = 0;
        Boolean criarAtorP = false;
        Boolean criarAtrizP = false;
        Boolean criarAtoresS = false;
        Boolean auxVazio;
        int i = 0;
        int indexFilmes = -1;

        try {
            FileReader lerFicheiro = new FileReader(ficheiro);
            BufferedReader lerDados = new BufferedReader(lerFicheiro);
            String line;
            while ((line = lerDados.readLine()) != null) {
                StringBuilder aux = new StringBuilder();
                aux.append(line).append(" ");
                String auxString = aux.toString();

                if (auxString.equals("-------------------------------- ")) {
                    criarAtoresS = false;
                    indexFilmes++;
                }
                if (auxString.equals(" ")) {

                    auxVazio = true;
                } else {
                    auxVazio = false;
                }

                if (criarAtorP && !auxVazio) {

                    switch (i) {
                        case 0:
                            nomeAtor = auxString;
                            break;
                        case 1:
                            if (auxString.equals("F ")) {
                                generoAtor = false;
                            } else {
                                generoAtor = true;
                            }
                            break;

                        case 2:

                            Ator ator = new Ator(nomeAtor, generoAtor, 9);
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

                if (criarAtrizP && !auxVazio) {

                    switch (i) {
                        case 0:
                            nomeAtor = auxString;
                            break;
                        case 1:
                            if (auxString.equals("F ")) {
                                generoAtor = false;
                            } else {
                                generoAtor = true;
                            }
                            break;
                        case 2:

                            Ator ator = new Ator(nomeAtor, generoAtor, 9);
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

                if (criarAtoresS && !auxVazio) {

                    switch (i) {
                        case 0:
                            nomeAtor = auxString;
                            break;
                        case 1:
                            if (auxString.equals("F ")) {
                                generoAtor = false;
                            } else {
                                generoAtor = true;
                            }
                            break;
                        case 2:

                            Ator ator = new Ator(nomeAtor, generoAtor, 9);
                            edicoes.get(indexEdicoes).getFilmes().get(indexFilmes).insereAtor(ator, 3);

                            break;
                    }
                    if (i == 2) {
                        i = 0;
                    } else {
                        i++;
                    }
                }
                if (auxString.equals("Ator principal: ")) {
                    criarAtorP = true;
                }
                if (auxString.equals("Atriz principal: ")) {
                    criarAtrizP = true;

                }
                if (auxString.equals("Atores Secundarios: ")) {
                    criarAtoresS = true;
                }
            }
        } catch (IOException ioe) {
            System.out.println("Ocorreu um Erro");
        }
    }

    public static void insereFilmesCarregados(ArrayList<Edicao> edições, File ficheiro, int numEdicao, int indexEdicoes) {
        String nomeFilme = " ";
        String generoFilme = " ";
        String nomeRealizador = " ";
        Boolean generoRealizador = false;
        int i = 0;
        String tracinhos = "-------------------------------- ";
        Boolean criarNovoFilme = false;

        try {
            FileReader lerFicheiro = new FileReader(ficheiro);
            BufferedReader lerDados = new BufferedReader(lerFicheiro);
            String line;
            Boolean auxVazio = false;
            while ((line = lerDados.readLine()) != null) {
                StringBuilder aux = new StringBuilder();
                aux.append(line).append(" ");
                String auxString = aux.toString();

                if (auxString.equals(" ")) {

                    auxVazio = true;
                } else {
                    auxVazio = false;
                }

                if (criarNovoFilme && !auxVazio) {
                    switch (i) {
                        case 0:
                            nomeFilme = auxString;
                            break;
                        case 1:
                            generoFilme = auxString;
                            break;
                        case 2:
                            nomeRealizador = auxString;
                            break;
                        case 3:
                            if (auxString.equals("F ")) {
                                generoRealizador = false;
                            } else {
                                generoRealizador = true;
                            }

                            criarNovoFilme = false;
                            Realizador realizador = new Realizador(nomeRealizador, generoRealizador);
                            Filme filme = new Filme(nomeFilme, generoFilme, numEdicao, realizador);
                            edições.get(indexEdicoes).insereFilmes(filme);
                            break;
                    }
                    if (i == 3) {
                        i = 0;
                    } else {
                        i++;
                    }
                }

                if (auxString.equals(tracinhos)) {
                    criarNovoFilme = true;
                }
            }
        } catch (IOException ioe) {
            System.out.println("Ocorreu um Erro");
        }
    }

}
