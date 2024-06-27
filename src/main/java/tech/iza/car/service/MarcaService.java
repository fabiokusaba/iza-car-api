package tech.iza.car.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.iza.car.model.MarcaEntity;
import tech.iza.car.model.MarcaResponse;
import tech.iza.car.repository.MarcaRepository;

import java.util.List;
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
    public Integer alterar(Integer id, MarcaEntity entity) {
        return gravar(id, entity);
    }

    //E da mesma forma vamos criar um método público 'incluir'
    public Integer incluir(MarcaEntity entity) {
        return gravar(null, entity);
    }

    //Disponibilização de recursos
    private Integer gravar(Integer id, MarcaEntity entity) {

        //Garantindo a integridade dos dados
        if (entity.getNome() == null) {
            throw new IllegalArgumentException("O nome da marca não pode ser nulo");
        }

        if (entity.getNome().isEmpty() || entity.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome da marca não pode ser vazio");
        }

        entity.setNome(entity.getNome().toUpperCase());

        //Vamos dizer que a nossa 'dbEntity' por default vai ser igual a 'entity'
        MarcaEntity dbEntity = entity;

        //Se não, podemos novamente verificar se o 'id' é diferente de nulo
        if (id != null) {

            //Tentamos fazer o processo de buscar se já existe, se não achar lança uma exceção
            dbEntity = buscarEntity(id);

            //Quando a 'dbEntity' for localizada ela terá o seu id e não a 'entity' que foi criada aqui, a 'dbEntity'
            //vai ter um valor padrão, então se temos a 'dbEntity' a 'entity' deverá vir com alguma característica
            //alterada

            //A nossa entidade foi localizada no banco, mas está com o valor desatualizado a nossa 'entity' veio com
            //o valor atualizado, então desta forma precisamos fazer esse tratamento
            dbEntity.setNome(entity.getNome());
        }

        //Ao final, teremos a operação de inclusão da 'dbEntity' retornando o seu id
        return repository.save(dbEntity).getId();
    }

    private MarcaEntity buscarEntity(Integer id) {

        //Quando damos um 'findById' ele nos retorna um 'Optional' que é um tipo específico da linguagem Java que
        //encapsula a resposta e essa resposta pode ser uma entidade ou pode ser um elemento caracterizado como nullable
        //que quer dizer um elemento que não foi encontrado, não sendo encontrado podemos lançar uma exceção
        return repository.findById(id).orElseThrow(() -> new NullPointerException("Marca não encontrada pelo id " + id));
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
