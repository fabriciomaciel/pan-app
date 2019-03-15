package br.com.maciel.panapp.service;

import br.com.maciel.panapp.exception.PanAppException;
import br.com.maciel.panapp.model.Endereco;
import br.com.maciel.panapp.model.Pessoa;

public interface PessoaService {

  Pessoa consultarPessoa(String cpf) throws PanAppException;

  Endereco consultarEnderecoPessoa(String cpf, String cep) throws PanAppException;
}
