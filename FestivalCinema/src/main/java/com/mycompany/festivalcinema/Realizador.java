package com.mycompany.festivalcinema;

public class Realizador extends Pessoa {

    protected Realizador(String nome, boolean genero) {
        super(nome, genero);
    }

    @Override
    public String toString() {
        return super.toString();
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
        Realizador reali = (Realizador) obj;
        return this.getGenero() == reali.getGenero() && this.getNome().equalsIgnoreCase(reali.getNome());
    }
}
