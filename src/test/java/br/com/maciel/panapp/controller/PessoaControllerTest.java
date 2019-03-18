package br.com.maciel.panapp.controller;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PessoaControllerTest {

    @Autowired
    public WebApplicationContext context;

    public MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    /********** Testes do endpoint: @GetMapping(value = "/pessoa/{cpf}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
     *
     *
     */
    /*      Cpf inválido     */
    @Test
    public void pessoa_retornar_erro_o_cpf_informado_e_invalido() throws Exception {
        mockMvc.perform(get("/pessoa/{cpf}", "AB081816604"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{'message':'Erro na validação de dados informados.'}"))
                .andExpect(content().string(Matchers.containsString("O CPF Informado não é valido")));
    }

    /*      Cpf válido porém não consta na base de clientes     */
    @Test
    public void pessoa_retornar_no_content_para_cpf_nao_encontrado_na_base() throws Exception {
        mockMvc.perform(get("/pessoa/{cpf}", "22214516867"))
                .andExpect(status().isNoContent());
    }

    /*      Cpf de um cliente válido (sem formatação)     */
    @Test
    public void pessoa_retornar_ok_para_cpf_informado_sem_formatacao() throws Exception {
        mockMvc.perform(get("/pessoa/{cpf}", "45081816604"))
                .andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().json("{'cpf':'45081816604'}"));
    }

    /*      Cpf de um cliente válido (com formatação)     */
    @Test
    public void pessoa_retornar_ok_para_cpf_informado_com_formatacao() throws Exception {
        mockMvc.perform(get("/pessoa/{cpf}", "450.818.166-04"))
                .andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().json("{'cpf':'45081816604'}"));
    }


    /********** Testes do endpoint: @GetMapping("/pessoa/{cpf}/endereco/{cep}")
     *
     *
     */
    /*      Cpf inválido     */
    @Test
    public void endereco_retornar_erro_de_cpf_invalido_ao_buscar_endereco() throws Exception {
        mockMvc.perform(get("/pessoa/{cpf}/endereco/{cep}", "ABC.818.166-04", "01111-001"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{'message':'Erro na validação de dados informados.'}"))
                .andExpect(content().string(Matchers.containsString("O CPF Informado não é valido")));
    }

    /*      CEP inválido     */
    @Test
    public void endereco_retornar_erro_de_cep_invalido_ao_buscar_endereco() throws Exception {
        mockMvc.perform(get("/pessoa/{cpf}/endereco/{cep}", "450.818.166-04", "ABC11-001"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{'message':'Erro na validação de dados informados.'}"))
                .andExpect(content().string(Matchers.containsString("O Cep informado não possui o formato correto")));
    }

    /*      Cpf e CEP inválido     */
    @Test
    public void endereco_retornar_erro_de_cpf_e_cep_invalido_ao_buscar_endereco() throws Exception {
        mockMvc.perform(get("/pessoa/{cpf}/endereco/{cep}", "ABC.818.166-04", "01111-ABC"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{'message':'Erro na validação de dados informados.'}"))
                .andExpect(content().string(Matchers.containsString("O CPF Informado não é valido")))
                .andExpect(content().string(Matchers.containsString("O Cep informado não possui o formato correto")));
    }

    /*      Cpf e CEP validos não consta na base de clientes     */
    public void endereco_retornar_no_content_para_cpf_e_cep_nao_encontrado_na_base() throws Exception {
        mockMvc.perform(get("/pessoa/{cpf}/endereco/{cep}", "22214516867", "01111-001"))
                .andExpect(status().isNoContent());
    }

    /*      Cpf válido porém CEP não consta na base de clientes     */
    public void endereco_retornar_no_content_para_cep_nao_encontrado_na_base() throws Exception {
        mockMvc.perform(get("/pessoa/{cpf}/endereco/{cep}", "450.818.166-04", "01111-001"))
                .andExpect(status().isNoContent());
    }
    /*      Cpf e CEP de um cliente válido (com formatação)     */
    @Test
    public void endereco_retornar_ok_para_cpf_e_cep_validos_com_formatacao() throws Exception {
        //Com formatação
        mockMvc.perform(get("/pessoa/{cpf}/endereco/{cep}", "450.818.166-04", "02621-003"))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json("[{'cep':'02621003'}]"));
    }
    /*      Cpf e CEP de um cliente válido (com formatação)     */
    @Test
    public void endereco_retornar_ok_para_cpf_e_cep_validos_sem_formatacao() throws Exception {
        mockMvc.perform(get("/pessoa/{cpf}/endereco/{cep}", "45081816604", "02621003"))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json("[{'cep':'02621003'}]"));
    }
    /********** Testes do endpoint: @PutMapping("/pessoa/{cpf}/endereco/{enderecoId}")
     *
     *
     */
    private final String PUT_JSON_INVALIDO = "{\n" +
            "   \"apelidoEndereco\":\"nova Casa\",\n" +
            "   \"numeroLogradouro\":3500,\n" +
            "   \"complemento\":\"Apt 111 A\",\n" +
            "   \"bairro\":\"Limão\",\n" +
            "   \"localidade\":\"São Paulo\"\n" +
            "}";
    private final String PUT_JSON_VALIDO = "{\n" +
            "   \"apelidoEndereco\":\"nova Casa\",\n" +
            "   \"logradouro\":\"Avenida das avendias da cidade\",\n" +
            "   \"numeroLogradouro\":3500,\n" +
            "   \"complemento\":\"Apt 111 A\",\n" +
            "   \"bairro\":\"Limão\",\n" +
            "   \"localidade\":\"São Paulo\",\n" +
            "   \"uf\":\"SP\",\n" +
            "   \"cep\":\"02621-003\"\n" +
            "}";

    /*      Este método é o unico que precisa ter informado no cabeçalho que está enviado um JSON  */
    @Test
    public void alteracao_cabecalho_invalido() throws Exception {
        mockMvc.perform(put("/pessoa/{cpf}/endereco/{enderecoId}", "450.818.166-04", "4")
                .contentType(MediaType.TEXT_PLAIN_VALUE))
                .andExpect(status().isUnsupportedMediaType());
    }

    /*      Não estamos informando o modelo do body da requisição  */
    @Test
    public void alteracao_modelo_nao_informado() throws Exception {
        mockMvc.perform(put("/pessoa/{cpf}/endereco/{enderecoId}", "450.818.166-04", "4"))
                .andExpect(status().isBadRequest());
    }

    /*      Informamos um enderecoId igual a Zero  */
    @Test
    public void alteracao_enderecoId_invalido() throws Exception {
        mockMvc.perform(put("/pessoa/{cpf}/endereco/{enderecoId}", "450.818.166-04", "0")
                .content(PUT_JSON_VALIDO)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{'message':'Erro na validação de dados informados.'}"))
                .andExpect(content().string(Matchers.containsString("O enderecoID informado não é valido")));
    }

    /*      Informamos um modelo incompleto no body da requisição  */
    @Test
    public void alteracao_modelo_invalido() throws Exception {
        mockMvc.perform(put("/pessoa/{cpf}/endereco/{enderecoId}", "450.818.166-04", "4")
                .content(PUT_JSON_INVALIDO)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{'message':'Erro na validação de dados informados.'}"))
                .andExpect(content().string(Matchers.containsString("O campo Endereco -> cep, é obrigatório")))
                .andExpect(content().string(Matchers.containsString("O campo Endereco -> logradouro, é obrigatório")))
                .andExpect(content().string(Matchers.containsString("O campo Endereco -> uf, é obrigatório")));
    }

    /*      Cpf inválido     */
    @Test
    public void alteracao_cpf_invalido() throws Exception {
        mockMvc.perform(put("/pessoa/{cpf}/endereco/{enderecoId}", "ABC.818.166-04", "4")
                .content(PUT_JSON_VALIDO)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{'message':'Erro na validação de dados informados.'}"))
                .andExpect(content().string(Matchers.containsString("O CPF Informado não é valido")));
    }

    /*      Alteração OK     */
    @Test
    public void alteracao_valida() throws Exception {
        mockMvc.perform(put("/pessoa/{cpf}/endereco/{enderecoId}", "450.818.166-04", "4")
                .content(PUT_JSON_VALIDO)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json("{'logradouro':'Avenida das avendias da cidade'}"));
    }
}