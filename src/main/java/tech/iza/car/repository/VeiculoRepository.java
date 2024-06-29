package tech.iza.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tech.iza.car.model.veiculo.VeiculoEntity;
import tech.iza.car.model.veiculo.VeiculoProjection;

import java.util.List;

public interface VeiculoRepository extends JpaRepository<VeiculoEntity, Integer> {

    //Usamos a anotação '@Query' para podermos usar o poder do JPQL
    @Query("SELECT v.chassi as chassi, v.placa as placa, v.descricao as descricao, m.nome as marca, md.nome as modelo " +
            "FROM VeiculoEntity v " +
            "JOIN MarcaEntity m ON v.marca = m.id " +
            "JOIN ModeloEntity md on v.modelo = md.id")
    List<VeiculoProjection> list();
}
