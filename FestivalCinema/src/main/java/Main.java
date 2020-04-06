
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean novo = true;
        ArrayList<Edicao> edicoes = new ArrayList<Edicao>();
        ArrayList<Ator> atores = new ArrayList<Ator>();

        int indexEdicoes = 0;
        int numEdicao = 1;
        int ano = 3000;
        System.out.println("\t\t\tFESTIVAL CINEMA");
        System.out.print("(n): Começar um novo programa\t(c): Carregar um programa anterior\nOpção: ");
        Scanner scan = new Scanner(System.in, "cp1252");
        String opcao = scan.nextLine();
        while (opcao.length() > 1) {
            System.out.println("Por favor selecione uma das opções disponíveis.");
            System.out.print("(n): Começar um novo programa\t(c): Carregar um programa anterior\nOpção: ");
            opcao = scan.nextLine();
        }
        if (opcao.equalsIgnoreCase("c")) {
            novo = false;
        } else {
            edicoes.add(new Edicao(numEdicao, ano));
        }
        boolean quebra = false;

        while (!quebra) {
            System.out.println("*****************************************************************");
            System.out.print("\t\t\tOpções:\n(c): Criar algo\t(l): Listar algo\t(h): Criar Edição\t(s): Sair\nOpção: ");
            String opcaoGeral = scan.nextLine();
            while (opcaoGeral.length() > 1) {
                System.out.println("Por favor selecione uma das opções disponíveis.");
                System.out.print("(c): Criar algo\t(l): Listar algo\t(h): Criar Edição\t(s): Sair\nOpção: ");
                opcaoGeral = scan.nextLine();
            }
            switch (opcaoGeral.toLowerCase()) {

                case "c":
                    System.out.print("\t\t\tOpções\n(f): Criar Filme\t(a): Criar Ator\t(p): Atribui Papel\t(c): Escolher candidatos\nOpção: ");
                    opcao = scan.nextLine();
                    while (opcao.length() > 1) {
                        System.out.println("Por favor selecione uma das opções disponíveis.");
                        System.out.print("(f): Criar Filme\t(a): Criar Ator\t(p): Atribui Papel\nOpção: ");
                        opcao = scan.nextLine();
                    }
                    switch (opcao) {
                        case "f":
                            criarFilme(scan, numEdicao, indexEdicoes, edicoes);
                            break;
                        case "a":
                            criarAtor(scan, atores);
                            break;
                        case "p":
                            atribuirPapel(atores, scan, indexEdicoes, edicoes);
                            break;
                        case "c":
                            escolherCandidatos(edicoes, scan);
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
                            listarAtores(atores);
                            break;
                        case "f":
                            listarFilmes(edicoes);
                            break;
                        case "p":
                            listarPremios(edicoes);
                            break;
                        case "i":
                            consultarEdicoes(edicoes);
                            break;
                        case "c":
                            listarCandidatos(scan, edicoes, atores);
                            break;
                    }
                    break;
                case "n":
                    System.out.print("CRIAR EDIÇÃO:\nAno: ");
                    ano = scan.nextInt();
                    scan.nextLine();
                    numEdicao++;
                    edicoes.add(new Edicao(numEdicao, ano));
                    indexEdicoes++;
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
    public static void criarFilme(Scanner scan, int numEdicao, int indexEdicoes, ArrayList<Edicao> edicoes) {
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
    public static void criarAtor(Scanner scan, ArrayList<Ator> atores) {
        System.out.print("NOVO ATOR\nNome do Ator/Atriz: ");
        String nomeAtor = scan.nextLine();
        System.out.print("Ator ou Atriz (M-Masculino; F-Feminino): ");
        String generoAtor = scan.nextLine();
        while (generoAtor.length() > 1 || !(generoAtor.equalsIgnoreCase("M") || generoAtor.equalsIgnoreCase("F"))) { //verifica que inseriu uma opção válida
            System.out.print("Género Inválido. Género (M-Masculino; F-Feminino): ");
            generoAtor = scan.nextLine();
        }
        System.out.print("Anos de carreira do Ator/Atriz: ");
        int anosCarreira = scan.nextInt();
        scan.nextLine(); //discarda o enter
        Ator ator = new Ator(nomeAtor, generoAtor.equalsIgnoreCase("M"), anosCarreira);
        atores.add(ator); //adiciona o ator criado no array de atores
    }

    /**
     *
     * @param atores - array de atores criado durante a execução do programa
     * @param scan - o scanner
     * @param indexEdicoes - o indice que indica a edição atual
     * @param edicoes - o array das edições
     */
    public static void atribuirPapel(ArrayList<Ator> atores, Scanner scan, int indexEdicoes, ArrayList<Edicao> edicoes) {
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

    public static void consultarEdicoes(ArrayList<Edicao> edicoes) {
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

    public static void listarAtores(ArrayList<Ator> atores) {
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

    public static void listarFilmes(ArrayList<Edicao> edições) {
        int posiçãoFilme = 0;
        for (int posiçãoEdição = 0; posiçãoEdição < edições.size(); posiçãoEdição++) {
            System.out.println("EDIÇÃO: " + edições.get(posiçãoEdição).getNumEdicao());
            if (edições.get(posiçãoEdição).getFilmes().isEmpty()) {
                System.out.println("Não existem filmes nesta edição.\n");
            } else {
                System.out.println("Filmes: \n");
                while (posiçãoFilme < edições.get(posiçãoEdição).getFilmes().size()) {
                    System.out.print(edições.get(posiçãoEdição).getFilmes().get(posiçãoFilme) + "\n");
                    posiçãoFilme++;
                }
                posiçãoFilme = 0;
            }
        }
    }

    public static void listarPremios(ArrayList<Edicao> edições) {
        System.out.println("CATEGORIAS A PREMIAR:");
        edições.get(0).imprimePremios();
    }

    public static void escolherCandidatos(ArrayList<Edicao> edições, Scanner scan) {
        System.out.println("Escolha o prémio:");
        System.out.print("(1) Melhor Ator Principal\n" + "(2) Melhor Atriz Principal\n"
                + "(3) Melhor Ator Secundário\n" + "(4) Melhor Atriz Secundária\n"
                + "(5) Melhor Filme\n"
                + "(6) Melhor Realizador\n"
                + "(7) Melhor Argumento\n"
                + "(8) Melhor Cinematografia\nOPÇÃO: ");
        String opcao = scan.nextLine();
        switch (opcao) {
            case "1": //MELHOR ATOR PRINCIPAL
                ArrayList<Pessoa> atoresCandidatos1 = escolherAtoresPrincipaisCandidatos(scan, edições, true);
                edições.get(0).getPremios().get(0).setAtores(atoresCandidatos1);  //primeira ediçao apenas (mudar)
                break;
            case "2": //MELHOR ATRIZ PRINCIPAL
                ArrayList<Pessoa> atoresCandidatos2 = escolherAtoresPrincipaisCandidatos(scan, edições, false);
                edições.get(0).getPremios().get(1).setAtores(atoresCandidatos2);  //primeira ediçao apenas (mudar)
                break;
            case "3": //MELHOR ATOR SECUNDÁRIO
                ArrayList<Pessoa> atoresCandidatos3 = new ArrayList<Pessoa>(4);
                //listar e escolher ator:

                //... FALTA
                edições.get(0).getPremios().get(2).setAtores(atoresCandidatos3);  //primeira ediçao apenas (mudar)
                break;
            case "4": //MELHOR ATRIZ SECUNDÁRIA
                ArrayList<Pessoa> atoresCandidatos4 = new ArrayList<Pessoa>(4);
                //listar e escolher ator:

                //... FALTA
                edições.get(0).getPremios().get(3).setAtores(atoresCandidatos4);  //primeira ediçao apenas (mudar)
                break;
            case "5": //MELHOR FILME
                ArrayList<Filme> filmesCandidatos1 = escolherFilmesCandidatos(scan, edições);
                edições.get(0).getPremios().get(4).setFilmes(filmesCandidatos1);  //primeira ediçao apenas (mudar)
                break;
            case "6": //MELHOR REALIZADOR
                ArrayList<Filme> filmesCandidatos2 = escolherRealizadorCandidatos(scan, edições);
                edições.get(0).getPremios().get(5).setFilmes(filmesCandidatos2);  //primeira ediçao apenas (mudar)
                break;
            case "7": //MELHOR ARGUMENTO
                ArrayList<Filme> filmesCandidatos3 = escolherFilmesCandidatos(scan, edições);
                edições.get(0).getPremios().get(6).setFilmes(filmesCandidatos3);  //primeira ediçao apenas (mudar)
                break;
            case "8": //MELHOR CINEMATOGRAFIA
                ArrayList<Filme> filmesCandidatos4 = escolherFilmesCandidatos(scan, edições);
                edições.get(0).getPremios().get(7).setFilmes(filmesCandidatos4);  //primeira ediçao apenas (mudar)
                break;
        }
    }

    //método que permite o utilizador escolher os filmes candidatos para um dado prémio:
    public static ArrayList<Filme> escolherFilmesCandidatos(Scanner scan, ArrayList<Edicao> edições) {
        ArrayList<Filme> filmesCandidatos = new ArrayList<Filme>();
        int escolhendo = 1;
        while (escolhendo <= 4) { //até escolher os 4 candidatos
            int i = 1;
            for (Filme filme : edições.get(0).getFilmes()) {
                System.out.printf("%d. %s\n", i, filme.getNome());
                i++;
            }
            System.out.println("Escolha um candidato: ");
            int pos = scan.nextInt();
            scan.nextLine();
            try {
                Filme candidato = edições.get(0).getFilmes().get(pos - 1);
                filmesCandidatos.add(candidato);
                escolhendo++;
            } catch (Exception e) {
                System.out.println("Porfavor, escolha um filme válido.");
            }
        }
        // System.out.print(filmesCandidatos); //PARA TESTAR
        return filmesCandidatos;
    }

    //método que permite o utilizador escolher os melhores realizadores (devolve os filmes dos melhores realizadores):
    public static ArrayList<Filme> escolherRealizadorCandidatos(Scanner scan, ArrayList<Edicao> edições) {
        ArrayList<Filme> filmesCandidatos = new ArrayList<Filme>();
        int escolhendo = 1;
        while (escolhendo <= 4) { //até escolher os 4 candidatos
            int i = 1;
            for (Filme filme : edições.get(0).getFilmes()) {
                System.out.printf("%d. %s\n", i, filme.getRealizador().getNome()); //mostra nome do realizador do filme
                i++;
            }
            System.out.println("Escolha um candidato: ");
            int pos = scan.nextInt();
            scan.nextLine();
            try {
                Filme candidato = edições.get(0).getFilmes().get(pos - 1);
                filmesCandidatos.add(candidato); //adiciona filme do realizador
                escolhendo++;
            } catch (Exception e) {
                System.out.println("Porfavor, escolha um filme válido.");
            }
        }
        // System.out.print(filmesCandidatos); //PARA TESTAR
        return filmesCandidatos;
    }

    //método que permite o utilizador escolher os atores (e as atrizes) principais:
    public static ArrayList<Pessoa> escolherAtoresPrincipaisCandidatos(Scanner scan, ArrayList<Edicao> edições, boolean homem) {
        ArrayList<Pessoa> atoresCandidatos = new ArrayList<Pessoa>();
        int escolhendo = 1;
        while (escolhendo <= 4) { //até escolher os 4 candidatos
            int i = 1;
            if (homem) { //para ator principal
                for (Filme filme : edições.get(0).getFilmes()) {
                    System.out.printf("%d. %s\n", i, filme.getAtorPrincipal().getNome());
                    i++;
                }
                System.out.println("Escolha um candidato: ");
                int pos = scan.nextInt();
                scan.nextLine();
                try {
                    Filme candidato = edições.get(0).getFilmes().get(pos - 1);
                    atoresCandidatos.add(candidato.getAtorPrincipal());
                    escolhendo++;
                } catch (Exception e) {
                    System.out.println("Porfavor, escolha um filme válido.");
                }
            } else { //para atriz principal
                for (Filme filme : edições.get(0).getFilmes()) {
                    System.out.printf("%d. %s\n", i, filme.getAtrizPrincipal().getNome());
                    i++;
                }
                System.out.println("Escolha um candidato: ");
                int pos = scan.nextInt();
                scan.nextLine();
                try {
                    Filme candidato = edições.get(0).getFilmes().get(pos - 1);
                    atoresCandidatos.add(candidato.getAtrizPrincipal());
                    escolhendo++;
                } catch (Exception e) {
                    System.out.println("Porfavor, escolha um filme válido.");
                }
            }
        }
        // System.out.print(atoresCandidatos); //PARA TESTAR
        return atoresCandidatos;
    }

    //método que mostra os quatro candidatos ao prémio em cada categoria
    public static void listarCandidatos(Scanner scan, ArrayList<Edicao> edições, ArrayList<Ator> atores) {
        int contaPremios = 1;
        System.out.println("CANDIDATOS AOS PRÉMIOS:");

        for (Premio premio : edições.get(0).getPremios()) {
            if (contaPremios == 1) {
                System.out.println("- MELHOR ATOR PRINCIPAL:");
            } else if (contaPremios == 2) {
                System.out.println("- MELHOR ATRIZ PRINCIPAL:");
            } else if (contaPremios == 3) {
                System.out.println("- MELHOR ATOR SECUNDÁRIO:");
            } else if (contaPremios == 4) {
                System.out.println("- MELHOR ATRIZ SECUNDÁRIA:");
            } else if (contaPremios == 5) {
                System.out.println("- MELHOR FILME:");
            } else if (contaPremios == 6) {
                System.out.println("- MELHOR REALIZADOR:");
            } else if (contaPremios == 7) {
                System.out.println("- MELHOR ARGUMENTO:");
            } else if (contaPremios == 8) {
                System.out.println("- MELHOR CINEMATOGRAFIA:");
            }
            try {
                if (contaPremios == 1 || contaPremios == 2 || contaPremios == 3 || contaPremios == 4) {
                    for (int i = 0; i < 4; i++) {
                        System.out.println(premio.getAtoresCandidatos().get(i).getNome());
                    }
                } else if (contaPremios == 5 || contaPremios == 7 || contaPremios == 8) {
                    for (int i = 0; i < 4; i++) {
                        System.out.println(premio.getFilmesCandidatos().get(i).getNome());
                    }
                } else if (contaPremios == 6) {
                    for (int i = 0; i < 4; i++) {
                        System.out.println(premio.getFilmesCandidatos().get(i).getRealizador().getNome());
                    }
                }
            } catch (Exception e) {
                System.out.println("Não tem candidatos.");
            }
            contaPremios++;
        }
    }

}
