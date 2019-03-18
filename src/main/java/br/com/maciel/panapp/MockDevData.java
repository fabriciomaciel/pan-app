package br.com.maciel.panapp;

import br.com.maciel.panapp.model.TIPO_TELEFONE;
import br.com.maciel.panapp.model.entity.EnderecoEntity;
import br.com.maciel.panapp.model.entity.PessoaEntity;
import br.com.maciel.panapp.model.entity.TelefoneEntity;
import br.com.maciel.panapp.repository.EnderecoRepository;
import br.com.maciel.panapp.repository.PessoaRepository;
import br.com.maciel.panapp.repository.TelefoneRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
/**
 *  Classe de apoio usada no desenvolvimento para popular automaticamente os dados da base em memória
 */
public class MockDevData implements ApplicationListener<ContextRefreshedEvent> {

  private PessoaRepository pessoaRepo;
  private EnderecoRepository enderecoRepo;
  private TelefoneRepository telefoneRepo;

  //Constructor injection
  public MockDevData(PessoaRepository pessoaRepo, EnderecoRepository enderecoRepo, TelefoneRepository telefoneRepo) {
    this.pessoaRepo = pessoaRepo;
    this.enderecoRepo = enderecoRepo;
    this.telefoneRepo = telefoneRepo;
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

    PessoaEntity pessoa = createPessoa();
    pessoa.setTelefones(createTelefones());
    telefoneRepo.saveAll(pessoa.getTelefones());
    pessoa.setEnderecos(createEnderecos());
    enderecoRepo.saveAll(pessoa.getEnderecos());
    //
    pessoaRepo.save(pessoa);

  }

  /**
   * Cria uma pessoa Mock
   * @return uma pessoa
   */
  private PessoaEntity createPessoa() {
    PessoaEntity p = new PessoaEntity();
      p.setCpfPessoa("45081816604");
    p.setNomePessoa("Nome cliente com cpf final 04");
    p.setFiliacaoMae("Mae do cliente com cpf final 04");
    p.setFiliacaoPai("Pai do cliente com cpf final 04");
    return p;
  }

  /**
   * Cria uma lista mock de telefones
   * @return a lista de telefones
   */
  private List<TelefoneEntity> createTelefones() {
    List<TelefoneEntity> listaTelefones = new ArrayList<TelefoneEntity>();
    listaTelefones.add(new TelefoneEntity(TIPO_TELEFONE.RESIDENCIAL, 11, 22449966));
    listaTelefones.add(new TelefoneEntity(TIPO_TELEFONE.CELULAR, 11, 988771122));
    return listaTelefones;
  }

  /**
   * Cria uma lista mock de endereços
   * @return a lista de endereços
   */
  private List<EnderecoEntity> createEnderecos() {
    List<EnderecoEntity> listaEnderecos = new ArrayList<EnderecoEntity>();
    EnderecoEntity e = new EnderecoEntity();
    e.setApelidoEndereco("Casa");
    e.setCep("02721200");
    e.setLogradouro("Nome da rua residencial");
    e.setNumeroLogradouro(3555);
    e.setBairro("Limão");
    e.setLocalidade("São Paulo");
    e.setUf("SP");
    listaEnderecos.add(e);
    //
    e = new EnderecoEntity();
    e.setApelidoEndereco("Escritorio");
    e.setCep("02621003");
    e.setLogradouro("Nome da rua comercial");
    e.setNumeroLogradouro(100);
    e.setBairro("Freguesia do ó");
    e.setLocalidade("São Paulo");
    e.setUf("SP");
    listaEnderecos.add(e);
    //
    return listaEnderecos;
  }
}
