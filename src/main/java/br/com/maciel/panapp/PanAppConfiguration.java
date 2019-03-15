package br.com.maciel.panapp;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PanAppConfiguration {

    /*
        Definindo uma unica instancia do RestTemplate para ser usada
        nas classes de integaração http
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

}
