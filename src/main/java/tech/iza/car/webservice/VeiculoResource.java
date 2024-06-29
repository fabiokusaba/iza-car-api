package tech.iza.car.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.iza.car.infra.http.Response;
import tech.iza.car.infra.http.ResponseFactory;
import tech.iza.car.model.veiculo.VeiculoProjection;
import tech.iza.car.model.veiculo.VeiculoRequest;
import tech.iza.car.repository.VeiculoRepository;
import tech.iza.car.service.VeiculoService;

import java.util.List;

@RestController
@RequestMapping("veiculos")
public class VeiculoResource {

    @Autowired
    private VeiculoService service;

    @Autowired
    private VeiculoRepository repository;

    @PostMapping
    public Response post(@RequestBody VeiculoRequest request) {
        return ResponseFactory.create(service.incluir(request), "Veiculo criado com sucesso");
    }

    @GetMapping
    public List<VeiculoProjection> lista() {
        return repository.list();
    }
}
