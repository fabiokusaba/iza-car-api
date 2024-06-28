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
}
