package br.com.maciel.panapp.controller;

import br.com.maciel.panapp.exception.PanAppException;
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
    //Validar os dados de entrada (gera uma exception em casos de erro)
    service.validarCpf(cpf);
    //Realiza a consulta
    Pessoa pessoa = service.consultarPessoa(cpf);

    if (pessoa == null) {
      return ResponseEntity.noContent().build();
    }
    //Retornar o resutado
    return ResponseEntity.ok(pessoa);
  }


}
