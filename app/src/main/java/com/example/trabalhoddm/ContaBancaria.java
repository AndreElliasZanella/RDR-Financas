package com.example.trabalhoddm;

public class ContaBancaria {

    private String nome;
    private String tipo;
    private Banco banco;

    public ContaBancaria(String nome, String tipo, Banco banco) {
        this.nome = nome;
        this.tipo = tipo;
        this.banco = banco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }
}
