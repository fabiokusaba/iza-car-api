package tech.iza.car.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tab_marca")
@Getter
@Setter
public class MarcaEntity {

    //Nós detectamos que atributos de classes de modelo ou de domínio precisam ser privados

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer id;

    @Column(nullable = false, length = 30)
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

    //Falando em persistência de banco de dados não é conveniente definirmos o id das nossas entidades espera-se que o
    //banco faça isso por nós, diante dessa concepção vamos colocar a anotação '@GeneratedValue' para indicar que o
    //nosso banco de dados vai ser o provedor de chaves primárias

    //Como definimos que o banco de dados se encarregará da geração da chave primária de nossa tabela nós podemos
    //definir através da propriedade da anotação '@Setter' o nível de acesso do nosso id e quando definimos como NONE
    //significa dizer que ninguém terá acesso ao método "setId" dessa propriedade

}
