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
    public Response delete(@PathVariable("id") Integer id) {

        //A exclusão pode ser considerada tanto física quanto lógica, quando é uma exclusão física literalmente o
        //registro é removido do banco de dados, no meio convencional no ambiente corporativo é muito comum que a
        //gente pratique a exclusão lógica que basicamente é inativar ou sinalizar que aquele arquivo foi excluído
        //então ao invés da gente realizar um delete a gente vai realizar um update em uma coluna que determine
        //essa característica

        service.excluir(id);

        return ResponseFactory.ok(true, "Modelo excluído com sucesso");
    }
}
