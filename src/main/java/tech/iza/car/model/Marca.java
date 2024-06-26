package tech.iza.car.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tab_marca")
@Getter
@Setter
public class Marca {

    //Nós detectamos que atributos de classes de modelo ou de domínio precisam ser privados

    @Id
    @Column(name = "id_marca")
    private Integer id;

    @Column(name = "nm_marca", nullable = false, length = 40)
    private String nome;

    //Para que os nossos atributos sejam acessados pela nossa aplicação precisamos ter os métodos acessores e métodos de
    //definição, com a utilização do Lombok usamos a anotação '@Getter' e '@Setter' ou podemos usar a anotação '@Data'
    //que nos fornece além dos getters e setters, toString e outras funcionalidades

    //A partir de agora vamos pensar que essa nossa classe Marca se tornará uma entidade, iremos trazer o poder de
    //persistência para a nossa aplicação e para isso vamos estar utilizando o JPA

    //Anotando a nossa classe com '@Entity' ela passará a ser uma entidade e o primeiro parâmetro que precisamos
    //considerar a partir de agora é que toda entidade na concepção de persistência ela precisa do seu identificador
    //para isso precisamos dizer quem é o atributo '@Id' aqui dentro da concepção da nossa classe

    //Em algumas convenções de mercado a gente usa o que chamamos de prefixos ou nomeações da tabela para isso surge
    //uma nova anotação chamada '@Table' onde a gente nomeia a nossa tabela, muitas das vezes essas convenções usam o
    //prefixo "tb_nome_da_tabela" no plural, "tab_nome_da_tabela", caracterizando o mapeamento real do nosso projeto

    //Poderíamos colocar distinções de nomenclatura e características em colunas com a anotação '@Column'

}
