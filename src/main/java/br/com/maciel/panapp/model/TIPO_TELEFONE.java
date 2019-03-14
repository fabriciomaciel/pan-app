package br.com.maciel.panapp.model;

public enum TIPO_TELEFONE {

  RESIDENCIAL("Residencial"),
  COMERCIAL("Comercial"),
  RECADO("Recado"),
  CELULAR("Celular");

  private String tipo;
  TIPO_TELEFONE(String tipo) {
    this.tipo = tipo;
  }
}

