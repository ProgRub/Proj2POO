
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
                    System.out.print("\t\t\tOpções\n(f): Criar Filme\t(a): Criar Ator\t(p): Atribui Papel\nOpção: ");
                    opcao = scan.nextLine();
                    while (opcao.length() > 1) {
                        System.out.println("Por favor selecione uma das opções disponíveis.");
                        System.out.print("(f): Criar Filme\t(a): Criar Ator\t(p): Atribui Papel\nOpção: ");
                        opcao = scan.nextLine();
                    }
                    switch (opcao) {
                        case "f":
                            criarFilme(scan, numEdicao, indexEdicoes);
                            break;
                        case "a":
                            criarAtor(scan, atores);
                            break;
                        case "p":
                            atribuirPapel(atores, scan, indexEdicoes);
                            break;
                    }
                    break;
                case "l":
                    System.out.print("\t\t\tOpções\n(a): Listar Atores\t(f): Listar Filmes\t(p): Listar Prémios\t(i): Consultar Edição\nOpção: ");
                    opcao = scan.nextLine();
                    while (opcao.length() > 1) {
                        System.out.println("Por favor selecione uma das opções disponíveis.");
                        System.out.print("(f): Listar Filmes\t(a): Listar Atores\t(i): Consultar Edição\nOpção: ");
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

    public static void criarFilme(Scanner scan, int numEdicao, int indexEdicoes) {
        System.out.print("NOVO FILME\nNome do Filme: ");
        String nome = scan.nextLine();
        System.out.print("Género do Filme: ");
        String genero = scan.nextLine();
        System.out.print("Primeiro Nome do Realizador: ");
        String primeiroNomeRealizador = scan.nextLine();
        System.out.print("Último Nome do Realizador: ");
        String ultimoNomeRealizador = scan.nextLine();
        System.out.print("Género do Realizador (M-Masculino; F-Feminino): ");
        String generoRealizador = scan.nextLine();
        while (generoRealizador.length() > 1 || !(generoRealizador.equalsIgnoreCase("M") || generoRealizador.equalsIgnoreCase("F"))) {
            System.out.print("Género Inválido. Género (M-Masculino; F-Feminino): ");
            generoRealizador = scan.nextLine();
        }
        Realizador realizador = new Realizador(primeiroNomeRealizador, ultimoNomeRealizador, generoRealizador.equalsIgnoreCase("M"));
        Filme filme = new Filme(nome, genero, numEdicao, realizador);
        edicoes.get(indexEdicoes).insereFilmes(filme);
    }

    public static void criarAtor(Scanner scan, ArrayList<Ator> atores) {
        System.out.print("NOVO ATOR\nPrimeiro Nome do Ator/Atriz: ");
        String primeiroNomeAtor = scan.nextLine();
        System.out.print("Último Nome do Ator/Atriz: ");
        String ultimoNomeAtor = scan.nextLine();
        System.out.print("Ator ou Atriz (M-Masculino; F-Feminino): ");
        String generoAtor = scan.nextLine();
        while (generoAtor.length() > 1 || !(generoAtor.equalsIgnoreCase("M") || generoAtor.equalsIgnoreCase("F"))) {
            System.out.print("Género Inválido. Género (M-Masculino; F-Feminino): ");
            generoAtor = scan.nextLine();
        }
        System.out.print("Anos de carreira do Ator/Atriz: ");
        int anosCarreira = scan.nextInt();
        scan.nextLine();
        Ator ator = new Ator(primeiroNomeAtor, ultimoNomeAtor, generoAtor.equalsIgnoreCase("M"), anosCarreira);
        atores.add(ator);
    }

    public static void atribuirPapel(ArrayList<Ator> atores, Scanner scan, int indexEdicoes) {
        int i = 1;
        for (Ator a : atores) {
            System.out.printf("%d. %s\n", i, a.getPrimeiroNome() + " " + a.getUltimoNome());
            i++;
        }
        System.out.print("Escolha a posição do ator que quer inserir num filme\nPosição: ");
        int pos = scan.nextInt();
        scan.nextLine();
        try {
            Ator mudar = atores.get(pos - 1);
            if (mudar.podeInserirFilme()) {
                i = 1;
                for (Filme f : edicoes.get(indexEdicoes).getFilmes()) {
                    System.out.printf("%d. %s\n", i, f.getNome());
                    i++;
                }
                System.out.println("Qual o filme?");
                int posFilme = scan.nextInt();
                Filme casting = edicoes.get(indexEdicoes).getFilmes().get(posFilme - 1);
                scan.nextLine();
                System.out.println("Qual o papel do ator/atriz (P-Principal ou S-Secundário)?");
                String papel = scan.nextLine();
                while (papel.length() > 1) {
                    System.out.print("Opção inválida. Opção (P-Principal ou S-Secundário): ");
                    papel = scan.nextLine();
                }
                switch (papel.toLowerCase()) {
                    case "p":
                        casting.insereAtor(mudar, mudar.getGenero() ? 0 : 1);
                        mudar.inserirFilme(casting);
                        break;
                    case "s":
                        casting.insereAtor(mudar, 2);
                        mudar.inserirFilme(casting);
                        break;
                }
            }
        } catch (Exception e) {
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

    public static void escolherCandidatos(ArrayList<Edicao> edições) {
        System.out.println("Escolha o prémio:");
        System.out.print("(1) Melhor Ator Principal\n" + "(2) Melhor Atriz Principal\n"
                + "(3) Melhor Ator Secundário\n" + "(4) Melhor Atriz Secundária\n"
                + "(5) Melhor Filme\n"
                + "(6) Melhor Realizador\n"
                + "(7) Melhor Argumento\n"
                + "(8) Melhor Cinematografia)");
        Scanner scan = new Scanner(System.in, "cp1252");
        String opcao = scan.nextLine();
        switch (opcao) {
            case "1":
                ArrayList<Pessoa> atoresCandidatos1 = new ArrayList<Pessoa>(4);
                //listar e escolher ator:

                edições.get(0).getPremios().get(0).setAtores(atoresCandidatos1);  //primeira ediçao apenas (mudar)
                break;
            case "2":
                ArrayList<Pessoa> atoresCandidatos2 = new ArrayList<Pessoa>(4);
                //listar e escolher atriz:

                edições.get(0).getPremios().get(1).setAtores(atoresCandidatos2);  //primeira ediçao apenas (mudar)
                break;
            case "3":
                ArrayList<Pessoa> atoresCandidatos3 = new ArrayList<Pessoa>(4);
                //listar e escolher ator:

                edições.get(0).getPremios().get(2).setAtores(atoresCandidatos3);  //primeira ediçao apenas (mudar)
                break;
            case "4":
                ArrayList<Pessoa> atoresCandidatos4 = new ArrayList<Pessoa>(4);
                //listar e escolher ator:

                edições.get(0).getPremios().get(3).setAtores(atoresCandidatos4);  //primeira ediçao apenas (mudar)
                break;
            case "5":
                ArrayList<Filme> filmesCandidatos1 = new ArrayList<Filme>(4);
                escolherFilmesCandidatos(edições);
                edições.get(0).getPremios().get(4).setFilmes(filmesCandidatos1);  //primeira ediçao apenas (mudar)
                break;
            case "6":
                ArrayList<Filme> filmesCandidatos2 = new ArrayList<Filme>(4);
                escolherFilmesCandidatos(edições);
                edições.get(0).getPremios().get(5).setFilmes(filmesCandidatos2);  //primeira ediçao apenas (mudar)
                break;
            case "7":
                ArrayList<Filme> filmesCandidatos3 = new ArrayList<Filme>(4);
                escolherFilmesCandidatos(edições);
                edições.get(0).getPremios().get(6).setFilmes(filmesCandidatos3);  //primeira ediçao apenas (mudar)
                break;
            case "8":
                ArrayList<Filme> filmesCandidatos4 = new ArrayList<Filme>(4);
                escolherFilmesCandidatos(edições);
                edições.get(0).getPremios().get(7).setFilmes(filmesCandidatos4);  //primeira ediçao apenas (mudar)
                break;
        }
    }

    public static void escolherFilmesCandidatos(ArrayList<Edicao> edições) {
        int posiçãoFilme = 0;
        //MUDAR PARA COMO O RÚBEN FEZ
        if (edições.get(0).getFilmes().isEmpty()) {
            System.out.println("Não existem filmes nesta edição.\n");
        } else {
            System.out.println("Filmes: \n");
            while (posiçãoFilme < edições.get(0).getFilmes().size()) {
                System.out.print("(" + posiçãoFilme + 1 + ") " + edições.get(0).getFilmes().get(posiçãoFilme).getNome() + "\n");
                posiçãoFilme++;
            }
        }
    }
}
