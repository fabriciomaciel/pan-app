package br.com.maciel.panapp.service;

import br.com.maciel.panapp.exception.PanAppException;
import br.com.maciel.panapp.model.Endereco;
import br.com.maciel.panapp.model.Pessoa;
import org.springframework.stereotype.Service;

public interface PessoaService {

  boolean validarDadosEntrada(String cpf, String cep) throws PanAppException;

  Pessoa consultarPessoa(String cpf) throws PanAppException;

  public Endereco consultarEnderecoPessoa(String cpf, String cep) throws PanAppException;
}
