package br.com.maciel.panapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Pessoa {

  @JsonProperty("nome")
  private String nomePessoa;
  @JsonProperty("cpf")
  private String cpfPessoa;
  @JsonProperty("nomePai")
  private String filiacaoPai;
  @JsonProperty("nomeMae")
  private String filiacaoMae;

  private List<Telefone> telefones;

  private List<Endereco> enderecos;


  public String getNomePessoa() {
    return nomePessoa;
  }

  public void setNomePessoa(String nomePessoa) {
    this.nomePessoa = nomePessoa;
  }

  public String getCpfPessoa() {
    return cpfPessoa;
  }

  public void setCpfPessoa(String cpfPessoa) {
    this.cpfPessoa = cpfPessoa;
  }

  public String getFiliacaoPai() {
    return filiacaoPai;
  }

  public void setFiliacaoPai(String filiacaoPai) {
    this.filiacaoPai = filiacaoPai;
  }

  public String getFiliacaoMae() {
    return filiacaoMae;
  }

  public void setFiliacaoMae(String filiacaoMae) {
    this.filiacaoMae = filiacaoMae;
  }

  public List<Telefone> getTelefones() {
    return telefones;
  }

  public void setTelefones(List<Telefone> telefones) {
    this.telefones = telefones;
  }

  public List<Endereco> getEnderecos() {
    return enderecos;
  }

  public void setEnderecos(List<Endereco> enderecos) {
    this.enderecos = enderecos;
  }
}