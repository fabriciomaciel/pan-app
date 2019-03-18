package br.com.maciel.panapp.service;

import br.com.maciel.panapp.exception.PanAppException;
import br.com.maciel.panapp.model.Endereco;
import br.com.maciel.panapp.model.Pessoa;
import br.com.maciel.panapp.model.entity.PessoaEntity;
import br.com.maciel.panapp.repository.PessoaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PessoaServiceImpl implements PessoaService {

  private PessoaRepository repository;

  private static final Logger LOGGER = LoggerFactory.getLogger(PessoaServiceImpl.class);

  public PessoaServiceImpl(PessoaRepository repository) {
    this.repository = repository;
  }

  /**
   *
   * @param cpf
   * @return
   * @throws PanAppException
   */
  @Override
  public Pessoa consultarPessoa(String cpf) {
    LOGGER.info("Realizando consulta da pessoa com o parâmetro cpf: {}.", cpf);
    List<PessoaEntity> pessoas = repository.findByCpfPessoa(cpf.replace(".", "").replace("-", ""));
    if(pessoas.isEmpty()) {
      LOGGER.error("Nenhuma pessoa encontrada com o parâmetro cpf: {}.", cpf);
      return null;    }
    else {
      Pessoa p = new Pessoa();
      BeanUtils.copyProperties(pessoas.get(0), p);
      LOGGER.error("Pessoa encontrada com o parâmetro cpf: {}, -> {}.", cpf, p);
      return p;
    }
  }

  /**
   *
   * @param cpf
   * @param cep
   * @return
   * @throws PanAppException
   */
  @Override
  public List<Endereco> consultarEnderecoPessoa(String cpf, String cep)  {
    PessoaEntity pessoaEndereco = repository.findByCpfPessoaAndEnderecosCep(cpf.replace(".", "").replace("-", ""), cep.replace("-", ""));
    if(pessoaEndereco == null || pessoaEndereco.getEnderecos().isEmpty()) { return null;}
    else {
      List<Endereco> retorno = new ArrayList<Endereco>();
      pessoaEndereco.getEnderecos().forEach( entity -> {
        Endereco e = new Endereco();
        BeanUtils.copyProperties(entity, e);
        retorno.add(e);
      });
      return retorno;
    }
  }

  /**
   *
   * @param cpf
   * @param enderecoId
   * @param enderecoAlterado
   * @return
   */
  @Override
  public Endereco alterarEnderecoPessoa(String cpf, Long enderecoId, Endereco enderecoAlterado) {
    //Setamos o endereçoId com base no parâmetro informado
    enderecoAlterado.setIdEndereco(enderecoId);
    PessoaEntity pessoaEndereco = repository.findByCpfPessoaAndEnderecosIdEndereco(cpf.replace(".", "").replace("-", ""),
        enderecoId);
    if(pessoaEndereco == null || pessoaEndereco.getEnderecos().isEmpty()) { return null;}
    else {
      BeanUtils.copyProperties(enderecoAlterado, pessoaEndereco.getEnderecos().get(0));
      repository.save(pessoaEndereco);
      //retornar o endeço atualizado
      Endereco e = new Endereco();
      BeanUtils.copyProperties(pessoaEndereco.getEnderecos().get(0), e);
      return e;
    }
  }
}


