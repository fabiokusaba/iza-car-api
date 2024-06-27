package tech.iza.car.infra.http;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Resposta da requisição", description = "Representação padrão do conteúdo das respostas")
public class Response {

    ResponseStatus status;
    @Schema(description = "Corpo da resposta da requisição que pode ser uma lista, um objeto ou um")
    Object body;
}
