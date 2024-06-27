package tech.iza.car.model.marca;

//Quando precisamos incluir uma marca nós não precisamos do id, se estou criando uma requisição essa requisição precisa
//ser a representação fiel da request no payload no json diante disso criamos a nossa Request

//Então, vamos converter as nossas requests em entidades no qual serão as operações para incluir novos elementos ou
//alterar os elementos da nossa aplicação

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarcaRequest {

    private String nome;
}
