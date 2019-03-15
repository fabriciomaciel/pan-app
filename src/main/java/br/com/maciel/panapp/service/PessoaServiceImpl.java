package br.com.maciel.panapp.service;

import br.com.maciel.panapp.exception.PanAppException;
import br.com.maciel.panapp.model.Endereco;
import br.com.maciel.panapp.model.Pessoa;
import br.com.maciel.panapp.model.entity.PessoaEntity;
import br.com.maciel.panapp.repository.PessoaRepository;
import org.hibernate.validator.constraints.br.CPF;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PessoaServiceImpl implements PessoaService {


  private PessoaRepository repository;

  private Logger logger;

  public PessoaServiceImpl(PessoaRepository repository) {
    this.repository = repository;
    logger = LoggerFactory.getLogger(PessoaServiceImpl.class);
  }

  @Override
  public Pessoa consultarPessoa(String cpf) throws PanAppException {
    logger.info("Realizando consulta da pessoa com o parâmetro cpf: {}.", cpf);
    List<PessoaEntity> pessoas = repository.findAllByCpfPessoa(cpf.replace(".", "").replace("-", ""));
    if(pessoas.size() == 0) {
      logger.error("Nenhuma pessoa encontrada com o parâmetro cpf: {}.", cpf);
      return null;    }
    else {
      Pessoa p = new Pessoa();
      BeanUtils.copyProperties(pessoas.get(0), p);
      logger.error("Pessoa encontrada com o parâmetro cpf: {}, -> {}.", cpf, p);
      return p;
    }
  }

  public Endereco consultarEnderecoPessoa(String cpf, String cep) throws PanAppException {
    List<PessoaEntity> pessoaEndereco = repository.findAllByCpfPessoaAndEnderecosCep(cpf.replace(".", "").replace("-", ""), cep.replace("-", ""));
    if(pessoaEndereco.get(0).getEnderecos().size()==0) { return null;}
    else {
      Endereco e = new Endereco();
      BeanUtils.copyProperties(pessoaEndereco.get(0).getEnderecos().get(0), e);
      return e;
    }
  }
}


