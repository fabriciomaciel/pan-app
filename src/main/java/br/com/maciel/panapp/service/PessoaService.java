package br.com.maciel.panapp.service;

import br.com.maciel.panapp.model.Endereco;
import br.com.maciel.panapp.model.Pessoa;

import java.util.List;

public interface PessoaService {

  Pessoa consultarPessoa(String cpf);

  List<Endereco> consultarEnderecoPessoa(String cpf, String cep);

  Endereco alterarEnderecoPessoa(String cpf, Long enderecoId, Endereco enderecoAlterado);
}
