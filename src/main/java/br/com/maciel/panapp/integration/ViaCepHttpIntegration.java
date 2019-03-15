package br.com.maciel.panapp.integration;

import br.com.maciel.panapp.model.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ViaCepHttpIntegration {

    @Value("${via.cep.url.api}")
    private String viaCepUrlApi;
    @Value("${via.cep.service.cep}")
    private String viaCepServiceCep;

    @Autowired
    private RestTemplate restTemplate;


    /**
     * Consulta no endpoint da API ViaCep para obter os dados de endereço de um determinado CEP
     * @param cep
     * @return
     */
    public Endereco obterDadosCep(String cep) {
        ResponseEntity<Endereco> response = restTemplate.exchange(
                viaCepUrlApi + viaCepServiceCep,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Endereco>(){},
                cep.replace("-", ""));
        return response.getBody();
    }
}
