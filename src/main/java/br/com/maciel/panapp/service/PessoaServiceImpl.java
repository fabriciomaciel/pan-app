package br.com.maciel.panapp.service;

import br.com.maciel.panapp.exception.PanAppException;
import br.com.maciel.panapp.model.Endereco;
import br.com.maciel.panapp.model.Pessoa;
import br.com.maciel.panapp.model.entity.PessoaEntity;
import br.com.maciel.panapp.repository.PessoaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaServiceImpl implements PessoaService {

  private PessoaRepository repository;

  private static final Logger LOGGER = LoggerFactory.getLogger(PessoaServiceImpl.class);

  public PessoaServiceImpl(PessoaRepository repository) {
    this.repository = repository;
  }

  @Override
  public Pessoa consultarPessoa(String cpf) throws PanAppException {
    LOGGER.info("Realizando consulta da pessoa com o parâmetro cpf: {}.", cpf);
    List<PessoaEntity> pessoas = repository.findAllByCpfPessoa(cpf.replace(".", "").replace("-", ""));
    if(pessoas.isEmpty()) {
      LOGGER.error("Nenhuma pessoa encontrada com o parâmetro cpf: {}.", cpf);
      return null;    }
    else {
      Pessoa p = new Pessoa();
      BeanUtils.copyProperties(pessoas.get(0), p);
      LOGGER.error("Pessoa encontrada com o parâmetro cpf: {}, -> {}.", cpf, p);
      return p;
    }
  }

  public Endereco consultarEnderecoPessoa(String cpf, String cep) throws PanAppException {
    List<PessoaEntity> pessoaEndereco = repository.findAllByCpfPessoaAndEnderecosCep(cpf.replace(".", "").replace("-", ""), cep.replace("-", ""));
    if(pessoaEndereco.get(0).getEnderecos().isEmpty()) { return null;}
    else {
      Endereco e = new Endereco();
      BeanUtils.copyProperties(pessoaEndereco.get(0).getEnderecos().get(0), e);
      return e;
    }
  }
}


