package br.com.maciel.panapp.repository;

import br.com.maciel.panapp.model.entity.PessoaEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PessoaRepository extends CrudRepository<PessoaEntity, Long> {

  List<PessoaEntity> findByCpfPessoa(String cpf);

  @Query("SELECT p FROM PessoaEntity p JOIN FETCH p.enderecos pe WHERE p.cpfPessoa = :cpf AND pe.cep = :cep")
  PessoaEntity findByCpfPessoaAndEnderecosCep(String cpf, String cep);

  @Query("SELECT p FROM PessoaEntity p JOIN FETCH p.enderecos pe WHERE p.cpfPessoa = :cpf AND pe.idEndereco = :idEndereco")
  PessoaEntity findByCpfPessoaAndEnderecosIdEndereco(String cpf, Long idEndereco);
}
