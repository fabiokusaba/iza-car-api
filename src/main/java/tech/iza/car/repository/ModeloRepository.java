package tech.iza.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.iza.car.model.modelo.ModeloEntity;

public interface ModeloRepository extends JpaRepository<ModeloEntity, Integer> {
}
