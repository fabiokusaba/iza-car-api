package tech.iza.car.model.veiculo;

//O nosso 'VeiculoResponse' vai ser o retorno do nosso repositório
//Nesse caso esse nosso 'VeiculoResponse' vai precisar ser uma interface porque a interface o Spring tem um conceito que
//chamamos de projeções/projections e ele diz que se você tiver os métodos Getters dessa projeção eu consigo usar um
//recurso que a gente chama de reflection que eu vou pegar as colunas e montar esses objetos pra você sendo uma
//interface

public interface VeiculoProjection {

    String getChassi();

    String getPlaca();

    String getDescricao();

    String getMarca();

    String getModelo();

    //String getAnoFabricacao();

    //VeiculoCategoria getCategoria();

    //Integer getKmAtual();
}
