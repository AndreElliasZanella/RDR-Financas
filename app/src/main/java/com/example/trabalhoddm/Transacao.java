package com.example.trabalhoddm;

import java.util.Date;

public class Transacao {

    private String tipo;
    private String descricao;
    private String data;
    private Double valor;
    private Categoria categoria;
    private SubCategoria subcategoria;
    private Banco banco;
    private ContaBancaria contaBancaria;

    public Transacao(String tipo, String descricao, String data, Double valor, Categoria categoria, SubCategoria subcategoria, Banco banco, ContaBancaria contaBancaria) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.data = data;
        this.valor = valor;
        this.categoria = categoria;
        this.subcategoria = subcategoria;
        this.banco = banco;
        this.contaBancaria = contaBancaria;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public SubCategoria getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(SubCategoria subcategoria) {
        this.subcategoria = subcategoria;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public ContaBancaria getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(ContaBancaria contaBancaria) {
        this.contaBancaria = contaBancaria;
    }
}
