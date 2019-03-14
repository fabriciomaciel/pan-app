package br.com.maciel.panapp.service;

import br.com.maciel.panapp.exception.PanAppException;
import br.com.maciel.panapp.model.Pessoa;
import org.springframework.stereotype.Service;

public interface PessoaService {

  boolean validarCpf(String cpf) throws PanAppException;

  Pessoa consultarPessoa(String cpf) throws PanAppException;

}
