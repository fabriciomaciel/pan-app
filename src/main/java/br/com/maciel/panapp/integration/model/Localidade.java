package br.com.maciel.panapp.integration.model;

/**
 * Representa uma Localidade retornada pelo serviço de integração com IBGE
 */
public class Localidade {

    private Integer id;
    private String sigla;
    private String nome;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
