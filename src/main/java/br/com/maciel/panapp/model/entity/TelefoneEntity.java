package br.com.maciel.panapp.model.entity;

import br.com.maciel.panapp.model.TIPO_TELEFONE;

import javax.persistence.*;

@Entity
@Table(name = "PessoaTelefone")
public class TelefoneEntity {

  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private Long idTelefone;
  @Enumerated(EnumType.STRING)
  private TIPO_TELEFONE tipoTelefone;
  private Integer ddd;
  private Integer numeroTelefone;

  public TelefoneEntity() {
    super();
  }

  public TelefoneEntity(TIPO_TELEFONE tipoTelefone, Integer ddd, Integer numeroTelefone) {
    this.tipoTelefone = tipoTelefone;
    this.ddd = ddd;
    this.numeroTelefone = numeroTelefone;
  }

  public Long getIdTelefone() {
    return idTelefone;
  }

  public void setIdTelefone(Long idTelefone) {
    this.idTelefone = idTelefone;
  }

  public TIPO_TELEFONE getTipoTelefone() {
    return tipoTelefone;
  }

  public void setTipoTelefone(TIPO_TELEFONE tipoTelefone) {
    this.tipoTelefone = tipoTelefone;
  }

  public Integer getDdd() {
    return ddd;
  }

  public void setDdd(Integer ddd) {
    this.ddd = ddd;
  }

  public Integer getNumeroTelefone() {
    return numeroTelefone;
  }

  public void setNumeroTelefone(Integer numeroTelefone) {
    this.numeroTelefone = numeroTelefone;
  }

  @Override
  public String toString() {
    return "Telefone "
        + tipoTelefone +
        ", (" + ddd +") " + numeroTelefone;
  }
}