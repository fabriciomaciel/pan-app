package br.com.maciel.panapp.service;

import br.com.maciel.panapp.exception.PanAppException;
import br.com.maciel.panapp.model.Pessoa;
import br.com.maciel.panapp.model.entity.PessoaEntity;
import br.com.maciel.panapp.repository.PessoaRepository;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PessoaServiceImpl implements PessoaService {

  private Validator validator;

  private PessoaRepository repository;

  public PessoaServiceImpl(Validator validator, PessoaRepository repository) {
    this.validator = validator;
    this.repository = repository;
  }

  public boolean validarCpf(String cpf) throws PanAppException {
    Set<ConstraintViolation<Object>> validateResult = validator.validate(new CPfModel(cpf));
    if(validateResult.size() > 0) {
      List<String> errorDetails = new ArrayList<String>();
      validateResult.forEach(it -> errorDetails.add(it.getMessage()));
      throw new PanAppException(HttpStatus.BAD_REQUEST, "Erro na validação do request", errorDetails);
    }
    return true;
  }

  @Override
  public Pessoa consultarPessoa(String cpf) throws PanAppException {
    List<PessoaEntity> pessoas = repository.findAllByCpfPessoa(cpf.replace(".", "").replace("-", ""));
    if(pessoas.size() == 0) {
      return null;
    } else {
      Pessoa p = new Pessoa();
      BeanUtils.copyProperties(pessoas.get(0), p);
      return p;
    }
  }
}

class CPfModel {
  @CPF(message = "O CPF Informado não é valido")
  private String cpf;

  public CPfModel(String cpf) {
    this.cpf = cpf;
  }
  public String getCpf() {
    return cpf;
  }
}
