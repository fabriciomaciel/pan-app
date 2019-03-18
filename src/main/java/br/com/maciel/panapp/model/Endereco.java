package br.com.maciel.panapp.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Endereco {

  private Long idEndereco;
  @NotNull(message = "O campo Endereco -> apelidoEndereco, é obrigatório")
  private String apelidoEndereco;
  @NotNull(message = "O campo Endereco -> logradouro, é obrigatório")
  private String logradouro;
  @NotNull(message = "O campo Endereco -> numeroLogradouro, é obrigatório")
  private Integer numeroLogradouro;
  private String complemento;
  @NotNull(message = "O campo Endereco -> bairro, é obrigatório")
  private String bairro;
  @NotNull(message = "O campo Endereco -> localidade, é obrigatório")
  @JsonProperty("cidade")
  @JsonAlias({ "localidade", "cidade" }) //Necessário para mapeamento com ViaCep
  private String localidade;
  @NotNull(message = "O campo Endereco -> uf, é obrigatório")
  @JsonProperty("estado")
  @JsonAlias({ "uf", "estado" }) //Necessário para mapeamento com ViaCep
  private String uf;
  @NotNull(message = "O campo Endereco -> cep, é obrigatório")
  private String cep;

  public Long getIdEndereco() {
    return idEndereco;
  }

  public void setIdEndereco(Long idEndereco) {
    this.idEndereco = idEndereco;
  }

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
