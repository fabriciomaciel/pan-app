package br.com.maciel.panapp.model;

public class Estado {

    private Integer id;
    private String sigla;
    private String nome;

    public Estado(Integer id,  String sigla, String nome) {
        this.id = id;
        this.sigla = sigla;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public String getSigla() {
        return sigla;
    }

    public String getNome() {
        return nome;
    }
}
