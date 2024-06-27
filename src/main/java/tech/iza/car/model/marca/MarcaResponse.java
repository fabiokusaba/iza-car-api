package tech.iza.car.model.marca;

//Dto -> precisamos proteger e restringir a visibilidade direta das nossas entidades dessa forma temos familiaridade
//do que chamamos de dto ou data transfer object e essa representação determina que ou sugere que as tuas classes de
//entidade que tem forte relação com banco de dados muita das vezes não podem ser externalizadas para as suas APIs
//diante disso nós precisamos criar uma transformação desses elementos para uma nova estrutura que ela esteja mais
//protegida ou que ela encapsule as requisições e a estruturação das nossas entidades

//Então essa é a proposta dos dtos garantir que a nossa camada de entidade ela esteja protegida da nossa camada
//pública que são os nossos recursos

import lombok.Data;

@Data
public class MarcaResponse {

    private Integer id;
    private String nome;
}
