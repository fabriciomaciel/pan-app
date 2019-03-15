package br.com.maciel.panapp.integration;

import br.com.maciel.panapp.integration.model.Localidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class IbgeHttpIntegration {

    /* Dados parametrizados em application.properties
     */
    @Value("${ibge.url.api}")
    private String ibgeApiUrl;
    @Value("${ibge.service.localidade}")
    private String ibgeServiceLocalidade;
    @Value("${ibge.service.municipios}")
    private String ibgeServiceMunicipio;

    @Autowired
    private RestTemplate restTemplate;


    /**
     * Obtem uma lista de localidades do service do IBGE
     * @return uma lista de Localidades
     */
    public List<Localidade> obterListaLocalidades() {
        ResponseEntity<List<Localidade>> response = restTemplate.exchange(
                ibgeApiUrl + ibgeServiceLocalidade,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Localidade>>(){});
        return response.getBody();
    }

    public List<Localidade> obterListaMunicipiosLocalidades(Integer localidadeId) {
        ResponseEntity<List<Localidade>> response = restTemplate.exchange(
                ibgeApiUrl + ibgeServiceMunicipio,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Localidade>>(){},
                localidadeId.toString());
        return response.getBody();
    }

}
