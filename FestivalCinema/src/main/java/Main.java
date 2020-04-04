
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean novo = true;
        ArrayList<Ator> atores = new ArrayList();
        int numEdicao = 1;
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
        }
        boolean quebra = false;
        while (!quebra) {
            System.out.println("*********************");
            System.out.print("\t\t\tOpções\n(f): Criar Filme\t(a): Criar Ator\t(p) Atribui Papel\t(s): Sair\nOpção: ");
            opcao = scan.nextLine();
            while (opcao.length() > 1) {
                System.out.println("Por favor selecione uma das opções disponíveis.");
                System.out.print("(f): Criar Filme\t(a): Criar Ator\nOpção: ");
                opcao = scan.nextLine();
            }
            switch (opcao.toLowerCase()) {
                case "f":
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
                    Filme f = new Filme(nome, genero, numEdicao, realizador);
                    System.out.println(f);
                    break;
                case "a":
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
                    Ator ator = new Ator(primeiroNomeAtor, ultimoNomeAtor, generoAtor.equalsIgnoreCase("M"), anosCarreira);
                    atores.add(ator);
                    System.out.print(ator);
                    break;
                case "p":
                    int i = 1;
                    for (Ator a : atores) {
                        System.out.printf("%d. %s", i, a.getPrimeiroNome() + " " + a.getUltimoNome());
                        i++;
                    }
                    System.out.print("Escolha a posição do ator que quer inserir num filme\nPosição: ");
                    int pos = scan.nextInt();
                    try {
                        Ator mudar = atores.get(pos-1);
                        i = 1;
//                        for (Filme f : edicao.getFilmes) {
//                            System.out.printf("%d. %s", i, a.getPrimeiroNome() + " " + a.getUltimoNome());
//                            i++;
//                        }
                        System.out.println("Qual o filme?");
                        int posFilme = scan.nextInt();
                        System.out.println("Qual o papel do ator/atriz (P-Principal ou S-Secundário)?");
                        String papel = scan.nextLine();
                        while (papel.length() > 1) {
                            System.out.print("Opção inválida. Opção (P-Principal ou S-Secundário): ");
                            papel = scan.nextLine();
                        }
                        switch (papel.toLowerCase()) {
                            case "p":

                                break;
                            case "s":
                                break;
                        }
                    } catch (Exception e) {
                        System.out.println("Posição errada.");
                    }
            }
            quebra = true;
        }
    }

}
