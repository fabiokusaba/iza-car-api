package tech.iza.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.iza.car.model.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Integer> {

    //Para persistir uma Marca no banco de dados precisamos ter um repositório e esse repositório utilizando o Spring
    //Data JPA é muito simples

    //Tudo o que for de implementação será uma interface que vamos extender de "JpaRepository", dessa forma o Spring diz
    //que passando o nome da nossa entidade, ou seja, Marca, e qual o tipo do seu identificador já temos acesso a vários
    //métodos
}
