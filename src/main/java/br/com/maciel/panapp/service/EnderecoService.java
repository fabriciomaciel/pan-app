package br.com.maciel.panapp.service;

import br.com.maciel.panapp.model.Endereco;
import br.com.maciel.panapp.model.Estado;
import br.com.maciel.panapp.model.Municipio;

import java.util.List;

public interface EnderecoService {

    List<Estado> obterListaEstados();

    List<Municipio> obterListaMunicipios(Integer estadoId);

    Endereco obterEnderecoDoCep(String cep);

}
