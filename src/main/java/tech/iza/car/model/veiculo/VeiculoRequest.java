package tech.iza.car.model.veiculo;

import lombok.Data;
import tech.iza.car.model.marca.MarcaEntity;

@Data
public class VeiculoRequest {

    private String chassi;
    private String placa;
    private String descricao;
    private String anoFabricacao;
    private VeiculoCategoria categoria;
    private Integer kmAtual;
    private Integer modelo;
    private Integer marca;
}
