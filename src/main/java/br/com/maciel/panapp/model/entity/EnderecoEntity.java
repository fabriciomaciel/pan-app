package br.com.maciel.panapp.model.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;

@Entity
@Table(name = "PessoaEndereco")
public class EnderecoEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long idEndereco;
  private String apelidoEndereco;
  private String logradouro;
  private Integer numeroLogradouro;
  private String complemento;
  private String bairro;
  private String localidade;
  private String uf;
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
