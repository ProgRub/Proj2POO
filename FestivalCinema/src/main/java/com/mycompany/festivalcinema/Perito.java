package com.mycompany.festivalcinema;

import java.util.Scanner;

public class Perito extends Pessoa {

    protected Perito(String nome, boolean genero) {
        super(nome, genero);
    }

    @Override
    public String toString() {
        String perito = super.toString();
        return perito;
    }

    /**
     *
     * @param premio - prémio no qual inserir a pontuação
     * @param indiceCandidato - linha na matriz das pontuações que indica a qual
     * candidato estamos a atribuir a pontuação
     * @param indicePerito - coluna na matriz das pontuações que indica o perito
     * que atribui a pontuação
     * @param scan - o scanner
     * @return true se a pontuação inserida é valida (entre 1 e 10 e inteira),
     * false caso contrário
     */
    protected boolean inserePontuacao(Premio premio, int indiceCandidato, int indicePerito, Scanner scan) {
        System.out.printf((this.getGenero() ? "O perito %s " : "A perita %s ") + "atribui ao candidato a pontuação: ", this.getNome());
        String aux;
        double pontuacao;
        while (true) { //não sai do while enquanto o utilizador não inserir um número
            aux = scan.nextLine();
            try {
                pontuacao = Double.parseDouble(aux);
                break; //só chega aqui se o parseDouble não atirar a NumberFormatException
            } catch (NumberFormatException e) { //entra aqui se não é inserido um número
                System.out.println("A pontuação deve ser um número!");
            }
        }
        if (pontuacao > 0 && pontuacao <= 10 && pontuacao == (int) pontuacao) { //verifica-se se a pontuação atribuída é inteira e se está entre 1 e 10, inclusive
            premio.setPontuacao(indiceCandidato, indicePerito, (int) pontuacao);
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Perito perito = (Perito) obj;
        return this.getNome().equalsIgnoreCase(perito.getNome()) && this.getGenero() == perito.getGenero();
    }
}
