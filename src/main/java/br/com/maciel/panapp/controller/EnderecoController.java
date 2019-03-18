package br.com.maciel.panapp.controller;

import br.com.maciel.panapp.model.Endereco;
import br.com.maciel.panapp.model.Estado;
import br.com.maciel.panapp.model.Municipio;
import br.com.maciel.panapp.service.EnderecoService;
import br.com.maciel.panapp.service.UtilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EnderecoController {

    private UtilService utilService;

    private EnderecoService service;

    private static final Logger LOGGER = LoggerFactory.getLogger(EnderecoController.class);


    @Autowired
    public EnderecoController(UtilService utilService, EnderecoService service) {
        this.utilService = utilService;
        this.service = service;
    }

    /**
     * Endpoint de entrada para obter uma lista de Estados
     * @return
     */
    @GetMapping(value = "/endereco/estados", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Estado>> obterListaEstados() {
        LOGGER.info("Recebendo requisição para obter lista de estados.");
        List<Estado> listaEstados = service.obterListaEstados();
        if(listaEstados.isEmpty()) {
            LOGGER.error("Nenhum estado encontrado.");
            return ResponseEntity.noContent().build();
        }
        LOGGER.info("Finalizando com sucesso a requisição de lista de estados");
        return ResponseEntity.ok(listaEstados);
    }

    /**
     * Endpoint de entrada para obter uma lista de Municipios de um determinado Estado
     * @param estadoId
     * @return
     */
    @GetMapping(value = "/endereco/estados/{estadoId}/municipios", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Municipio>> obterListaMunicipios(@PathVariable("estadoId") Integer estadoId) {
        LOGGER.info("Recebendo requisição para obter lista de municipios com o parametro estadoId: {}.", estadoId);
        if(estadoId <= 0) {
            LOGGER.error("Erro de validação para o parâmetro estadoId: {},  erro: o numero informado é invalido.", estadoId);
            return ResponseEntity.badRequest().build();
        }
        List<Municipio> listaMunicipios = service.obterListaMunicipios(estadoId);
        if(listaMunicipios.isEmpty()) {
            LOGGER.error("Nenhum municipio encontrado com o parâmetro estadoId: {}.", estadoId);
            return ResponseEntity.noContent().build();
        }
        LOGGER.info("Finalizando com sucesso a requisição de lista de municipios para o parâmetros estadoId: {}.", estadoId);
        return ResponseEntity.ok(listaMunicipios);
    }

    /**
     * Obtem os dados de um endereço pelo seu CEP
     * @param cep
     * @return
     */
    @GetMapping(value = "/endereco/cep/{cep}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Endereco> obterEnderecoDoCep(@PathVariable("cep") String cep) {
        LOGGER.info("Recebendo requisição para obter os dados do endereço com o parâmetro cep: {}", cep);
        //Validar os dados de entrada (gera uma exception em casos de erro)
        utilService.validarDadosEntrada(null, cep, null, null);
        //
        Endereco endereco = service.obterEnderecoDoCep(cep);
        if(endereco == null) {
            LOGGER.error("Nenhum endereço encontrado com o parâmetro cep: {}.", cep);
            return ResponseEntity.noContent().build();
        }
        //
        LOGGER.info("Finalizando com sucesso a requisição para obter os dados do endereço com o parâmetro cep: {}", cep);
        return ResponseEntity.ok(endereco);
    }
}
