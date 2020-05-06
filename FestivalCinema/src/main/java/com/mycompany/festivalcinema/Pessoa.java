package com.mycompany.festivalcinema;

public abstract class Pessoa {

    private final String nome;
    private final boolean genero; //true->Homem false->Mulher

    protected Pessoa(String nome, boolean genero) {
        this.nome = nome;
        this.genero = genero;
    }

    protected String getNome() {
        return nome;
    }

    protected boolean getGenero() {
        return genero;
    }

    @Override
    public String toString() {
        String pessoa;
        pessoa = "Nome: " + getNome() + "\n";
        pessoa += "Género: " + (getGenero() ? "Masculino" : "Feminino") + "\n";
        return pessoa;
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
        Pessoa pes = (Pessoa) obj;
        return this.genero == pes.genero && this.nome.equalsIgnoreCase(pes.nome);
    }
}
