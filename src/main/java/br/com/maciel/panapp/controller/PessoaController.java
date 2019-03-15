package br.com.maciel.panapp.controller;

import br.com.maciel.panapp.model.Endereco;
import br.com.maciel.panapp.model.Pessoa;
import br.com.maciel.panapp.service.PessoaService;
import br.com.maciel.panapp.service.UtilService;
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

  private UtilService utilService;

  private PessoaService service;

  private static final Logger LOGGER = LoggerFactory.getLogger(PessoaController.class);

  @Autowired
  public PessoaController(UtilService utilService, PessoaService service) {
    this.utilService = utilService;
    this.service = service;
  }

  /**
   * Obtem os dados cadastrais de uma pessoa
   * @param cpf
   * @return
   */
  @GetMapping(value = "/pessoa/{cpf}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<Pessoa> obterDadosPessoa(@PathVariable("cpf") String cpf) {
    LOGGER.info("Recebendo requisição para obter os dados da pessoa com o parâmetro cpf: {}", cpf);
    //Validar os dados de entrada (gera uma exception em casos de erro)
    utilService.validarDadosEntrada(cpf, null);
    //Realiza a consulta
    Pessoa pessoa = service.consultarPessoa(cpf);

    if (pessoa == null) {
      return ResponseEntity.noContent().build();
    }
    //Retornar o resutado
    LOGGER.info("Finalizando com sucesso a requisição da pessoa com o parâmetro cpf: {}", cpf);
    return ResponseEntity.ok(pessoa);
  }

  /**
   * Obtem os dados de endereço de uma pessoa
   * @param cpf
   * @param cep
   * @return
   */
  @GetMapping("/pessoa/{cpf}/endereco/{cep}")
  public ResponseEntity<Endereco> obterEnderecoPessoa(@PathVariable("cpf") String cpf, @PathVariable("cep") String cep) {
    LOGGER.info("Recebendo requisição para obter os dados da pessoa com o parâmetro cpf: {}, cep: {}", cpf, cep);
    //Validar os dados de entrada (gera uma exception em casos de erro)
    utilService.validarDadosEntrada(cpf, cep);
    //Realiza a consulta
    Endereco endereco = service.consultarEnderecoPessoa(cpf, cep);

    if (endereco == null) {
      return ResponseEntity.noContent().build();
    }
    //Retornar o resutado
    LOGGER.info("Finalizando com sucesso a requisição da pessoa com o parâmetro cpf: {}, cep: {}", cpf, cep);
    return ResponseEntity.ok(endereco);
  }

}
