package br.com.maciel.panapp.controller;

import br.com.maciel.panapp.exception.PanAppException;
import br.com.maciel.panapp.model.Endereco;
import br.com.maciel.panapp.model.Pessoa;
import br.com.maciel.panapp.service.PessoaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PessoaController {

  private PessoaService service;

  private Logger logger;

  @Autowired
  public PessoaController(PessoaService service) {
    this.service = service;
    logger = LoggerFactory.getLogger(PessoaController.class);
  }

  @GetMapping(value = "/pessoa/{cpf}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<Pessoa> obterDadosPessoa(@PathVariable("cpf") String cpf) {
    logger.info("Recebendo requisição para obter os dados da pessoa com o parâmetro cpf: {}", cpf);
    //Validar os dados de entrada (gera uma exception em casos de erro)
    service.validarDadosEntrada(cpf, null);
    //Realiza a consulta
    Pessoa pessoa = service.consultarPessoa(cpf);

    if (pessoa == null) {
      return ResponseEntity.noContent().build();
    }
    //Retornar o resutado
    logger.info("Finalizando com sucesso a requisição da pessoa com o parâmetro cpf: {}", cpf);
    return ResponseEntity.ok(pessoa);
  }
  @GetMapping("/pessoa/{cpf}/endereco/{cep}")
  public ResponseEntity<Endereco> obterEnderecoPessoa(@PathVariable("cpf") String cpf, @PathVariable("cep") String cep) {
    logger.info("Recebendo requisição para obter os dados da pessoa com o parâmetro cpf: {}, cep: {}", cpf, cep);
    //Validar os dados de entrada (gera uma exception em casos de erro)
    service.validarDadosEntrada(cpf, cep);
    //Realiza a consulta
    Endereco endereco = service.consultarEnderecoPessoa(cpf, cep);

    if (endereco == null) {
      return ResponseEntity.noContent().build();
    }
    //Retornar o resutado
    logger.info("Finalizando com sucesso a requisição da pessoa com o parâmetro cpf: {}, cep: {}", cpf, cep);
    return ResponseEntity.ok(endereco);
  }

}
