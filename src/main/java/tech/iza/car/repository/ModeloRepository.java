package tech.iza.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.iza.car.model.modelo.ModeloEntity;

import java.util.List;

public interface ModeloRepository extends JpaRepository<ModeloEntity, Integer> {

    //Buscando todos os modelos de uma marca específica
    List<ModeloEntity> findByMarca(Integer marca);
}
