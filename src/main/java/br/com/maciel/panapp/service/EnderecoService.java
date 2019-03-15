package br.com.maciel.panapp.service;

import br.com.maciel.panapp.model.Estado;
import br.com.maciel.panapp.model.Municipio;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EnderecoService {

    List<Estado> obterListaEstados();

    List<Municipio> obterListaMunicipios(Integer estadoId);

}
