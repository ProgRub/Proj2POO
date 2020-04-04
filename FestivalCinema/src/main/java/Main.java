
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean novo = true;
        System.out.println("\t\t\tFESTIVAL CINEMA");
        System.out.print("(n): Começar um novo programa \t(c): Carregar um programa anterior\nOpção:");
        Scanner scan = new Scanner(System.in, "cp1252");
        String opcao = scan.nextLine();
        while (opcao.length() >= 2) {
            System.out.println("Por favor selecione uma das opções disponíveis.");
//            try {
//                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
//            } catch (Exception e) {
//
//            }
            //System.out.println("\t\t\tFESTIVAL CINEMA");
            System.out.print("(n): Começar um novo programa \t(c): Carregar um programa anterior\nOpção:");
            opcao = scan.nextLine();
        }
        if (opcao.equalsIgnoreCase("c")) {
            novo = false;
        }
        boolean quebra = false;
        while (!quebra) {
            System.out.println("CHEGOU");
            quebra = true;
        }
    }

}
