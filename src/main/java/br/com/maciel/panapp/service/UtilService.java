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

    private Validator validator;
    private Logger logger;

    public UtilService(Validator validator) {
        this.validator = validator;
        logger = LoggerFactory.getLogger(UtilService.class);
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
        if(validateResult.size() > 0) {
            List<String> errorDetails = new ArrayList<String>();
            validateResult.forEach( it -> {
                errorDetails.add(it.getMessage());
                logger.error("Erro de validação para os parâmetros cpf: {}, cep: {}, erro: {}.", cpf, cep, it.getMessage());
            });
            throw new PanAppException(HttpStatus.BAD_REQUEST, "Erro na validação de dados informados.", errorDetails);
        }
        logger.info("Validação realizada com sucesso para os parâmetros cpf: {}, cep: {}.", cpf, cep);
        return true;
    }

    class ValidateRequestModel  {
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
