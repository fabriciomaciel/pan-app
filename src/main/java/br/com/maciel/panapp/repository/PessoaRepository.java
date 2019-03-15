package br.com.maciel.panapp.repository;

import br.com.maciel.panapp.model.entity.EnderecoEntity;
import br.com.maciel.panapp.model.entity.PessoaEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PessoaRepository extends CrudRepository<PessoaEntity, Long> {

  List<PessoaEntity> findAllByCpfPessoa(String cpf);

  List<PessoaEntity> findAllByCpfPessoaAndEnderecosCep(String cpf, String cep);
}
