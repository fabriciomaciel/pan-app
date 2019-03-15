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

  private Validator validator;

  private PessoaRepository repository;

  private Logger logger;

  public PessoaServiceImpl(Validator validator, PessoaRepository repository) {
    this.validator = validator;
    this.repository = repository;
    logger = LoggerFactory.getLogger(PessoaServiceImpl.class);
  }

  public boolean validarDadosEntrada(String cpf, String cep) throws PanAppException {
    Set<ConstraintViolation<Object>> validateResult = validator.validate(new PessoaRequestModel(cpf, cep));
    if(validateResult.size() > 0) {
      List<String> errorDetails = new ArrayList<String>();
      validateResult.forEach( it -> {
                errorDetails.add(it.getMessage());
                logger.error("Erro de validação para os parâmetros cpf: {}, cep: {}, erro: {}.", cpf, cep, it.getMessage());
              });
      throw new PanAppException(HttpStatus.BAD_REQUEST, "Erro na validação de dados informados.", errorDetails);
    }
    logger.info("Validação realizada com sucesso para os parâmetros cpf: {}, cep: {}.", cpf, cep);
    return true;
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

  class PessoaRequestModel  {
  @CPF(message = "O CPF Informado não é valido")
  private String cpf;
  @Size(min = 8, max = 8, message = "O Cep informado não possui o formato correto")
  private String cep;

    public PessoaRequestModel(String cpf,String cep) {
      this.cpf = cpf;
      this.cep = cep;
    }

    public String getCpf() {
      return cpf;
    }

    public String getCep() {
      return cep;
    }
  }
