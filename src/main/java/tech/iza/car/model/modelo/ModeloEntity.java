package tech.iza.car.model.modelo;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Entity
@Table(name = "tab_modelo")
public class ModeloEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer id;

    @Column(nullable = false, length = 30)
    private String nome;

    //Quando tentamos criar uma coluna e essa coluna sendo do tipo primitivo ela exige que a gente tenha um valor padrão
    //Essa coluna vai representar a nossa exclusão lógica de Modelo, podemos pensar da seguinte forma o Modelo foi
    //excluído? Sim ou não
    private boolean excluido;

    //Quando falamos de relacionamento entre tabelas é muito comum ouvirmos falar das anotações '@ManyToOne',
    //'@OneToMany', '@ManoToMany', '@OneToOne', e precisamos tomar muito cuidado com tais anotações é claro que quando
    //você retornar o Modelo é preciso saber a qual Marca ele pertence, mas às vezes não precisamos ter a Marca já logo
    //identificada na iteração de consulta, são questionamentos que precisamos realizar quando estamos realizando um
    //mapeamento com foco no nosso domínio

    //Então, a primeira abordagem é que eu sei que preciso da Marca e que o Modelo tem uma relação com Marca, assim
    //podemos usar a relação de id, valor inteiro, o id daquela marca isso seria o mínimo necessário que preciso
    @Column(name = "marca_id")
    private Integer marca;
}
