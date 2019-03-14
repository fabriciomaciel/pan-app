package br.com.maciel.panapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Endereco {

  private String apelidoEndereco;
  private String logradouro;
  private Integer numeroLogradouro;
  private String complemento;
  private String bairro;
  @JsonProperty("cidade")
  private String localidade;
  @JsonProperty("estado")
  private String uf;
  private String cep;

  public String getApelidoEndereco() {
    return apelidoEndereco;
  }

  public void setApelidoEndereco(String apelidoEndereco) {
    this.apelidoEndereco = apelidoEndereco;
  }

  public String getLogradouro() {
    return logradouro;
  }

  public void setLogradouro(String logradouro) {
    this.logradouro = logradouro;
  }

  public Integer getNumeroLogradouro() {
    return numeroLogradouro;
  }

  public void setNumeroLogradouro(Integer numeroLogradouro) {
    this.numeroLogradouro = numeroLogradouro;
  }

  public String getComplemento() {
    return complemento;
  }

  public void setComplemento(String complemento) {
    this.complemento = complemento;
  }

  public String getBairro() {
    return bairro;
  }

  public void setBairro(String bairro) {
    this.bairro = bairro;
  }

  public String getLocalidade() {
    return localidade;
  }

  public void setLocalidade(String localidade) {
    this.localidade = localidade;
  }

  public String getUf() {
    return uf;
  }

  public void setUf(String uf) {
    this.uf = uf;
  }

  public String getCep() {
    return cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }
}
