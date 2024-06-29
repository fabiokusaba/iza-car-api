package tech.iza.car.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.iza.car.infra.http.Response;
import tech.iza.car.infra.http.ResponseFactory;
import tech.iza.car.model.marca.MarcaRequest;
import tech.iza.car.model.marca.MarcaResponse;
import tech.iza.car.service.MarcaService;

//Com a anotação '@RestController' estamos fazendo com que a nossa 'MarcaResource' provê uma arquitetura http diante dos
//nossos recursos

//A anotação '@RequestMapping' é como se fosse um prefixo para que todas as rotas direcionadas a marcas já começassem
//com "/marcas"

@RestController
@RequestMapping("marcas")
public class MarcaResource {

    //Aqui nós temos a necessidade da nossa 'MarcaService'
    @Autowired
    private MarcaService service;

    //E vamos fazer os verbos http para que ele represente o CRUD da nossa aplicação

    //É imprescindível lembrar o uso da anotação '@RequestBody' do spring para que seja possível receber esse elemento
    //em uma estrutura json
    @PostMapping
    public Response post(@RequestBody MarcaRequest request) {
        return ResponseFactory.create(service.incluir(request), "Marca criada com sucesso");
    }

    @PutMapping(value = "/{id}")
    public Response put(@PathVariable("id") Integer id, @RequestBody MarcaRequest request) {
        return ResponseFactory.ok(service.alterar(id, request));
    }

    @GetMapping
    public Response getList() {
        return ResponseFactory.ok(service.listar());
    }

    @GetMapping("/validas")
    public Response getListValid() {
        return ResponseFactory.ok(service.listarMarcasValidas());
    }

    //Níveis de maturidade de uma arquitetura rest
    @GetMapping(value = "/{id}")
    public Response getItem(@PathVariable("id") Integer id) {
        MarcaResponse response = service.buscar(id);
        return ResponseFactory.ok(response);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Integer id) {
        service.excluir(id);
    }

    @DeleteMapping(value = "/exclusao-logica/{id}")
    public void deleteLogic(@PathVariable("id") Integer id) {
        service.exclusaoLogica(id);
    }
}
