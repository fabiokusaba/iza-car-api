package tech.iza.car.model.veiculo;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import tech.iza.car.model.marca.MarcaEntity;

@Entity
@Table(name = "tab_veiculo")
@Data
public class VeiculoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer id;

    @Column(length = 32, nullable = false)
    private String chassi;

    @Column(length = 10, nullable = false)
    private String placa;

    @Column(length = 50, nullable = false)
    private String descricao;

    @Column(name = "ano_fabricacao", length = 8, nullable = false)
    private String anoFabricacao;

    @Column(nullable = false)
    private Integer kmAtual;

    //Se você determinar essa anotação '@Enumerated' o JPA vai determinar que você vai salvar o índice desses elementos
    //agora se você diz que o '@Enumerated' tem uma estratégia de String ele vai salvar o valor literal
    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private VeiculoCategoria categoria;

    @Column(name = "marca_id")
    private Integer marca;

    @Column(name = "modelo_id")
    private Integer modelo;
}
