
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean novo = true;
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
            System.out.print("\t\t\tOpções\n(f): Criar Filme\t(a): Criar Ator\nOpção: ");
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
                    break;
            }
            quebra = true;
        }
    }

}
