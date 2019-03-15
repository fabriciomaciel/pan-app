package br.com.maciel.panapp.model;

public class Municipio {

    private Integer id;
    private String nome;

    public Municipio(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
