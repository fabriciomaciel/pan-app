package br.com.maciel.panapp.controller;

import br.com.maciel.panapp.model.Estado;
import br.com.maciel.panapp.model.Municipio;
import br.com.maciel.panapp.service.EnderecoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EnderecoController {

    private EnderecoService service;

    private Logger logger;

    public EnderecoController(EnderecoService service) {
        this.service = service;
        logger = LoggerFactory.getLogger(EnderecoController.class);
    }

    /**
     * Endpoint de entrada para obter uma lista de Estados
     * @return
     */
    @GetMapping(value = "/endereco/estados", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Estado>> obterListaEstados() {
        logger.info("Recebendo requisição para obter lista de estados.");
        List<Estado> listaEstados = service.obterListaEstados();
        if(listaEstados.size() == 0) {
            logger.error("Nenhum estado encontrado.");
            return ResponseEntity.noContent().build();
        }
        logger.info("Finalizando com sucesso a requisição de lista de estados");
        return ResponseEntity.ok(listaEstados);
    }

    /**
     * Endpoint de entrada para obter uma lista de Municipios de um determinado Estado
     * @param estadoId
     * @return
     */
    @GetMapping(value = "/endereco/estados/{estadoId}/municipios", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Municipio>> obterListaMunicipios(@PathVariable("estadoId") Integer estadoId) {
        logger.info("Recebendo requisição para obter lista de municipios com o parametro estadoId: {}.", estadoId);
        if(estadoId <= 0) {
            logger.error("Erro de validação para o parâmetro estadoId: {},  erro: o numero informado é invalido.", estadoId);
            return ResponseEntity.badRequest().build();
        }
        List<Municipio> listaMunicipios = service.obterListaMunicipios(estadoId);
        if(listaMunicipios.size() == 0) {
            logger.error("Nenhum municipio encontrado com o parâmetro estadoId: {}.", estadoId);
            return ResponseEntity.noContent().build();
        }
        logger.info("Finalizando com sucesso a requisição de lista de municipios para o parâmetros estadoId: {}.", estadoId);
        return ResponseEntity.ok(listaMunicipios);
    }

}
