package tech.iza.car.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tech.iza.car.infra.business.CampoObrigatorioException;
import tech.iza.car.infra.business.RegistroNaoLocalizadoException;
import tech.iza.car.model.marca.MarcaEntity;
import tech.iza.car.model.marca.MarcaResponse;
import tech.iza.car.model.modelo.ModeloEntity;
import tech.iza.car.model.modelo.ModeloRequest;
import tech.iza.car.model.modelo.ModeloResponse;
import tech.iza.car.repository.ModeloRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ModeloService {

    private final ModeloRepository repository;

    public ModeloService(ModeloRepository repository) {
        this.repository = repository;
    }

    public Integer incluir(ModeloRequest request) {
        return gravar(null, request);
    }

    public Integer alterar(Integer id, ModeloRequest request) {
        return gravar(id, request);
    }

    public ModeloResponse buscar(Integer id) {
        return converter(buscarEntity(id));
    }

    public List<ModeloResponse> listar() {

        List<ModeloResponse> response = repository.findAll().stream().map(this::converter).collect(Collectors.toList());

        return response;
    }

    public void excluir(Integer id) {
        repository.deleteById(id);
    }

    private Integer gravar(Integer id, ModeloRequest request) {

        if (request.getNome() == null || request.getNome().isBlank()) {
            throw new CampoObrigatorioException();
        }

        ModeloEntity entity = Optional.ofNullable(id).isPresent() ? buscarEntity(id) : new ModeloEntity();

        BeanUtils.copyProperties(request, entity);

        return repository.save(entity).getId();
    }

    private ModeloEntity buscarEntity(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RegistroNaoLocalizadoException());
    }

    private ModeloResponse converter(ModeloEntity entity) {

        ModeloResponse response = new ModeloResponse();

        BeanUtils.copyProperties(entity, response);

        return response;
    }
}
