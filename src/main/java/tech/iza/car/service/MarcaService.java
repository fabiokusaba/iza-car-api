package tech.iza.car.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.iza.car.infra.business.CampoObrigatorioException;
import tech.iza.car.infra.business.RegistroNaoLocalizadoException;
import tech.iza.car.model.marca.MarcaEntity;
import tech.iza.car.model.marca.MarcaRequest;
import tech.iza.car.model.marca.MarcaResponse;
import tech.iza.car.repository.MarcaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//Camada de negócio contendo regras de negócios e dependências adicionais este nosso componente precisará ser
//identificado pelo spring

//Com a anotação '@Service' estamos dizendo que essa classe é sim um componente identificado pelo spring, mas ela tem um
//papel de representar um service

//O nosso service deve garantir a integridade do nosso registro com regras de conferência ou checagem dos valores que
//estão sendo inseridos

//O objetivo da nossa camada de service é conter regras de negócio, transformações. Já a camada de repositório tem a
//finalidade de somente realizar a persistência, confirmação, a concretização da persistência

//A grande curiosidade da nossa concepção de incluir ou alterar usando o Spring Data JPA é que o método 'save' tem a
//finalidade tanto de incluir quanto de alterar

@Service
public class MarcaService {

    //Injeção de dependência
    @Autowired
    private MarcaRepository repository;

    //Para que o nosso método 'gravar' tenha efeito nós vamos criar um método público 'alterar' chamando ele
    public Integer alterar(Integer id, MarcaRequest request) {
        return gravar(id, request);
    }

    //E da mesma forma vamos criar um método público 'incluir'
    public Integer incluir(MarcaRequest request) {
        return gravar(null, request);
    }

    //Disponibilização de recursos
    private Integer gravar(Integer id, MarcaRequest request) {

        //Garantindo a integridade dos dados
        if (request.getNome() == null || request.getNome().isBlank()) {
            throw new CampoObrigatorioException();
        }

        //Protegendo a nossa entidade
        //MarcaEntity entity = null;

        //Se foi informado o id não vai vir uma nova entidade, nós iremos buscar uma nova entidade
        //if (id != null) {
            //Ao invés de criar uma nova entidade a gente vai alterar a entidade
            //entity = buscarEntity(id);
        //} else {
            //Se não for informado o id aí sim iremos criar uma nova entidade
            //entity = new MarcaEntity();
        //}

        //Melhorando o nosso código com operações ternárias
        MarcaEntity entity = Optional.ofNullable(id).isPresent() ? buscarEntity(id) : new MarcaEntity();

        BeanUtils.copyProperties(request, entity);

        //Ao final, teremos a operação de inclusão da 'entity' retornando o seu id
        return repository.save(entity).getId();
    }

    private MarcaEntity buscarEntity(Integer id) {

        //Quando damos um 'findById' ele nos retorna um 'Optional' que é um tipo específico da linguagem Java que
        //encapsula a resposta e essa resposta pode ser uma entidade ou pode ser um elemento caracterizado como nullable
        //que quer dizer um elemento que não foi encontrado, não sendo encontrado podemos lançar uma exceção
        return repository.findById(id).orElseThrow(() -> new RegistroNaoLocalizadoException());
    }

    public MarcaResponse buscar(Integer id) {
        return converter(buscarEntity(id));
    }

    public void excluir(Integer id) {
        repository.deleteById(id);
    }

    public List<MarcaResponse> listar() {

        //Usando os recursos do Java Stream com a concepção da linguagem funcional ou lambda expressions temos a nossa
        //lista que vai buscar todas as entidades, mas aqui chamo o método 'stream' que tem o método 'map' que nada mais
        //é do que a conversão de uma estrutura para outra onde ele irá acionar o 'converter' que implicitamente vai
        //receber cada elemento convertendo para uma response
        List<MarcaResponse> response = repository.findAll().stream().map(this::converter).collect(Collectors.toList());

        return response;
    }

    //O nosso service passa a ter uma responsabilidade, além das que ele já possuí, de encapsular essa respectiva
    //conversão, preciso converter a minha Entity em uma Response
    private MarcaResponse converter(MarcaEntity entity) {

        MarcaResponse response = new MarcaResponse();

        //Podemos pedir ao spring através da classe 'BeanUtils' copiar as propriedades da nossa origem que é a entity
        //para o nosso response que é o nosso destino, claro que para essa operação os atributos precisam ser
        //correspondentes
        BeanUtils.copyProperties(entity, response);

        return response;
    }
}
