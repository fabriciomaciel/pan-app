package br.com.maciel.panapp.model;

public class Telefone {

  private TIPO_TELEFONE tipoTelefone;
  private Integer ddd;
  private Integer numeroTelefone;

  public Telefone() {
  }

  public Telefone(TIPO_TELEFONE tipoTelefone, Integer ddd, Integer numeroTelefone) {
    this.tipoTelefone = tipoTelefone;
    this.ddd = ddd;
    this.numeroTelefone = numeroTelefone;
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