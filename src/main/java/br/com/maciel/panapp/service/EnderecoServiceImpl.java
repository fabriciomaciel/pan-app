package br.com.maciel.panapp.service;

import br.com.maciel.panapp.integration.IbgeHttpIntegration;
import br.com.maciel.panapp.integration.model.Localidade;
import br.com.maciel.panapp.model.Estado;
import br.com.maciel.panapp.model.Municipio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    private IbgeHttpIntegration ibgeService;

    private Logger logger;

    private final List<String> estadosFavoritos = new ArrayList<String>(){{
            add("SP");
            add("RJ");
        }};

    public EnderecoServiceImpl(IbgeHttpIntegration ibgeService) {
        this.ibgeService = ibgeService;
        logger = LoggerFactory.getLogger(EnderecoServiceImpl.class);
    }

    /**
     * Obtem uma lista dos estados brasileiros
     * @return lista de estados
     */
    public List<Estado> obterListaEstados() {
        List<Localidade> localidades = ibgeService.obterListaLocalidades();
        List<Estado> estadoList = localidades.stream().map(l -> {
            return new Estado(l.getId(), l.getSigla().toUpperCase(), l.getNome());
        }).collect(Collectors.toList());
        //Ordenando
        estadoList.sort((o1, o2) -> {
            if(estadosFavoritos.contains(o1.getSigla()))
                return -999; //Faz com que os estados favoritos (SP, RJ) aparecam primeiro na listagem
            else
                return o1.getSigla().compareTo(o2.getSigla());
        });
        return estadoList;
    }

    /**
     * Obtem uma lista de municipios de um estado brasileiro
     * @param localidadeId
     * @return lista de municipios daquela localidade
     */
    public List<Municipio> obterListaMunicipios(Integer localidadeId) {
        List<Localidade> localidades = ibgeService.obterListaMunicipiosLocalidades(localidadeId);
        List<Municipio> municipioList = localidades.stream().map(l -> {
            return new Municipio(l.getId(), l.getNome());
        }).collect(Collectors.toList());
        return municipioList;
    }
}
