package tech.iza.car.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.iza.car.infra.http.Response;
import tech.iza.car.infra.http.ResponseFactory;
import tech.iza.car.model.modelo.ModeloRequest;
import tech.iza.car.model.modelo.ModeloResponse;
import tech.iza.car.service.ModeloService;

@RestController
@RequestMapping("modelos")
public class ModeloResource {

    @Autowired
    private ModeloService service;

    @PostMapping
    public Response post(@RequestBody ModeloRequest request) {
        return ResponseFactory.create(service.incluir(request), "Modelo criado com sucesso");
    }

    @PutMapping("/{id}")
    public Response put(@PathVariable("id") Integer id, @RequestBody ModeloRequest request) {
        return ResponseFactory.ok(service.alterar(id, request), "Modelo alterado com sucesso");
    }

    @GetMapping
    public Response getList() {
        return ResponseFactory.ok(service.listar());
    }

    @GetMapping("/{id}")
    public Response getItem(@PathVariable("id") Integer id) {
        ModeloResponse response = service.buscar(id);

        return ResponseFactory.ok(response);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        service.excluir(id);
    }
}
