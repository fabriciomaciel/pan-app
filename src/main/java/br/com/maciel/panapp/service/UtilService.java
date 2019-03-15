package br.com.maciel.panapp.service;

import br.com.maciel.panapp.exception.PanAppException;
import org.hibernate.validator.constraints.br.CPF;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UtilService {

    private final Validator validator;
    private static final Logger LOGGER = LoggerFactory.getLogger(UtilService.class);

    public UtilService(Validator validator) {
        this.validator = validator;
    }

    /**
     * Valida os dados informados de acordo com a especificação JSR 380
     * @param cpf
     * @param cep
     * @return
     * @throws PanAppException
     */
    public boolean validarDadosEntrada(String cpf, String cep) throws PanAppException {
        Set<ConstraintViolation<Object>> validateResult = validator.validate(new ValidateRequestModel(cpf, cep));
        if(!validateResult.isEmpty()) {
            List<String> errorDetails = new ArrayList<String>();
            validateResult.forEach( it -> {
                errorDetails.add(it.getMessage());
                LOGGER.error("Erro de validação para os parâmetros cpf: {}, cep: {}, erro: {}.", cpf, cep, it.getMessage());
            });
            throw new PanAppException(HttpStatus.BAD_REQUEST, "Erro na validação de dados informados.", errorDetails);
        }
        LOGGER.info("Validação realizada com sucesso para os parâmetros cpf: {}, cep: {}.", cpf, cep);
        return true;
    }

    private class ValidateRequestModel  {
        @CPF(message = "O CPF Informado não é valido")
        private String cpf;
        @Size(min = 8, max = 9, message = "O Cep informado não possui o formato correto")
        @Pattern(regexp = "[0-9]{5}-?[0-9]{3}", message = "O Cep informado não possui o formato correto")
        private String cep;

        public ValidateRequestModel(String cpf,String cep) {
            this.cpf = cpf;
            this.cep = cep;
        }

        public String getCpf() {
            return cpf;
        }

        public String getCep() {
            return cep;
        }
    }
}
