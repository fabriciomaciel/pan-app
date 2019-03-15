package br.com.maciel.panapp.service;

import br.com.maciel.panapp.integration.IbgeHttpIntegration;
import br.com.maciel.panapp.integration.ViaCepHttpIntegration;
import br.com.maciel.panapp.integration.model.Localidade;
import br.com.maciel.panapp.model.Endereco;
import br.com.maciel.panapp.model.Estado;
import br.com.maciel.panapp.model.Municipio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    private IbgeHttpIntegration ibgeService;

    private ViaCepHttpIntegration viacepService;

    private static final Logger LOGGER = LoggerFactory.getLogger(EnderecoServiceImpl.class);

    private final List<String> estadosFavoritos = new ArrayList<String>(){{
            add("SP");
            add("RJ");
        }};

    public EnderecoServiceImpl(IbgeHttpIntegration ibgeService, ViaCepHttpIntegration viacepService) {
        this.ibgeService = ibgeService;
        this.viacepService = viacepService;
    }

    /**
     * Obtem uma lista dos estados brasileiros
     * @return lista de estados
     */
    public List<Estado> obterListaEstados() {
        LOGGER.info("Obtendo lista de estados em IbgeService.");
        List<Localidade> localidades = ibgeService.obterListaLocalidades();
        if(localidades.isEmpty()) {
            LOGGER.error("Nenhum estado encontrado em IgbeService.");
            return null;
        }
        List<Estado> estadoList = localidades.stream().map(l -> {
            return new Estado(l.getId(), l.getSigla().toUpperCase(), l.getNome());
        }).collect(Collectors.toList());
        //Ordenando
        estadoList.sort((o1, o2) -> {
            if(estadosFavoritos.contains(o1.getSigla())) {
                return -999; //Faz com que os estados favoritos (SP, RJ) aparecam primeiro na listagem
            } else {
                return o1.getSigla().compareTo(o2.getSigla());
            }
        });
        LOGGER.info("Encontrados {} estados retornados pelo IbgeService.", estadoList.size());
        return estadoList;
    }

    /**
     * Obtem uma lista de municipios de um estado brasileiro
     * @param localidadeId
     * @return lista de municipios daquela localidade
     */
    public List<Municipio> obterListaMunicipios(Integer localidadeId) {
        LOGGER.info("Obtendo lista de municipios com o parâmetro localidadeId: {localidadeId}, em IbgeService.", localidadeId);
        List<Localidade> localidades = ibgeService.obterListaMunicipiosLocalidades(localidadeId);
        if(localidades.isEmpty()) {
            LOGGER.error("Nenhum municipio encontrado com o parâmetro localidadeId: {localidadeId}, em IbgeService.", localidadeId);
            return null;
        }
        LOGGER.error("Encontrados {} municipios com o parâmetro localidadeId: {localidadeId}, em IbgeService.", localidades.size());
        return localidades.stream().map(l -> {
            return new Municipio(l.getId(), l.getNome());
        }).collect(Collectors.toList());
    }

    /**
     * Requisita ao serviço de integração os dados do endereço de um determinado cep
     * @param cep
     * @return
     */
    @Override
    public Endereco obterEnderecoDoCep(String cep) {
        LOGGER.info("Obtendo dados do endereço com p parâmetro cep: {cep}, em ViaCepService.", cep);
        return viacepService.obterDadosCep(cep);
    }
}
